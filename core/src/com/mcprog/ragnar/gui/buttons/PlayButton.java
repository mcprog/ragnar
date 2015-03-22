package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.handlers.SetScreenListener;

public class PlayButton extends TextButton implements IScreenSetter {

    private boolean pauseState;
	
	public PlayButton(TextButtonStyle style) {
		super("Play", style);
        setScreenToSet();
	}

	
	public PlayButton () {
		this(GuiStyles.largeButtonStyle);
	}

    @Override
    public void setScreenToSet() {
        addListener(new SetScreenListener(Ragnar.GAME_ID));
    }
}
