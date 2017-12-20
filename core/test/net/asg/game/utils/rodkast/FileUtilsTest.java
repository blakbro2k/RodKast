package net.asg.game.utils.rodkast;

import net.asg.game.utils.FileUtils;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Blakbro2k on 12/18/2017.
 */
public class FileUtilsTest {
    @Test
    public void getInstance() throws Exception {
        Assert.assertNotNull(FileUtils.getInstance());
    }

    @Test
    public void isFileDownloaded() throws Exception {
        throw new Exception("Test not initialized");
    }

    @Test
    public void queueDownload() throws Exception {
        String fileName = "TestFileObject1";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new FileUtils.FileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);

        Assert.assertEquals(1,FileUtils.getInstance().getDownloadQueueSize(),0);
        FileUtils.getInstance().removeDownload();
        Assert.assertEquals(0,FileUtils.getInstance().getDownloadQueueSize(),0);
    }

    @Test
    public void removeDownload() throws Exception {
        String fileName = "TestFileObject2";
        URL url = new URL("http://www.examle.com");

        FileUtils.FileUtilObject obj = new FileUtils.FileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);

        //Assert.assertEquals(1,FileUtils.getInstance().getDownloadQueueSize(),0);
        FileUtils.FileUtilObject expected = FileUtils.getInstance().removeDownload();
        Assert.assertEquals(obj, expected);
        Assert.assertEquals(0,FileUtils.getInstance().getDownloadQueueSize(),0);
    }

    @Test
    public void processNextDownload() throws Exception {
        throw new Exception("Test not initialized");
        /*
        String fileName = "TestFileObject3";
        URL url = new URL("http://speedtest-ny.turnkeyinternet.net/100mb.bin");

        FileUtils.FileUtilObject obj = new FileUtils.FileUtilObject(fileName, url);
        FileUtils.getInstance().queueDownload(obj);
        //FileUtils.getInstance().processNextDownload();

        throw new Exception("Test not initialized");*/
    }
}