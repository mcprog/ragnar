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
	
	public PauseTable() {
		GuiStyles.init();
		resume = new TextButton("Resume", GuiStyles.largeButtonStyleLight);
		menu = new MenuButton(GuiStyles.largeButtonStyleLight);
		menu.setVisible(false);
		restart = new PlayButton(GuiStyles.largeButtonStyleLight);
		restart.setText("Restart");
		restart.setVisible(false);
		restart.addFunctionality();
		
		add(resume);
		row();
		add(menu);
		row();
		add(restart);
		
		resume.addListener(new ClickListener () {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (Ragnar.gameInstance.gameScreen.isPaused()) {
					resume();
					
				} else {
					pause();
				}
			}
		});
	}
	
	public void pause () {
		menu.setVisible(true);
		restart.setVisible(true);
	}
	
	public void resume () {
		menu.setVisible(false);
		restart.setVisible(false);
		resume.setText("Resume");
	}
}
