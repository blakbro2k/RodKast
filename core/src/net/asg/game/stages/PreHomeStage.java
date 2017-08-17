package net.asg.game.stages;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.asg.game.RodKastApplication;
import net.asg.game.utils.GlobalConstants;

/**
 * Created by eboateng on 8/9/2017.
 */

public class PreHomeStage extends RodkastStageAdapter {
    public PreHomeStage(RodKastApplication app){
        super(app);

        loadAssets();

        Table main = new Table();
        main.setWidth(GlobalConstants.VIEWPORT_WIDTH);
        main.setHeight(GlobalConstants.VIEWPORT_HEIGHT);

        setUpStageTitleWindow(main);
        addActor(main);
    }

    private void setUpStageTitleWindow(Table main){
        Label nameLabel = new Label(GlobalConstants.GAME_TITLE, titleScreenLabelStyle);
        main.add(nameLabel).height(BANNER_SIZE).expandX();
    }
}
