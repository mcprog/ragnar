package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.handlers.SetScreenListener;
import com.mcprog.ragnar.screens.OptionsScreen;
import com.mcprog.ragnar.screens.ScreenDrawable;

/**
 * Created by Michael on 3/21/2015.
 */
public class OptionsButton extends TextButton implements IScreenSetter {
    public OptionsButton(TextButtonStyle style) {
        super("Options", style);
        setScreenToSet(Ragnar.gameInstance, Ragnar.gameInstance.optionsScreen);
    }

    public OptionsButton () {
        this(GuiStyles.largeButtonStyle);
    }

    @Override
    public void setScreenToSet(Ragnar game, ScreenDrawable screen) {
        //addListener(new SetScreenListener(game, screen));
        System.out.println("Options screen set?");
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Ragnar.gameInstance.setScreen(Ragnar.gameInstance.optionsScreen);
            }
        });
    }
}
