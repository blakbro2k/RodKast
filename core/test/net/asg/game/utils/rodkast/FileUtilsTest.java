package net.asg.game.utils.rodkast;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import net.asg.game.utils.FileUtils;
import net.asg.game.utils.parser.gdxtests.GdxTestRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;
import java.util.Iterator;

/**
 * Created by Blakbro2k on 12/18/2017.
 */
@RunWith(GdxTestRunner.class)
public class FileUtilsTest {
    @Test
    public void getInstance() throws Exception {
        Assert.assertNotNull(FileUtils.getInstance());
    }

    @Test
    public void unhappyInitialization() throws Exception {
        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        //is missing
        try{
            FileUtils.FileUtilObject obj = new TestFileUtilObject(null, url);
        } catch(Exception e){
            System.out.println("unhappyInitialization(): Caught: " + e);
            Assert.assertTrue(e instanceof GdxRuntimeException);
        }

        //is missing
        try{
            FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, null);
        } catch(Exception e){
            System.out.println("unhappyInitialization(): Caught: " + e);
            Assert.assertTrue(e instanceof GdxRuntimeException);
        }
    }

    @Test
    public void queueDownload() throws Exception {
        FileUtils.getInstance().clearDownloadQueue();

        String fileName = "TestFileObject1";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);

        Assert.assertEquals(1,FileUtils.getInstance().getConcurrentDownloads(),0);
        FileUtils.getInstance().removeDownload();
        Assert.assertEquals(0,FileUtils.getInstance().getConcurrentDownloads(),0);
    }

    @Test
    public void removeDownload() throws Exception {
        FileUtils.getInstance().clearDownloadQueue();

        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);

        //Assert.assertEquals(1,FileUtils.getInstance().getDownloadQueueSize(),0);
        FileUtils.FileUtilObject expected = FileUtils.getInstance().removeDownload();
        Assert.assertEquals(obj, expected);
        Assert.assertEquals(0,FileUtils.getInstance().getConcurrentDownloads(),0);
    }

    @Test
    public void processNextDownload() throws Exception {
        FileUtils.getInstance().clearDownloadQueue();

        //throw new Exception("Test not initialized");

        String fileName = "TestFileObject3";
        URL url = new URL("http://speedtest-ny.turnkeyinternet.net/100mb.bin");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);

        FileUtils.getInstance().queueDownload(obj);
        FileUtils.getInstance().queueDownload(obj);
        FileUtils.getInstance().queueDownload(obj);

        FileUtils.getInstance().processNextDownload();
        FileUtils.getInstance().queueDownload(obj);

        FileUtils.getInstance().processNextDownload();
        FileUtils.getInstance().processNextDownload();
        FileUtils.getInstance().processNextDownload();


    }

    @Test
    public void getConcurrentDownloads() throws Exception {
        FileUtils.getInstance().clearDownloadQueue();

        String fileName = "TestFileObject3";
        URL url = new URL("http://speedtest-ny.turnkeyinternet.net/100mb.bin");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);
        FileUtils.getInstance().queueDownload(obj);
        Assert.assertEquals(2, FileUtils.getInstance().getConcurrentDownloads(), 0);
        FileUtils.getInstance().queueDownload(obj);
        FileUtils.getInstance().queueDownload(obj);
        Assert.assertEquals(4, FileUtils.getInstance().getConcurrentDownloads(), 0);
        FileUtils.getInstance().clearDownloadQueue();
        Assert.assertEquals(0, FileUtils.getInstance().getConcurrentDownloads(), 0);
    }

    @Test
    public void updateBytes() throws Exception {
        byte[] b1 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b2 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b3 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b4 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b5 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b6 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b7 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};
        byte[] b8 = {0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 79, 83, 88, 89, 90, 95,
                99, 103, 104, 108, 109, 110, 111, 112, 115};

        Array<byte[]> expectedArray = new Array<>();

        expectedArray.add(b1);
        expectedArray.add(b2);
        expectedArray.add(b3);
        expectedArray.add(b4);
        expectedArray.add(b5);
        expectedArray.add(b6);
        expectedArray.add(b7);
        expectedArray.add(b8);

        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);
        obj.setFileLength((long) expectedArray.size);

        Iterator<byte[]> iter = expectedArray.iterator();

        int count = 0;
        if(iter != null){
            while(iter.hasNext()){

                byte[] read = iter.next();
                obj.updateBytes(read, count);
                count += read.length;

                System.out.println("Progress: " + obj.getProgress());
            }
        }

        Assert.assertEquals(expectedArray.iterator().getClass(), obj.iterator().getClass());
    }

    @Test
    public void getUrl() throws Exception {
        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);

        Assert.assertEquals(url, obj.getUrl());
    }

    @Test
    public void testFileLength() throws Exception {
        long expectedLength = 1249L;

        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new TestFileUtilObject(fileName, url);
        obj.setFileLength(expectedLength);

        Assert.assertEquals(expectedLength, obj.getFileLength());
    }

    @Test
    public void getProgress() throws Exception {
        throw new Exception("Test not initialized");
    }

    private class TestFileUtilObject extends FileUtils.FileUtilObject {

        public TestFileUtilObject(String fileName, URL url) {
            super(fileName, url);
        }

        @Override
        public void write(Json json) {
/*
        private String fileName;
        private URL url;
        private Array<byte[]> fileBytes;
        private long fileLength = 0;
        private long bytesRead = 0;
 */
        }

        @Override
        public void read(Json json, JsonValue jsonData) {
/*
        private String fileName;
        private URL url;
        private Array<byte[]> fileBytes;
        private long fileLength = 0;
        private long bytesRead = 0;
 */
        }
    }
}