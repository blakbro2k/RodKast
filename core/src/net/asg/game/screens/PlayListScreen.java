package net.asg.game.screens;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.PlayListStage;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class PlayListScreen extends RodKastScreenAdapter{
    public PlayListScreen(RodKastApplication app) {
        super();

        if(this.app == null) {
            this.app = app;
        }

        stage = new PlayListStage(app);
    }

    @Override
    public void gotoScreen() {
        app.gotoPlayListScreen();
    }
}
