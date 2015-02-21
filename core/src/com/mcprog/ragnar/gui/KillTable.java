package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.KillScreen;


public class KillTable extends Table {

	private Label deathMsg;
	private Label score;
	private Label highscore;
	private TextButton buttonPlay;
	private TextButton buttonSettings;
	private Image deathImg;
	
	public KillTable () {
		GuiStyles.init();
		
		deathMsg = new Label("", GuiStyles.normalLabelStyleWhite);
		deathMsg.setWrap(true);
		score = new Label("", GuiStyles.normalLabelStyleWhite);
		highscore = new Label("", GuiStyles.normalLabelStyleWhite);
		
		buttonPlay = new TextButton("Play", GuiStyles.largeButtonStyleLight);
		buttonSettings = new TextButton("Settings", GuiStyles.largeButtonStyleLight);
		
		deathImg = new Image(Assets.getLoadedTexture(Assets.DEAD_PLAYER_PATH));
		
		addFunctionality();
		
		add(deathMsg).center().width(800).colspan(2);
		row();
		add(score).colspan(2);
		row();
		add(highscore).colspan(2);
		row();
		add(deathImg).colspan(2).center().size(deathImg.getWidth() * 8, deathImg.getHeight() * 8);
		row();
		add(buttonPlay);
		add(buttonSettings);
	}
	
	public void show (String deathMsg, String score, String highscore, int deathType) {
		this.deathMsg.setText(deathMsg);
		this.score.setText(score);
		this.highscore.setText(highscore);
		System.out.println(deathType);
		if (deathType == KillScreen.STABBED) {
			deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_STABBED_PATH))));
			getCell(deathImg).size(21 * 8, 28 * 8);
		} else {
			deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_PATH))));
			getCell(deathImg).size(24 * 8, 16 * 8);
		}
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
