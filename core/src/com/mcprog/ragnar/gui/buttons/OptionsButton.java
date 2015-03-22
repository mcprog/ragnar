package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.handlers.SetScreenListener;

/**
 * Created by Michael on 3/21/2015.
 */
public class OptionsButton extends TextButton implements IScreenSetter {
    public OptionsButton(TextButtonStyle style) {
        super("Options", style);
        setScreenToSet();
    }

    public OptionsButton () {
        this(GuiStyles.largeButtonStyle);
    }

    @Override
    public void setScreenToSet() {
        addListener(new SetScreenListener(Ragnar.OPTIONS_ID));
    }
}
