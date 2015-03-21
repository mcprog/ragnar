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
    private Label pauseRightKey;
	private TextButton buttonPlayerType;
	private TextButton buttonVibrate;
	private TextButton buttonSound;
    private TextButton buttonPauseRight;
	private PlayButton playButton;
	private MenuButton menuButton;

	
	public SettingsTable() {
		GuiStyles.init();
		
		header = new Label("Settings", GuiStyles.headerLabelStyle);
		playerKey = new Label("Player Type:", GuiStyles.normalLabelStyle);
		vibrateKey = new Label("Vibrate:", GuiStyles.normalLabelStyle);
		soundKey = new Label("Sound:", GuiStyles.normalLabelStyle);
        pauseRightKey = new Label("Pause Location:", GuiStyles.normalLabelStyle);
		
		playButton = new PlayButton();
		menuButton = new MenuButton();
		buttonPlayerType = new TextButton(getPlayerType(), GuiStyles.smallButtonStyle);
		buttonVibrate = new TextButton(RagnarConfig.vibrate ? "On" : "Off", GuiStyles.smallButtonStyle);
		buttonSound = new TextButton(RagnarConfig.sound ? "On" : "Off", GuiStyles.smallButtonStyle);
        buttonPauseRight = new TextButton(RagnarConfig.isPauseOnRight ? "Right" : "Left", GuiStyles.smallButtonStyle);
		
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
        add(pauseRightKey).left();
        add(buttonPauseRight).right().pad(20);
        row();
		add(playButton).pad(20);
		add(menuButton).pad(20);

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
				buttonVibrate.setText(RagnarConfig.vibrate ? "On" : "Off");
			}
		});
		buttonSound.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				RagnarConfig.sound = !RagnarConfig.sound;
				RagnarConfig.updateFile();
				buttonSound.setText(RagnarConfig.sound ? "On" : "Off");
			}
		});
        buttonPauseRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                RagnarConfig.isPauseOnRight ^= true;//this toggling is soooo cool
                RagnarConfig.updateFile();
                buttonPauseRight.setText(RagnarConfig.isPauseOnRight ? "Right": "Left");
            }
        });
	}
	
	private String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

}
