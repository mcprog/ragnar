package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.OptionsButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.gui.buttons.QuitApp;

public class MenuTable extends RagnarTable {

	private PlayButton playButton;
	//private TextButton settings;
	//private TextButton highscore;
	private TextButton credits;
    private QuitApp quit;
    private OptionsButton optionsButton;
	
	public MenuTable () {
		GuiStyles.init();
		
		playButton = new PlayButton();
		//settings = new TextButton("Settings", GuiStyles.largeButtonStyle);
		//highscore = new TextButton("Highscore", GuiStyles.largeButtonStyle);
		credits = new TextButton("Credits", GuiStyles.largeButtonStyle);
        quit = new QuitApp();
        optionsButton = new OptionsButton();
		
		add(playButton).pad(20);
		row();
		add(optionsButton).pad(20);
		row();
		add(credits).pad(20);
        row();
        add(quit).pad(20);
		
		addFunctionality();
	}
	
	private void addFunctionality () {
		/*settings.addListener(new ClickListener() {
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
		});*/
		credits.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.creditsScreen);
			}
		});
	}
}
