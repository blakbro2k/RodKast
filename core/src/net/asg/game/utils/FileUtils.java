package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Queue;

import java.net.URL;

/**
 * Created by eboateng on 10/19/2017.
 */

public class FileUtils {
    private Queue downloadQueue;

    public static boolean isFileDownloaded(String fileName) throws GdxRuntimeException{
        return fileName != null && PreferencesUtil.getStoragePref()? Gdx.files.external(fileName).exists() : Gdx.files.internal(fileName).exists();
    }

    public static void queueDownload(String fileName, URL url) throws GdxRuntimeException{

    }

    public static void removeQueuedDownload(String fileName) throws GdxRuntimeException{
        throw new GdxRuntimeException("Download not found in queue.");
    }

    private class RodkastHttpListener implements Net.HttpResponseListener{

        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {

        }

        @Override
        public void failed(Throwable t) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run () {
                    //ErrorUtils.getInstance().showErrorPopup(t);
                    //throw new GdxRuntimeException(t);
                    //Gdx.app.log("Error",t.getMessage());
                }
            });
        }

        @Override
        public void cancelled() {
            throw new UnsupportedOperationException("Cancel feature is Unsupported");
        }
    }
}
