package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Queue;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by eboateng on 10/19/2017.
 */

public class FileUtils {
    private static FileUtils _ourInstance = new FileUtils();
    private Queue<FileUtilObject> downloadQueue;
    private Map<String, Integer> fileNameIndex;
    public final int DEFAULT_DOWNLOAD_THRESHOLD = 3;

    private FileUtils(){
        initialize();
    }

    private void initialize(){
        if(downloadQueue == null){
            downloadQueue = new Queue<>();
        }
    }

    public static FileUtils getInstance() {
        return _ourInstance;
    }

    public boolean isFileDownloaded(String fileName) throws GdxRuntimeException{
        return fileName != null && PreferencesUtil.getStoragePref()? Gdx.files.external(fileName).exists() : Gdx.files.internal(fileName).exists();
    }

    public void queueDownload(FileUtilObject fileUtilObject) throws GdxRuntimeException{
        if(fileUtilObject == null){
            return;
        }

        //if(downloadQueue.size >= DEFAULT_DOWNLOAD_THRESHOLD){
        //}
    }

    public FileUtilObject removeQueuedDownload() throws GdxRuntimeException{
       /* if(activeDownloadQueue.size < 1 && passiveDownloadQueue.size < 1){
            return null;
        }
        FileUtilObject fileUobj = activeDownloadQueue.first();

        if(activeDownloadQueue.size < DEFAULT_DOWNLOAD_THRESHOLD && passiveDownloadQueue.size > 0){
            activeDownloadQueue.addFirst(passiveDownloadQueue.first());
        }*/
        return null;//fileUobj;
    }

    public void processNextDownload(){

    }

    private class RodkastHttpListener implements Net.HttpResponseListener{
        private static final String CONTENT_LENGTH = "Content-Length";

        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            // Determine how much we have to download
            long length = Long.parseLong(httpResponse.getHeader(CONTENT_LENGTH));
            //AudioUtils.EpisodeAudio audioIndexObject = AudioUtils.getInstance().getAudioFromIndex(fileName);
            //audioIndexObject.updateAudioLength(length);
            //audioIndexObject.setFilePath(getFullFilePath(fileName));

            // We're going to download the file to external storage, create the streams
            InputStream is = httpResponse.getResultAsStream();
            //OutputStream os = Gdx.files.external(audioIndexObject.getFilePath()).write(false);

            byte[] bytes = new byte[1024];
            int count;
            long read = 0;
            /*try {
                // Keep reading bytes and storing them until there are no more.
                while ((count = is.read(bytes, 0, bytes.length)) != -1) {

                    os.write(bytes, 0, count);
                    read += count;

                    // Update the UI with the download progress
                    // System.out.println("float: " + ((double) read / (double) length));
                    final int progress = ((int) (((double) read / (double) length) * 100));
                    final String progressString = progress == 100 ? "Click to download" : progress + "%";

                    //audioIndexObject.updateProgres(progress);

                    if(progress == 100){
                       // audioIndexObject.setTotalFileLength(length);
                    }*/
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

    private class FileUtilObject implements Json.Serializable{
        private final int MAX_OBJECT_BYTES = 1024;
        private String fileName;
        private URL url;
        private Array<byte[]> fileBytes;
        private byte[] bytes;

        public FileUtilObject(String fileName, URL url){
            this.fileName = fileName;
            this.url = url;
            fileBytes = new Array<>();
        }

        public void updateBytes(byte[] inBytes){
            if(bytes.length > MAX_OBJECT_BYTES){
                throw new GdxRuntimeException("Cannot update file, bytes exceed maximum.");
            }
            fileBytes.add(inBytes);
        }

        @Override
        public void write(Json json) {

        }

        @Override
        public void read(Json json, JsonValue jsonData) {

        }
    }
}
