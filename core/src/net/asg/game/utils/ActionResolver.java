package net.asg.game.utils;

import net.asg.game.stages.HomeStage;

/**
 * Created by Blakbro2k on 6/25/2017.
 */

public interface ActionResolver {
        /**
         * Interface for back button
         */
        void backButton(HomeStage stage);

        void showBannerAd();

        void hideBannerAd();

        void showOrLoadInterstital();

        void share();

        void getDebugSetting();
}
