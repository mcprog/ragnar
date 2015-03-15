package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;

public class PauseTable extends Table {

	private TextButton pauseResume;
	
	private boolean isPaused;
	
	public PauseTable() {
		GuiStyles.init();
		pauseResume = new TextButton("Pause", GuiStyles.largeButtonStyleLight);
		
		add(pauseResume);
		
		pauseResume.addListener(new ClickListener () {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (isPaused) {
					resume();
					
				} else {
					pause();
					
				}
			}
		});
	}
	
	public void textToPause () {
		pauseResume.setText("pause");
	}
	
	public void textToResume () {
		pauseResume.setText("resume");
	}
	
	public void pause () {
		isPaused = true;
	}
	
	public void resume () {
		isPaused = false;
	}
	
	public boolean isPaused () {
		return isPaused;
	}
	
	public void setPauseState (boolean state) {
		isPaused = state;
	}
}
