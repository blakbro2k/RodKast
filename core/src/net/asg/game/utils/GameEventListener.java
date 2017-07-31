package net.asg.game.utils;

import net.asg.game.RodKastApplication;
import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public interface GameEventListener {
    /**
     * Interface for back button
     */
    void backButton(RodKastApplication app);

    void showBannerAd();

    void hideBannerAd();

    void showOrLoadInterstital();

    void share();
}
