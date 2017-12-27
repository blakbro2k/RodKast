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
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by eboateng on 10/19/2017.
 */

public class FileUtils {
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final int DEFAULT_FILE_BYTE_LENGTH = 1024;

    private static FileUtils _ourInstance = new FileUtils();
    private Queue<FileUtilObject> downloadQueue;
    public final int DEFAULT_DOWNLOAD_THRESHOLD = 3;
    private int byteLength = DEFAULT_FILE_BYTE_LENGTH;
    //private int concurrentDownloads = 0;

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

    public void queueDownload(FileUtilObject fileUtilObject){
        if(fileUtilObject == null){
            return;
        }
        downloadQueue.addFirst(fileUtilObject);
    }

    public FileUtilObject removeDownload(){
        if(downloadQueue.size == 0){
            return null;
        }
        return downloadQueue.removeFirst();
    }

    public void processNextDownload() throws GdxRuntimeException{
        //TODO: Check for internet
        //TODO: Check if is downloaded started
        //TODO: if finished remove download
        //FileUtilObject download = removeDownload();
        if(downloadQueue.size < 1){
            return;
        }

        FileUtilObject download = downloadQueue.get(0);

        if(download == null){
            return;
        }

        System.out.println("processNextDownload():getConcurrentDownloads()=" + getConcurrentDownloads());
        if(getConcurrentDownloads() <= DEFAULT_DOWNLOAD_THRESHOLD){
            System.out.println("processNextDownload(): processing=" + download);
            URL link = download.getUrl();

            Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
            request.setTimeOut(GlobalConstants.HTTP_REQUEST_TIMEOUT);
            request.setUrl(link.toString());

            System.out.println("link=" + link);
            // Send the request, listen for the response
            Gdx.net.sendHttpRequest(request, new RodkastHttpListener(download));
        } else {
            throw new GdxRuntimeException("The maximum number of concurrent downloads has been reached at " + getConcurrentDownloads());
        }
    }

    public int getConcurrentDownloads(){
        return downloadQueue.size;
    }

    public void clearDownloadQueue() {
        downloadQueue.clear();
    }

    private class RodkastHttpListener implements Net.HttpResponseListener {
        private final FileUtilObject obj;

        public RodkastHttpListener(FileUtilObject obj){
            this.obj = obj;
        }

        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse){
            System.out.println("Downloading " + obj.getFileName());

            // Determine how much we have to download
            long length = Long.parseLong(httpResponse.getHeader(CONTENT_LENGTH));
            obj.setFileLength(length);

            // We're going to download the file to external storage, create the streams
            InputStream is = httpResponse.getResultAsStream();
            //OutputStream os = Gdx.files.external(audioIndexObject.getFilePath()).write(false);

            byte[] bytes = new byte[byteLength];
            int count;
            //long read = 0;

            try{
                while ((count = is.read(bytes, 0, bytes.length)) != -1) {
                    //os.write(bytes, 0, count);
                    obj.updateBytes(bytes, count);
                    System.out.println("received " + bytes + " bytes for file: " + obj.getFileName());

                    if(obj.getProgress() == 100){
                        System.out.println("handleHttpResponse(): " + obj.getFileName() + obj);
                    }
                }
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            } finally {
                Utils.closeInputStream(is);
                //Utils.closeOutputStream(os);
            }
        }

        @Override
        public void failed(Throwable t) {//TODO:
            /*Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run () {
                    //ErrorUtils.getInstance().showErrorPopup(t);
                    //throw new GdxRuntimeException(t);
                    //Gdx.app.log("Error",t.getMessage());
                }
            });*/
        }

        @Override
        public void cancelled() {
            throw new UnsupportedOperationException("Cancel feature is Unsupported");
        }
    }

    public static abstract class FileUtilObject implements Json.Serializable{
        private final int MAX_OBJECT_BYTES = 1024;
        private String fileName;
        private URL url;
        private Array<byte[]> fileBytes;
        private long fileLength = 0;
        private long bytesRead = 0;

        public FileUtilObject(String fileName, URL url){
            if (fileName == null){
                throw new GdxRuntimeException("Unable to create new FileUtilObject, fileName is null");
            }

            if (url == null){
                throw new GdxRuntimeException("Unable to create new FileUtilObject, url is null");
            }

            this.fileName = fileName;
            this.url = url;
            fileBytes = new Array<>();
        }

        public void updateBytes(byte[] inBytes, int count){
            if (inBytes.length <= MAX_OBJECT_BYTES) {
                System.out.println("bytes: " + Arrays.toString(inBytes));

                bytesRead += count;
                fileBytes.add(inBytes);
            } else {
                throw new GdxRuntimeException("Cannot update file, bytes exceed maximum.");
            }
        }

        public URL getUrl(){
            return url;
        }

        public String getFileName(){
            return fileName;
        }

        @Override
        public abstract void write(Json json);

        @Override
        public abstract void read(Json json, JsonValue jsonData);

        public Iterator<byte[]> iterator(){
            return fileBytes.iterator();
        }

        public String toString(){
            return fileName + ": " + fileBytes;
        }

        public void setFileLength(long fileLength) {
            this.fileLength = fileLength;
        }

        public long getFileLength() {
            return fileLength;
        }

        public int getProgress(){
            return ((int) (((double) bytesRead / (double) fileLength) * 100));
        }
    }
}
