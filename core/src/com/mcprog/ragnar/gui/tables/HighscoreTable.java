package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.lib.RagnarConfig;

public class HighscoreTable extends RagnarTable {

	private Label header;
	private Label highscoreKey;
	private Label highscoreVal;
	private PlayButton playButton;
	private MenuButton menuButton;
	
	public HighscoreTable() {
		GuiStyles.init();
		
		playButton = new PlayButton();
		menuButton = new MenuButton();
		
		header = new Label("Highscore", GuiStyles.headerLabelStyle);
		
		highscoreKey = new Label("Highscore:", GuiStyles.normalLabelStyle);
		highscoreVal = new Label(Integer.toString(RagnarConfig.highScore), GuiStyles.normalLabelStyle);
		
		add(header).colspan(2);
		row();
		add(highscoreKey).left();
		add(highscoreVal).right().pad(20);
		row();
		add(playButton).pad(20);
		add(menuButton).pad(20);
		
	}
	
	public void show () {
		highscoreVal.setText(Integer.toString(RagnarConfig.highScore));
	}

}