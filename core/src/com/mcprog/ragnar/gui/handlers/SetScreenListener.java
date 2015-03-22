package com.mcprog.ragnar.gui.handlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;

/**
 * Created by Michael on 3/21/2015.
 */
public class SetScreenListener extends ClickListener {

    private int screenID;

    public SetScreenListener (int id) {
        this.screenID = id;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Ragnar.gameInstance.setToScreen(screenID);
    }
}
