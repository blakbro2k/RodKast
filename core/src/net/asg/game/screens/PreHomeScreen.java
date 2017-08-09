package net.asg.game.screens;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.PreHomeStage;

/**
 * Created by eboateng on 8/9/2017.
 */

public class PreHomeScreen extends RodKastScreenAdapter {
        public PreHomeScreen(RodKastApplication app) {
            super();

            if(this.app == null) {
                this.app = app;
            }

            stage = new PreHomeStage(app);
            app.gotoHomeScreen();
        }

    @Override
    public void gotoScreen() {
        app.gotoPreHomeScreen();
    }
}
