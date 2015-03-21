package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.handlers.SetScreenListener;
import com.mcprog.ragnar.screens.ScreenDrawable;

public class MenuButton extends TextButton implements IScreenSetter {

	public MenuButton(TextButtonStyle style) {
		super("Menu", style);
        setScreenToSet(Ragnar.gameInstance, Ragnar.gameInstance.menuScreen);
	}
	
	public MenuButton() {
		this(GuiStyles.largeButtonStyle);
	}

    @Override
    public void setScreenToSet(Ragnar game, ScreenDrawable screen) {
        addListener(new SetScreenListener(game, screen));
    }
}
