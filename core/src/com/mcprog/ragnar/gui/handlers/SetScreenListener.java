package com.mcprog.ragnar.gui.handlers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.screens.ScreenDrawable;

/**
 * Created by Michael on 3/21/2015.
 */
public class SetScreenListener extends ClickListener {

    private Ragnar game;
    private ScreenDrawable screen;

    public SetScreenListener (Ragnar game, ScreenDrawable screen) {
        this.game = game;
        this.screen = screen;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Ragnar.gameInstance.setScreen(screen);
    }
}
