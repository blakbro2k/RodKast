package net.asg.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Queue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

/**
 * Created by eboateng on 10/19/2017.
 */

public class FileUtils {
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final int DEFAULT_FILE_BYTE_LENTHG = 1024;

    private static FileUtils _ourInstance = new FileUtils();
    private Queue<FileUtilObject> downloadQueue;
    private Map<String, Integer> fileNameIndex;
    public final int DEFAULT_DOWNLOAD_THRESHOLD = 3;
    private int congruentDownloads = 0;

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

    public int getDownloadQueueSize(){
        return downloadQueue.size;
    }

    public boolean isFileDownloaded(String fileName) throws GdxRuntimeException{
        return fileName != null && PreferencesUtil.getStoragePref()? Gdx.files.external(fileName).exists() : Gdx.files.internal(fileName).exists();
    }

    public void queueDownload(FileUtilObject fileUtilObject) throws GdxRuntimeException{
        if(fileUtilObject == null){
            return;
        }
        downloadQueue.addFirst(fileUtilObject);
    }

    public FileUtilObject removeDownload() throws GdxRuntimeException{
        if(downloadQueue .size == 0){
            return null;
        }
        return downloadQueue.removeFirst();
    }

    public void processNextDownload() throws GdxRuntimeException{
        FileUtilObject download = removeDownload();
        if(download == null){
            return;
        }

        if(congruentDownloads > DEFAULT_DOWNLOAD_THRESHOLD){
            throw new GdxRuntimeException("Max congruent downloads reached");
        }

        increaseCongruentDownloads();
        URL link = download.getUrl();

        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setTimeOut(GlobalConstants.HTTP_REQUEST_TIMEOUT);
        request.setUrl(link.toString());

        RodkastHttpListener requestTest = new RodkastHttpListener(download);
        System.out.println("download: " + requestTest);
        // Send the request, listen for the response
        Gdx.net.sendHttpRequest(request, new RodkastHttpListener(download));
    }

    private int getCongruentDownloads(){
        return congruentDownloads;
    }

    private void increaseCongruentDownloads(){
        congruentDownloads++;
    }

    private void decreaseCongruentDownloads(){
        if(congruentDownloads < 1){
            congruentDownloads = 0;
        } else {
            congruentDownloads--;
        }
    }

    private class RodkastHttpListener implements Net.HttpResponseListener {
        private final FileUtilObject obj;

        public RodkastHttpListener(FileUtilObject obj){
            this.obj = obj;
        }

        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse){
            // Determine how much we have to download
            long length = Long.parseLong(httpResponse.getHeader(CONTENT_LENGTH));

            // We're going to download the file to external storage, create the streams
            InputStream is = httpResponse.getResultAsStream();
            //OutputStream os = Gdx.files.external(audioIndexObject.getFilePath()).write(false);

            byte[] bytes = new byte[DEFAULT_FILE_BYTE_LENTHG];
            int count;
            long read = 0;

            try{
                while ((count = is.read(bytes, 0, bytes.length)) != -1) {
                    //os.write(bytes, 0, count);

                    obj.updateBytes(bytes);
                    read += count;

                    // Update the UI with the download progress
                    // System.out.println("float: " + ((double) read / (double) length));
                    final int progress = ((int) (((double) read / (double) length) * 100));
                    //final String progressString = progress == 100 ? "Click to download" : progress + "%";

                    //audioIndexObject.updateProgres(progress);

                    if(progress == 100){
                        System.out.println(obj.getFileName() + obj);
                        // audioIndexObject.setTotalFileLength(length);
                    }
                }
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            } finally {
                decreaseCongruentDownloads();
                Utils.closeInputStream(is);
                //Utils.closeOutputStream(os);
            }
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

    public static class FileUtilObject implements Json.Serializable{
        private final int MAX_OBJECT_BYTES = 1024;
        private String fileName;
        private URL url;
        private Array<byte[]> fileBytes;
        //private byte[] bytes;

        public FileUtilObject(String fileName, URL url){
            this.fileName = fileName;
            this.url = url;
            fileBytes = new Array<>();
        }

        public void updateBytes(byte[] inBytes){
            if(inBytes.length > MAX_OBJECT_BYTES){
                throw new GdxRuntimeException("Cannot update file, bytes exceed maximum.");
            }
            System.out.println("bytes: " + inBytes);

            fileBytes.add(inBytes);
        }

        public URL getUrl(){
            return url;
        }

        public String getFileName(){
            return fileName;
        }

        @Override
        public void write(Json json) {

        }

        @Override
        public void read(Json json, JsonValue jsonData) {

        }
    }
}
