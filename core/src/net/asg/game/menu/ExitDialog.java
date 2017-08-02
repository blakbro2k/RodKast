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
        super("", skin);

        //button("Yes", true);
       // button("No", false);
        text(title);
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
        if(isVisible()){
            hide();
        }
        count = 0;
        //reset();
    }

    public int getCount(){
        return count;
    }
}
