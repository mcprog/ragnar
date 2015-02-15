package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;


public class KillTable extends Table {

	private Label deathMsg;
	private Label score;
	private Label highscore;
	private TextButton buttonPlay;
	private TextButton buttonSettings;
	
	
	public KillTable () {
		GuiStyles.init();
		
		deathMsg = new Label("", GuiStyles.normalLabelStyle);
		deathMsg.setWrap(true);
		score = new Label("", GuiStyles.normalLabelStyle);
		highscore = new Label("", GuiStyles.normalLabelStyle);
		
		buttonPlay = new TextButton("Play", GuiStyles.largeButtonStyle);
		buttonSettings = new TextButton("Settings", GuiStyles.largeButtonStyle);
		
		addFunctionality();
		
		add(deathMsg).center().width(800).colspan(2);
		row();
		add(score).colspan(2);
		row();
		add(highscore).colspan(2);
		row();
		add(buttonPlay);
		add(buttonSettings);
	}
	
	public void show (String deathMsg, String score, String highscore) {
		this.deathMsg.setText(deathMsg);
		this.score.setText(score);
		this.highscore.setText(highscore);
	}
	
	private void addFunctionality () {
		buttonPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
		
		buttonSettings.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.settingsScreen);
			}
		});
	}
	
}
