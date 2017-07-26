package net.asg.game;

import net.asg.game.stages.HomeStage;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class IOSEventListener implements GameEventListener {
    @Override
    public void backButton(RodKastApplication app) {
        Utils.backButtonUtil(app);
    }

    @Override
    public void showBannerAd() {

    }

    @Override
    public void hideBannerAd() {

    }

    @Override
    public void showOrLoadInterstital() {

    }

    @Override
    public void share() {

    }

    @Override
    public void getDebugSetting() {

    }
}
