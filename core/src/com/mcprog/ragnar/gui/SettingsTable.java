package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.RagnarConfig;

public class SettingsTable extends Table {
	
	private Label header;
	private Label playerKey;
	private Label highscoreKey;
	private Label highscoreVal;
	private TextButton buttonBack;
	private TextButton buttonPlayerType;
	
	public SettingsTable() {
		GuiStyles.init();
		
		header = new Label("Settings", GuiStyles.headerLabelStyle);
		playerKey = new Label("Player Type:", GuiStyles.normalLabelStyle);
		highscoreKey = new Label("Highscore:", GuiStyles.normalLabelStyle);
		highscoreVal = new Label(Integer.toString(RagnarConfig.highScore), GuiStyles.normalLabelStyle);
		
		buttonBack = new TextButton("Back", GuiStyles.largeButtonStyle);
		buttonPlayerType = new TextButton(getPlayerType(), GuiStyles.smallButtonStyle);
		
		addFunctionality();
		
		add(header).colspan(2);
		row();
		add(playerKey).left();
		add(buttonPlayerType).right().pad(20);
		row();
		add(highscoreKey).left();
		add(highscoreVal).right().padRight(20);
		row();
		add(buttonBack).colspan(2).pad(20);
	}
	
	public void show () {
		highscoreVal.setText(Integer.toString(RagnarConfig.highScore));
	}
	
	private void addFunctionality () {
		buttonPlayerType.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (RagnarConfig.playerType == 0) {
					RagnarConfig.playerType = 1;
					RagnarConfig.updateFile();
				} else {
					RagnarConfig.playerType = 0;
					RagnarConfig.updateFile();
				}
				buttonPlayerType.setText(getPlayerType());
			}
		});
		
		buttonBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
	}
	
	private String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

}
