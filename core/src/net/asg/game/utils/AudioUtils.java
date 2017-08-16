package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.RodkastStageAdapter;
import net.asg.game.utils.parser.RodkastEpisode;

import java.net.URL;

public class AudioUtils {
    private static AudioUtils ourInstance = new AudioUtils();
    private Music music;
    private float songDuration;
    private float currentPosition;
    private URL mediaLink;
    private String type;
    private RodkastEpisode currentEpisode;
    private String fileName;

    private static final String MUSIC_ON_PREFERENCE = "music_on";
    private static final String EXTERNAL_STORAGE_PREFERENCE = "ext_storage"; //true = external; false = internal
    private static final String STORAGE_PATH_PREFERENCE = "storage_path";

    private static final String INTERNAL_ASSETS_PATH = "data/";


    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        return ourInstance;
    }

    public Music getMusic() {
        return music;
    }

    private Preferences getPreferences() {
        return Gdx.app.getPreferences(GlobalConstants.PREFERENCES_NAME);
    }

    public void setEpisode(RodkastEpisode episode) {
        if(episode != null && !episode.equals(currentEpisode)){
            this.mediaLink = episode.getMediaLink();
            this.songDuration = episode.getDuration();
            this.type = episode.getType();
            this.currentEpisode = episode;
            this.fileName = mediaLink.getFile();
        }
    }

    private boolean checkIfDownloaded(RodkastEpisode episode) {
        URL link = null;
        if(episode != null){
            link = episode.getMediaLink();
        }

        String localFileName = "";
        if(link != null){
            localFileName = link.getFile();
        }

        boolean isExternal = getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, false);
        return isExternal? Gdx.files.external(localFileName).exists() : Gdx.files.internal(localFileName).exists();
    }

    public float getSongDuration(){
        return songDuration;
    }

    public void playMusic() {
        System.out.println("Play Episode" + fileName);
        //music.play();
    }

    public void toggleMusic() {
        saveBoolean(MUSIC_ON_PREFERENCE, !getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true));
    }

    public void toggleStorage() {
        saveBoolean(EXTERNAL_STORAGE_PREFERENCE, !getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, true));
    }

    private void saveBoolean(String key, boolean value) {
        Preferences preferences = getPreferences();
        preferences.putBoolean(key, value);
        preferences.flush();
    }

    public void dispose() {
        Utils.disposeObjects(music, currentEpisode);
        mediaLink = null;
        type = null;
    }

    public void pauseMusic() {
        System.out.println("Pause Episode" + fileName);
        //music.pause();
    }

    public void dowloadEpisode(RodkastStageAdapter stage, RodkastEpisode episode) {
        setEpisode(episode);
        if(currentEpisode != null && stage != null){
            boolean isDownloaded = checkIfDownloaded(episode);
            System.out.println("Episode Downloaded clicked");

            if(isDownloaded){
                music = createNewMusic();
                System.out.println("Episode Downloaded");
            } else {
                System.out.println("Episode Not Downloaded");
                System.out.println("downloading " + currentEpisode.getTitle() + "...");
                beginDownload(stage);
            }
        }
    }

    private void beginDownload(RodkastStageAdapter stage) {
        //stage.addListener()
    }

    private Music createNewMusic() {
        boolean isExternal = getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, false);
        if(isExternal){
            return Gdx.audio.newMusic(Gdx.files.external(fileName));
        } else {
            return Gdx.audio.newMusic(Gdx.files.internal(fileName));
        }
    }
}