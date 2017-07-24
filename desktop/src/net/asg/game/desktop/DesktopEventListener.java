package net.asg.game.desktop;

import net.asg.game.stages.HomeStage;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Util;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class DesktopEventListener implements GameEventListener {
    @Override
    public void backButton(HomeStage stage) {
        Util.backButtonUtil(stage);
    }

    @Override
    public void showBannerAd() {
        System.out.println("showBannerAd called");
    }

    @Override
    public void hideBannerAd() {
        System.out.println("hideBannerAd called");
    }

    @Override
    public void showOrLoadInterstital() {
        System.out.println("showOrLoadInterstital called");
    }

    @Override
    public void share() {
        System.out.println("share called");
    }

    @Override
    public void getDebugSetting() {
        System.out.println("getDebugSetting called");
    }
}