package net.asg.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.ui.RadialProgressBar;
import net.asg.game.utils.MessageCatalog;

/**
 * Created by eboateng on 8/9/2017.
 */

public class LoadingStage extends RodkastStageAdapter{
    private ProgressBar loadingBar;
    public RadialProgressBar cooldownTimerBlue;

    public LoadingStage(RodKastApplication app) {
        super(app);

        manager.loadPostAssets();

        Table main = new Table();
        main.setFillParent(true);
        main.center();
        //main.debug();

        setUpLoadingBar(main);

        addActor(main);
    }

    private void setUpLoadingBar(Table main){
        Label loadingLabel = new Label(MessageCatalog.LOADING_MSG, defaultScreenLabelStyle);
        Label RadialLabel = new Label(MessageCatalog.LOADING_MSG, defaultScreenLabelStyle);

        loadingBar = new ProgressBar(0, 1, 0.1f, false, defaultSkin);

        cooldownTimerBlue = new RadialProgressBar(true);
        cooldownTimerBlue.setSize(100, 100);
        //cooldownTimerBlue.setPosition(100, 100);
        cooldownTimerBlue.setColor(Color.BLUE);

        main.add(loadingLabel);
        main.add(loadingBar);
        System.out.println(cooldownTimerBlue);
        main.row();
        main.add(RadialLabel);
        main.add(cooldownTimerBlue);
    }

    public void update(float value){
        if(loadingBar != null){
            loadingBar.setValue(value);
        }

        if(cooldownTimerBlue != null){
            cooldownTimerBlue.setValue(value);
        }
    }
}
