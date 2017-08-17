package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.GdxRuntimeException;

import net.asg.game.stages.RodkastStageAdapter;
import net.asg.game.utils.parser.RodkastEpisode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class AudioUtils {
    private static AudioUtils ourInstance = new AudioUtils();
    private static Map<String,Music> audioTable = new Hashtable<>();
    private float songDuration;
    private float currentPosition;
    private RodkastEpisode currentEpisode;
    private String currentEpisodeName;


    private static final String EXTERNAL_STORAGE_PREFERENCE = "ext_storage"; //true = external; false = internal
    private static final String STORAGE_PATH_PREFERENCE = "storage_path";
    private static final String INTERNET_DOWNLOAD_PREFERENCE = "inet_only";

    private static final String INTERNAL_ASSETS_PATH = "data/";


    private AudioUtils() {
        setStoragePref(true);
        setStoragePathPref(null);
    }

    public static AudioUtils getInstance() {
        return ourInstance;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(GlobalConstants.PREFERENCES_NAME);
    }

    public void setEpisode(RodkastEpisode episode) {
        if(episode != null && !episode.equals(currentEpisode)){
            this.songDuration = episode.getDuration();
            this.currentEpisode = episode;
            this.currentEpisodeName = getEpisodeAudioFile(currentEpisode);
        }

        if(isDownloaded(currentEpisode)){
            addAudio(currentEpisodeName,createNewAudio(currentEpisodeName));
        }
    }

    private void addAudio(String key, Music music){
        if(key == null || music == null){
            return;
        }

        Music audio = getAudio(key);

        if(audio == null){
            audioTable.put(key, music);
        }
    }

    private Music getAudio(String key) {
        if(key == null){
            return null;
        }
        return audioTable.get(key);
    }

    private boolean isDownloaded(RodkastEpisode episode) {
        if(episode == null){
            return false;
        }

        String localFileName = getFileFromURL(episode.getMediaLink());

        boolean isExternal = getExternalPref();
        String rodKastEpisode = getStorageFolderPref() + "\\" + localFileName;

        return isExternal? Gdx.files.external(rodKastEpisode).exists() : Gdx.files.internal(rodKastEpisode).exists();
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

    public float getSongDuration(){
        return songDuration;
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
        Utils.disposeObjects(currentEpisode);
        //mediaLink = null;
        //type = null;
        audioTable.clear();
        audioTable = null;
    }

    public void playEpisode(RodkastEpisode episode) {
        System.out.println("Play Button pressed: episode : " + episode);

        //check if is download, no? kick rocks
        //check if currently playing, yes? kick rocks
        //no, pause currently playing
        //set this to currently playing
        //play this episode

        if(isDownloaded(episode)){
            currentEpisodeName = getEpisodeAudioFile(episode);
            System.out.println(currentEpisodeName + "is downloaded.");

            Music episodeAudio = createNewAudio(currentEpisodeName);
            System.out.println("episodeAudio: " + episodeAudio);
            System.out.println("audioTable: " + audioTable);
            System.out.println("is : " + currentEpisodeName + "playing? " + isCurrentlyPlaying(episodeAudio));

            if(!isCurrentlyPlaying(episodeAudio)){
                pauseCurrentEpisode();
                setCurrentlyplaying(episodeAudio);
                toggleEpisode(episodeAudio);
            } else {
                toggleEpisode(episodeAudio);
            }
        }
    }

    private void toggleEpisode(Music episodeAudio){
        if(episodeAudio != null){
            if(episodeAudio.isPlaying()){
                episodeAudio.pause();
            } else {
                episodeAudio.play();
            }
        }
    }

    private boolean isCurrentlyPlaying(Music episodeAudio) {
        Music playingAudio = getCurrentlyPlaying();
        return playingAudio != null && playingAudio.equals(episodeAudio);
    }

    private Music getCurrentlyPlaying(){
        return getAudio("playing");
    }

    private void pauseCurrentEpisode() {
        Music currentAudio = getCurrentlyPlaying();

        if(currentAudio != null){
            currentAudio.pause();
        }
    }

    public void setCurrentlyplaying(Music episodeAudio) {
        if(episodeAudio != null){
            addAudio("playing", episodeAudio);
        }
    }

    private String getEpisodeAudioFile(RodkastEpisode episode){
        if(episode == null){
            return null;
        }
        return getFileFromURL(episode.getMediaLink());
    }


    public void dowloadEpisode(RodkastEpisode episode) {
            boolean isDownloaded = isDownloaded(episode);

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

    private void beginDownload(RodkastEpisode episode) {
        if(episode == null) {
            throw new RuntimeException("Invalid episode");
        }

        URL episodeLink = episode.getMediaLink();
        System.out.println("Trying to download...]" + getFileFromURL(episodeLink));

        HttpRequest request = new HttpRequest(HttpMethods.GET);
        request.setTimeOut(GlobalConstants.HTTP_REQUEST_TIMEOUT);
        request.setUrl(episodeLink.toString());

        String fileName = getFileFromURL(episodeLink);

        // Send the request, listen for the response
        Gdx.net.sendHttpRequest(request, createNewRodKastListener(fileName));
    }

    private HttpResponseListener createNewRodKastListener(String fileName) throws GdxRuntimeException{
        if(fileName == null){
            throw new GdxRuntimeException("Episode not found");
        }

        final String name = fileName;

        return new HttpResponseListener() {
            @Override
            public void handleHttpResponse (HttpResponse httpResponse) {
                // Determine how much we have to download
                long length = Long.parseLong(httpResponse.getHeader("Content-Length"));

                // We're going to download the file to external storage, create the streams
                InputStream is = httpResponse.getResultAsStream();
                OutputStream os = Gdx.files.external(getStorageFolderPref() + "\\" + name).write(false);

                byte[] bytes = new byte[1024];
                int count = -1;
                long read = 0;
                try {
                    // Keep reading bytes and storing them until there are no more.
                    while ((count = is.read(bytes, 0, bytes.length)) != -1) {
                        os.write(bytes, 0, count);
                        read += count;

                        // Update the UI with the download progress
                        System.out.println("float" + (read / length));
                        final int progress = ((int) (((double) read / (double) length) * 100));
                        final String progressString = progress == 100 ? "Click to download" : progress + "%";

                        // Since we are downloading on a background thread, post a runnable to touch ui
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run () {
                                if (progress == 100) {
                                    System.out.println(progressString);
                                    //addAudio(name, createNewAudio(name));
                                    //button.setDisabled(false);
                                }
                                System.out.println(progressString);
                                //button.setText(progressString);
                            }
                        });
                    }
                } catch (IOException e) {

                } finally {
                    try {
                        is.close();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void failed (Throwable t) {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run () {
                        //button.setText("Too bad. Download failed.");
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
        String rodKastEpisode = getStorageFolderPref() + "\\" + fileName;

        if(isExternal){
            return Gdx.audio.newMusic(Gdx.files.external(rodKastEpisode));
        } else {
            return Gdx.audio.newMusic(Gdx.files.internal(rodKastEpisode));
        }
    }
}