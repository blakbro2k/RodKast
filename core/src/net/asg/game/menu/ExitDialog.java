package net.asg.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Blakbro2k on 6/24/2017.
 */

public class ExitDialog extends Dialog {
    private int count;

    public ExitDialog(String title, Skin skin) {
        super(title, skin);

        //text("Do you really want to leave?");
        button("Yes", true);
        button("No", false);
        resetCount();
    }

    @Override
    protected void result(Object obj){
        if(obj instanceof Boolean){
            if((Boolean) obj){
                Gdx.app.exit();
            } else {
                resetCount();
            }
        }
    }

    public void increment(){
        count++;
    }

    public void resetCount(){
        count = 0;
    }

    public int getCount(){
        return count;
    }
}
