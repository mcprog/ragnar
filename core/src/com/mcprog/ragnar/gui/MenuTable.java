package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;

public class MenuTable extends Table {

	private TextButton play;
	private TextButton settings;
	private TextButton highscore;
	private TextButton credits;
	
	public MenuTable () {
		GuiStyles.init();
		
		play = new TextButton("Play", GuiStyles.largeButtonStyle);
		settings = new TextButton("Settings", GuiStyles.largeButtonStyle);
		highscore = new TextButton("Highscore", GuiStyles.largeButtonStyle);
		credits = new TextButton("Credits", GuiStyles.largeButtonStyle);
		
		add(play);
		row();
		add(settings);
		row();
		add(highscore);
		row();
		add(credits);
		
		addFunctionality();
	}
	
	private void addFunctionality () {
		play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
		settings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.settingsScreen);
			}
		});
		highscore.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.highscoreScreen);
			}
		});
	}
}
