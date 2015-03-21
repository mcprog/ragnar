package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;

/**
 * Created by Michael on 3/20/2015.
 */
public class QuitApp extends TextButton implements  ICustomButton {

    public QuitApp() {
        super("Quit", GuiStyles.largeButtonStyle);

        addFunctionality();
    }

    @Override
    public void addFunctionality() {
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }


}
