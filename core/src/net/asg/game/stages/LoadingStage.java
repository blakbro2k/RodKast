package net.asg.game.stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.ui.RadialProgressBar;
import net.asg.game.utils.ErrorUtils;
import net.asg.game.utils.MessageCatalog;

/**
 * Created by eboateng on 8/9/2017.
 */

public class LoadingStage extends RodkastStageAdapter{
    private ProgressBar loadingBar;

    public LoadingStage(RodKastApplication app) {
        super(app);

        try{
            manager.loadPostAssets();

            Table main = new Table();
            main.setFillParent(true);
            main.center();

            setUpLoadingBar(main);

            addActor(main);
        } catch (Exception e){
            ErrorUtils.getInstance().showErrorPopup(e);
        }

    }

    private void setUpLoadingBar(Table main) throws Exception{
        Label loadingLabel = new Label(MessageCatalog.LOADING_MSG, defaultScreenLabelStyle);

        loadingBar = new ProgressBar(0, 1, 0.1f, false, defaultSkin);

        main.add(loadingLabel);
        main.add(loadingBar);
    }

    public void update(float value){
        if(loadingBar != null){
            loadingBar.setValue(value);
        }
    }
}
