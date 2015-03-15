package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.gui.GuiStyles;

public class PauseTable extends RagnarTable {

	private TextButton pauseResume;
	
	private boolean isPaused;
	
	public PauseTable() {
		setFillParent(true);
		GuiStyles.init();
		pauseResume = new TextButton("Pause", GuiStyles.tinyButtonStyleLight);
		
		add(pauseResume);
		pad(60);
		right();
		bottom();
		
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
}
