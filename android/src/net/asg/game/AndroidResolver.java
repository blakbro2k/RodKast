package net.asg.game;

import com.badlogic.gdx.Gdx;

import net.asg.game.menu.ExitDialog;
import net.asg.game.stages.HomeStage;
import net.asg.game.utils.ActionResolver;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class AndroidResolver implements ActionResolver {
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
