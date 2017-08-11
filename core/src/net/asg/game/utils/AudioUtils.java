package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

import net.asg.game.utils.parser.RodkastEpisode;

import java.net.URL;

public class AudioUtils {
    private static AudioUtils ourInstance = new AudioUtils();
    private static Music music;
    private float songDuration;
    private float currentPosition;
    private URL mediaLink;
    private String type;
    private RodkastEpisode currentEpisode;
    private boolean isDownloaded;

    private static final String MUSIC_ON_PREFERENCE = "music_on";
    private static final String EXTERNAL_STORAGE_PREFERENCE = "ext_storage"; //true = external; false = internal
    private static final String STORAGE_PATH_PREFERENCE = "storage_path";


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

            if(mediaLink != null){
                isDownloaded = checkIfDownloaded(mediaLink.getFile());
            }

            if(isDownloaded){
                System.out.println("Episode Downloaded");
            } else {
                System.out.println("Episode Not Downloaded");
            }
        }
    }

    private boolean checkIfDownloaded(String fileName) {
        boolean isExternal = getPreferences().getBoolean(EXTERNAL_STORAGE_PREFERENCE, false);
        return isExternal? Gdx.files.external(fileName).exists() : Gdx.files.internal(fileName).exists();
    }

    public float getSongDuration(){
        return songDuration;
    }

    public void playMusic() {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        if (musicOn) {
            music.play();
        }
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

    public static void dispose() {
        music.dispose();
    }

    public void pauseMusic() {
        music.pause();
    }
/*
    public String getSoundRegionName() {
        boolean soundOn = getPreferences().getBoolean(SOUND_ON_PREFERENCE, true);
        return soundOn ? Constants.SOUND_ON_REGION_NAME : Constants.SOUND_OFF_REGION_NAME;
    }

    public String getMusicRegionName() {
        boolean musicOn = getPreferences().getBoolean(MUSIC_ON_PREFERENCE, true);
        return musicOn ? Constants.MUSIC_ON_REGION_NAME : Constants.MUSIC_OFF_REGION_NAME;
    }*/
}