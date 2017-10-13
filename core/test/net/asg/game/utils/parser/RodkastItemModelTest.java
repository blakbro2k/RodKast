package net.asg.game.utils.parser;

import com.badlogic.gdx.utils.XmlReader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eboateng on 10/13/2017.
 */
public class RodkastItemModelTest {
    XmlReader.Element parent;
    private final String DESCRIPTION = "description";
    private final String TITLE = "title";
    private final String GOOD_LINK = "https://example.com";
    private final String BAD_LINK = "https:/example.com";

    @Before
    public void setUp() throws Exception {
        parent = new XmlReader.Element("channel",null);
        XmlReader.Element title = new XmlReader.Element("title",parent);
        XmlReader.Element link = new XmlReader.Element("link",parent);
        XmlReader.Element description = new XmlReader.Element("description",parent);

        link.setText(GOOD_LINK);
        title.setText(TITLE);
        description.setText(DESCRIPTION);

        parent.addChild(title);
        parent.addChild(link);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getRssUrl() throws Exception {

    }

    @Test
    public void getRssPubDate() throws Exception {

    }

    @Test
    public void getLastBuildDate() throws Exception {

    }

    @Test
    public void getRssTitle() throws Exception {

    }

    @Test
    public void getRssGUID() throws Exception {

    }

    @Test
    public void getRssDescription() throws Exception {
        //Description is missing
        try{
            RodkastItemModel.getRssDescription(parent);
        } catch(Exception e){
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }

        //Description is null
        XmlReader.Element description = new XmlReader.Element(DESCRIPTION,parent);
        parent.addChild(description);
        Assert.assertNull(DESCRIPTION,RodkastItemModel.getRssDescription(parent));
        parent.removeChild(description);

        //Description is set
        description.setText(DESCRIPTION);
        parent.addChild(description);
        Assert.assertEquals(DESCRIPTION,RodkastItemModel.getRssDescription(parent));
    }

    @Test
    public void getRssCategory() throws Exception {

    }

    @Test
    public void getRssLanguage() throws Exception {

    }

    @Test
    public void getRssEnclosure() throws Exception {

    }

    @Test
    public void getCompleteEpisodesList() throws Exception {

    }

    @Test
    public void getRssImages() throws Exception {

    }
}