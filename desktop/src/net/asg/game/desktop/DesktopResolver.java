package net.asg.game.desktop;

import com.badlogic.gdx.Gdx;

import net.asg.game.menu.ExitDialog;
import net.asg.game.stages.HomeStage;
import net.asg.game.utils.ActionResolver;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class DesktopResolver implements ActionResolver {
    @Override
    public void backButton(HomeStage stage) {
        ExitDialog exitDialog = stage.getExitDialog();

        if(exitDialog.isVisible()){
            if(exitDialog.getCount() > 0){
                Gdx.app.exit();
            }
        }
        exitDialog.show(stage);
        exitDialog.increment();
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
