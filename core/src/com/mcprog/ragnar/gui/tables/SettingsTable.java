package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.lib.RagnarConfig;

public class SettingsTable extends RagnarTable {
	
	private Label header;
	private Label playerKey;
	private Label vibrateKey;
	private Label soundKey;
	private TextButton buttonPlayerType;
	private TextButton buttonVibrate;
	private TextButton buttonSound;
	private PlayButton playButton;
	private MenuButton menuButton;
    private TextButton highscores;
	
	public SettingsTable() {
		GuiStyles.init();
		
		header = new Label("Settings", GuiStyles.headerLabelStyle);
		playerKey = new Label("Player Type:", GuiStyles.normalLabelStyle);
		vibrateKey = new Label("Vibrate:", GuiStyles.normalLabelStyle);
		soundKey = new Label("Sound", GuiStyles.normalLabelStyle);
		
		playButton = new PlayButton();
		menuButton = new MenuButton();
		buttonPlayerType = new TextButton(getPlayerType(), GuiStyles.smallButtonStyle);
		buttonVibrate = new TextButton(String.valueOf(RagnarConfig.vibrate), GuiStyles.smallButtonStyle);
		buttonSound = new TextButton(String.valueOf(RagnarConfig.sound), GuiStyles.smallButtonStyle);
        highscores = new TextButton("Leaderboards", GuiStyles.smallButtonStyle);
		
		addFunctionality();
		
		add(header).colspan(2);
		row();
		add(playerKey).left();
		add(buttonPlayerType).right().pad(20);
		row();
		if (Ragnar.isMobile) {
			add(vibrateKey).left();
			add(buttonVibrate).right().pad(20);
			row();
		}
		add(soundKey).left();
		add(buttonSound).right().pad(20);
		row();
		add(playButton).pad(20);
		add(menuButton).pad(20);
        if (Ragnar.isMobile) {
            row();
            add(highscores).colspan(2);
        }
	}
	
	private void addFunctionality () {
		buttonPlayerType.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (RagnarConfig.playerType == 0) {
					RagnarConfig.playerType = 1;
				} else {
					RagnarConfig.playerType = 0;
				}
				RagnarConfig.updateFile();
				buttonPlayerType.setText(getPlayerType());
			}
		});
		buttonVibrate.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				RagnarConfig.vibrate = !RagnarConfig.vibrate;
				RagnarConfig.updateFile();
				buttonVibrate.setText(String.valueOf(RagnarConfig.vibrate));
			}
		});
		buttonSound.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				RagnarConfig.sound = !RagnarConfig.sound;
				RagnarConfig.updateFile();
				buttonSound.setText(String.valueOf(RagnarConfig.sound));
			}
		});
        highscores.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Ragnar.gameInstance.gpgs.getLeaderBoard();
            }
        });
	}
	
	private String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

}
