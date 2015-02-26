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
	private TextButton buttonPlay;
	private TextButton buttonMenu;
	private TextButton buttonPlayerType;
	
	public SettingsTable() {
		GuiStyles.init();
		
		header = new Label("Settings", GuiStyles.headerLabelStyle);
		playerKey = new Label("Player Type:", GuiStyles.normalLabelStyle);
		
		buttonPlay = new TextButton("Play", GuiStyles.largeButtonStyle);
		buttonMenu = new TextButton("Menu", GuiStyles.largeButtonStyle);
		buttonPlayerType = new TextButton(getPlayerType(), GuiStyles.smallButtonStyle);
		
		addFunctionality();
		
		add(header).colspan(2);
		row();
		add(playerKey).left();
		add(buttonPlayerType).right().pad(20);
		row();
		add(buttonPlay).pad(20);
		add(buttonMenu).pad(20);
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
	
	private String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

}
