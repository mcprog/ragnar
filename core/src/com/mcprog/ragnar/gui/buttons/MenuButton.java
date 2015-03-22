package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.handlers.SetScreenListener;

public class MenuButton extends TextButton implements IScreenSetter {

	public MenuButton(TextButtonStyle style) {
		super("Menu", style);
        setScreenToSet();
	}
	
	public MenuButton() {
		this(GuiStyles.largeButtonStyle);
	}

    @Override
    public void setScreenToSet() {
        addListener(new SetScreenListener(Ragnar.MENU_ID));
    }
}
