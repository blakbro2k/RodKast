package net.asg.game.screens;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/21/2017.
 */

public class HomeScreen extends RodKastScreenAdapter {
    public HomeScreen(RodKastApplication app) {
        super();

        if(this.app == null) {
            this.app = app;
        }

        stage = new HomeStage(app);
    }

    @Override
    public void gotoScreen() {
        app.gotoHomeScreen();
    }
}
