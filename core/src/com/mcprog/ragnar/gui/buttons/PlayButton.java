package com.mcprog.ragnar.gui.buttons;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;

public class PlayButton extends TextButton implements ICustomButton {

    private boolean pauseState;
	
	public PlayButton(TextButtonStyle style) {
		super("Play", style);

		addFunctionality();
	}

	
	public PlayButton () {
		this(GuiStyles.largeButtonStyle);
	}

	public void addFunctionality() {
		addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
		
	}

}
