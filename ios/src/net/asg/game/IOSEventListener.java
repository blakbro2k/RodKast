package net.asg.game;

import net.asg.game.stages.HomeStage;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Util;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class IOSEventListener implements GameEventListener {
    @Override
    public void backButton(HomeStage stage) {
        Util.backButtonUtil(stage);
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
