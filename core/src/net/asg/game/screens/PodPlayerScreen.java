package net.asg.game.screens;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.PodPlayerStage;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class PodPlayerScreen extends RodKastScreenAdapter {
    public PodPlayerScreen(RodKastApplication app) {
        super();
        if(this.app == null) {
            this.app = app;
        }

        stage = new PodPlayerStage(app);
    }

    @Override
    public void gotoScreen() {
        app.gotoPodPlayerScreen();
    }
}
