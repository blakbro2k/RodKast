package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.ui.RadialProgressBar;
import net.asg.game.utils.parser.RodkastEpisode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

//import org.tritonus.share.sampled.TAudioFormat;
//import org.tritonus.share.sampled.file.TAudioFileFormat;

public class AudioUtils {
    private static final String CONTENT_LENGTH = "Content-Length";
    private static AudioUtils _ourInstance = new AudioUtils();
    private static Map<String,Music> _audioTable = new Hashtable<>();
    private static Map<String, AudioUtils.EpisodeAudio> _audioIndex = new Hashtable<>();
    private static String _currentAudioPlayingName = null;
    private static Music _episodeMusicObject;

    private static final String EXTERNAL_STORAGE_PREFERENCE = "ext_storage"; //true = external; false = internal
    private static final String STORAGE_PATH_PREFERENCE = "storage_path";
    private static final String INTERNET_DOWNLOAD_PREFERENCE = "inet_only";
    private static final String INTERNAL_ASSETS_PATH = "data/";


    private AudioUtils() {
        setStoragePref(true);
        setStoragePathPref(null);
    }

    public static AudioUtils getInstance() {
        return _ourInstance;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(GlobalConstants.PREFERENCES_NAME);
    }

    private void addAudio(String key, Music music){
        if(key == null || music == null){
            return;
        }

        Music audio = getAudio(key);

        if(audio == null){
            _audioTable.put(key, music);
        }
    }

    private Music getAudio(String key) {
        if(key == null){
            return null;
        }
        return _audioTable.get(key);
    }

    private boolean isDownloaded(RodkastEpisode episode) {
        if(episode == null){
            return false;
        }

        return isDownloaded(getFileFromURL(episode.getMediaLink()));
    }

    private boolean isDownloaded(String episodeName) {
        if(episodeName == null){
            return false;
        }

        String rodKastEpisode = getFullFilePath(episodeName);
        return getExternalPref()? Gdx.files.external(rodKastEpisode).exists() : Gdx.files.internal(rodKastEpisode).exists();
    }

    public String getStorageFolderPref(){
        return getPreferences().getString(STORAGE_PATH_PREFERENCE, GlobalConstants.DEFAULT_DOWNLOAD_FOLDER);
    }

    public boolean getExternalPref(){
        return getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, true);
    }

    public boolean getInternetOnlyPref(){
        return getPreferences().getBoolean(INTERNET_DOWNLOAD_PREFERENCE, true);
    }

    public void setStoragePref(boolean bool) {
        saveBoolean(EXTERNAL_STORAGE_PREFERENCE, bool);
    }

    public void setInternetOnlyPref(boolean bool) {
        saveBoolean(INTERNET_DOWNLOAD_PREFERENCE, bool);
    }

    public void setStoragePathPref(String path) {
        if(path == null){
            path = GlobalConstants.DEFAULT_DOWNLOAD_FOLDER;
        }
        saveString(STORAGE_PATH_PREFERENCE, path);
    }

    public void toggleStoragePref() {
        saveBoolean(EXTERNAL_STORAGE_PREFERENCE, !getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, true));
    }

    private void saveBoolean(String key, boolean value) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(key, value);
        preferences.flush();
    }

    private void saveString(String key, String value) {
        Preferences preferences = getPreferences();
        preferences.putString(key, value);
        preferences.flush();
    }

    public void dispose() {
        //Utils.disposeObjects(currentEpisode);
        //mediaLink = null;
        //type = null;
        _audioTable.clear();
        _audioTable = null;
        _ourInstance = null;
        _currentAudioPlayingName = null;
    }

    public void playEpisode(RodkastEpisode episode) {
        if(isDownloaded(episode)){
            String currentEpisodeName = getEpisodeAudioFile(episode);

            if(!isCurrentlyPlaying(currentEpisodeName)){
                pauseCurrentEpisode();
            }

            _currentAudioPlayingName = currentEpisodeName;
            toggleEpisode(_currentAudioPlayingName);
        }
    }


    public void setEpisodePosition(RodkastEpisode episode) {
        String currentEpisodeName = getEpisodeAudioFile(episode);

        if(isCurrentlyPlaying(currentEpisodeName)){
            System.out.println("TODO: Changing Position");

        }
    }

    private void toggleEpisode(String audioName){
        //TODO: Need to have one global Music file that is destroyed and created as needed.
        if(audioName != null){
            Music episodeAudio = getAudio(audioName);
            if(episodeAudio == null){
                episodeAudio = createNewAudio(audioName);
                addAudio(audioName, episodeAudio);
            }

            //FIXME: episodeAudio might not exist
            if(episodeAudio.isPlaying()){
                episodeAudio.pause();
            } else {
                episodeAudio.play();
            }
        }
    }

    public float getEpisodePositionValue(){
        Music episodeAudio = getCurrentlyPlaying();
        if(episodeAudio == null){
            return 0f;
        }

        episodeAudio.getPosition();
        //episodeAudio.
        return 0.5f;
    }

    private boolean isCurrentlyPlaying(String currentEpisodeName) {
        return _currentAudioPlayingName != null && !_currentAudioPlayingName.isEmpty() && _currentAudioPlayingName.equals(currentEpisodeName);
    }

    private Music getCurrentlyPlaying(){
        return getAudio(_currentAudioPlayingName);
    }

    private void pauseCurrentEpisode() {
        Music currentAudio = getCurrentlyPlaying();

        if(currentAudio != null){
            currentAudio.pause();
        }
    }

    private String getEpisodeAudioFile(RodkastEpisode episode){
        if(episode == null){
            return null;
        }
        return getFileFromURL(episode.getMediaLink());
    }


    public void dowloadEpisode(RodkastEpisode episode){
        if(episode == null){
            return;
        }
            boolean isDownloaded = isDownloaded(episode);
        //TODO: Add temp download functionality
        //TODO: it should download as ._temp
        //TODO: count all ._temp if exist restart downloads.
        //TODO: Check Network availibility
        //TODO: Check dl over Network config
        //TODO: error retry if network is unavailibly

            if(!isDownloaded){
                beginDownload(episode);
            }
    }

    private String getFileFromURL(URL url){
        if(url == null) {
            return null;
        }
        String filePath = url.getFile();
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }

    private void beginDownload(RodkastEpisode episode){
        if(episode == null) {
            throw new RuntimeException("Invalid episode");
        }

        URL episodeLink = episode.getMediaLink();
        System.out.println("Trying to download...]" + getFileFromURL(episodeLink));

        HttpRequest request = new HttpRequest(HttpMethods.GET);
        request.setTimeOut(GlobalConstants.HTTP_REQUEST_TIMEOUT);
        request.setUrl(episodeLink.toString());

        try {
            // Send the request, listen for the response
            Gdx.net.sendHttpRequest(request, createNewRodKastListener(getFileFromURL(episodeLink)));
        } catch (GdxRuntimeException e){
            throw new GdxRuntimeException(e);
        }
    }

    public float getAudioDownloadProgressValue(RodkastEpisode episode){
        if(episode != null){
            EpisodeAudio audioFile = getAudioFromIndex(getEpisodeAudioFile(episode));

            if(audioFile != null){
                return audioFile.getProgress();
            }
        }
        return 0;
    }

    public void addAudioToIndex(String fileName){
        if(fileName != null){
            EpisodeAudio audio = _audioIndex.get(fileName);

            if(audio == null){
                _audioIndex.put(fileName, new EpisodeAudio(fileName));
            }
        }
    }

    public EpisodeAudio getAudioFromIndex(String fileName){
        if(fileName != null){
            return _audioIndex.get(fileName);
        }
        return null;
    }


    public String getFullFilePath(String fileName){
        if(fileName == null){
            fileName = "";
        }
        return getStorageFolderPref() + "\\" + fileName;
    }


    private HttpResponseListener createNewRodKastListener(final String fileName) throws GdxRuntimeException{
        if(fileName == null){
            throw new GdxRuntimeException("Episode not found");
        }

        AudioUtils.getInstance().addAudioToIndex(fileName);

        return new HttpResponseListener() {
            @Override
            public void handleHttpResponse (HttpResponse httpResponse) {
                // Determine how much we have to download
                long length = Long.parseLong(httpResponse.getHeader(CONTENT_LENGTH));
                EpisodeAudio audioIndexObject = AudioUtils.getInstance().getAudioFromIndex(fileName);
                audioIndexObject.updateAudioLength(length);

                // We're going to download the file to external storage, create the streams
                InputStream is = httpResponse.getResultAsStream();
                OutputStream os = Gdx.files.external(getFullFilePath(fileName)).write(false);

                byte[] bytes = new byte[1024];
                int count;
                long read = 0;
                try {
                    // Keep reading bytes and storing them until there are no more.
                    while ((count = is.read(bytes, 0, bytes.length)) != -1) {
                        os.write(bytes, 0, count);
                        read += count;

                        // Update the UI with the download progress
                        //System.out.println("float: " + ((double) read / (double) length));
                        final int progress = ((int) (((double) read / (double) length) * 100));
                        final String progressString = progress == 100 ? "Click to download" : progress + "%";

                        audioIndexObject.updateProgres(progress);
                        //progressBar.setValue(progress);

                        // Since we are downloading on a background thread, post a runnable to touch ui
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run () {
                                if (progress == 100) {
                                    System.out.println(progressString);
                                    //FileHandle file = new FileHandle();
                                    //rename file;
                                    //button.setDisabled(false);
                                }
                                //System.out.println(fileName);
                                //ystem.out.println(progressString);
                                //button.setText(progressString);
                            }
                        });
                    }
                } catch (IOException e) {
                    throw new GdxRuntimeException(e);
                } finally {
                    Utils.closeInputStream(is);
                    Utils.closeOutputStream(os);
                }
            }
            @Override
            public void failed (final Throwable t) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run () {
                        ErrorUtils.getInstance().showErrorPopup(t);
                        throw new GdxRuntimeException(t);
                    }
                });
            }

            @Override
            public void cancelled() {
                throw new UnsupportedOperationException("Cancel feature is Unsupported");
            }
        };
    }

    private Music createNewAudio(String fileName) {
        boolean isExternal = getExternalPref();
        System.out.println("createNewAudio: " + isExternal);
        String rodKastEpisode = getFullFilePath(fileName);

        if(isExternal){
            return Gdx.audio.newMusic(Gdx.files.external(rodKastEpisode));
        } else {
            return Gdx.audio.newMusic(Gdx.files.internal(rodKastEpisode));
        }
    }

    private class EpisodeAudio{
        private float position;
        private String filePath;
        private boolean isPlaying;
        private float totalFileLength;
        private String type;
        private boolean isDownloadComplete;
        public float progress;
        private byte[] fileBytesBuffer;
        private RadialProgressBar downloadButton;

        public long duration;
        private String title;
        private String author;
        private String album;
        private String date;
        private String copyright;
        private String comment;
        //private Mpg123Decoder decoder;

        EpisodeAudio(String episodeName){
            System.out.println("added " + episodeName + "to Download index");

            if(isDownloaded(episodeName)){
                progress = 100;
            }
            //this.downloadButton = downloadButton;

            //this.title = episodeName
            /*
            File file = new File(filename);
            AudioFileFormat baseFileFormat = null;
            AudioFormat baseFormat = null;
            baseFileFormat = AudioSystem.getAudioFileFormat(file);
            baseFormat = baseFileFormat.getFormat();

            if (baseFileFormat instanceof TAudioFileFormat)
            {
                Map properties = ((TAudioFileFormat)baseFileFormat).properties();
                String key = "author";
                String val = (String) properties.get(key);
                key = "mp3.id3tag.v2";
                InputStream tag= (InputStream) properties.get(key);
            }
            // TAudioFormat properties
            if (baseFormat instanceof TAudioFormat)
            {
                Map properties = ((TAudioFormat)baseFormat).properties();
                String key = "bitrate";
                Integer val = (Integer) properties.get(key);
            }*/
        }

        public void updateProgres(float value) {
            isDownloadComplete = value > 100;
            progress = value;
            //downloadButton.setValue(value);
        }

        public void updateAudioLength(long value) {
            this.duration = value;
        }

        public float getProgress(){
            return progress * .01f;
        }
    }
}