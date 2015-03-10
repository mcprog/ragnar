package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;

public class PauseTable extends Table {

	private TextButton resume;
	private MenuButton menu;
	private PlayButton restart;
	
	private boolean isPaused;
	
	public PauseTable() {
		GuiStyles.init();
		resume = new TextButton("Pause", GuiStyles.largeButtonStyleLight);
		menu = new MenuButton(GuiStyles.largeButtonStyleLight);
		menu.addFunctionality();
		restart = new PlayButton(GuiStyles.largeButtonStyleLight);
		restart.setText("Restart");
		restart.addFunctionality();
		
		add(resume);
		
		resume.addListener(new ClickListener () {
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
	
	public void render () {
//		if (isPaused) {
//			pause();
//		} else {
//			resume();
//		}
	}
	
	public void pause () {
		clear();
		reset();
		removeActor(resume);
		resume.setText("Resume");
//		add(resume);
		row();
		add(menu);
		row();
		add(restart);
		isPaused = true;
	}
	
	public void resume () {
		clear();
		reset();
		removeActor(resume);
		resume.setText("Pause");
//		add(resume);
		isPaused = false;
	}
	
	public boolean isPaused () {
		return isPaused;
	}
	
	public void setPauseState (boolean state) {
		isPaused = state;
	}
}
