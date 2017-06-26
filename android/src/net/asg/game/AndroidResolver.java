package net.asg.game;

import com.badlogic.gdx.Gdx;

import net.asg.game.menu.ExitDialog;
import net.asg.game.stages.HomeStage;
import net.asg.game.utils.ActionResolver;
import net.asg.game.utils.Util;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public class AndroidResolver implements ActionResolver {
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
