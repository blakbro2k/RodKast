package net.asg.game.utils.parser;

import java.util.Map;

/**
 * Created by obaro on 27/11/2016.
 */

public abstract class RssFeedModel {
    public final String RSS_CHANNEL = "channel";
    public final String RSS_ITEM = "item";
    public final String RSS_IMAGE = "image";

    public RssFeedModel(Map<String, Object> attributes) {
        initialize(attributes);
    }

    public abstract void initialize(Map<String, Object> attributes);
}
