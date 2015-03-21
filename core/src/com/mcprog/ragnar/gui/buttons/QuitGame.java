package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;

/**
 * Created by Michael on 3/20/2015.
 */
public class QuitGame extends TextButton implements ICustomButton {


    public QuitGame(TextButtonStyle style) {
        super("Quit", style);
        addFunctionality();
    }

    @Override
    public void addFunctionality() {
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            Ragnar.gameInstance.setScreen(Ragnar.gameInstance.menuScreen);
            }
        });
    }
}
