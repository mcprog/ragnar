package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.RagnarConfig;

public class HighscoreTable extends Table {

	private Label header;
	private Label highscoreKey;
	private Label highscoreVal;
	private TextButton buttonPlay;
	private TextButton buttonMenu;
	
	public HighscoreTable() {
		GuiStyles.init();
		
		header = new Label("Highscore", GuiStyles.headerLabelStyle);
		
		buttonPlay = new TextButton("Play", GuiStyles.largeButtonStyle);
		buttonMenu = new TextButton("Menu", GuiStyles.largeButtonStyle);
		
		
		highscoreKey = new Label("Highscore:", GuiStyles.normalLabelStyle);
		highscoreVal = new Label(Integer.toString(RagnarConfig.highScore), GuiStyles.normalLabelStyle);
		
		add(header).colspan(2);
		row();
		add(highscoreKey).left();
		add(highscoreVal).right().pad(20);
		row();
		add(buttonPlay).pad(20);
		add(buttonMenu).pad(20);
		
		addFunctionality();
	}
	
	public void show () {
		highscoreVal.setText(Integer.toString(RagnarConfig.highScore));
	}

	private void addFunctionality() {
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
		buttonMenu.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.menuScreen);
			}
		});
		
	}
}
