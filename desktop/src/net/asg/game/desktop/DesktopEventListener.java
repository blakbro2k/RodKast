package net.asg.game.desktop;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.GameEventListener;
import net.asg.game.utils.Utils;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class DesktopEventListener implements GameEventListener {
    @Override
    public void backButton(RodKastApplication app) {
        Utils.backButton(app);
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
    public void appLog(String tag, String message) {
        System.out.println(tag + ": " + message);
    }
}
