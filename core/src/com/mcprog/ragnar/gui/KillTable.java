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
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.lib.RagnarConfig;
import com.mcprog.ragnar.screens.KillScreen;


public class KillTable extends Table {

	private Label deathMsg;
	private Label score;
	private Label highscore;
	private PlayButton playButton;
	private MenuButton menuButton;
	private Image deathImg;
	
	public KillTable () {
		GuiStyles.init();
		
		deathMsg = new Label("", GuiStyles.normalLabelStyleWhite);
		deathMsg.setWrap(true);
		score = new Label("", GuiStyles.normalLabelStyleWhite);
		highscore = new Label("", GuiStyles.normalLabelStyleWhite);
		
		playButton = new PlayButton(GuiStyles.largeButtonStyleLight);
		menuButton = new MenuButton(GuiStyles.largeButtonStyleLight);
		
		deathImg = new Image(Assets.getLoadedTexture(Assets.DEAD_PLAYER_PATH));
		
		
		add(deathMsg).center().width(800).colspan(2);
		row();
		add(score).colspan(2);
		row();
		add(highscore).colspan(2);
		row();
		add(deathImg).colspan(2).center().size(deathImg.getWidth() * 8, deathImg.getHeight() * 8);
		row();
		add(playButton);
		add(menuButton);
	}
	
	public void show (String deathMsg, String score, String highscore, int deathType) {
		this.deathMsg.setText(deathMsg);
		this.score.setText(score);
		this.highscore.setText(highscore);
		System.out.println(deathType);
		if (deathType == KillScreen.STABBED) {
			if (RagnarConfig.playerType == 0) {
				deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_STABBED_PATH))));
			} else {
				deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_STABBED_GIRL_PATH))));
			}
		} else {
			if (RagnarConfig.playerType == 0) {
				deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_PATH))));
			} else {
				deathImg.setDrawable(new SpriteDrawable(new Sprite(Assets.getLoadedTexture(Assets.DEAD_PLAYER_GIRL_PATH))));
			}
		}
		sizeImage(deathImg, deathType);
	}
	
	
	private void sizeImage (Image img, int deathType) {
		if (deathType == KillScreen.SHOT) {
			getCell(img).size(Constants.DEATH_IMAGE_SHOT_WIDTH * 8, Constants.DEATH_IMAGE_SHOT_HEIGHT * 8);
		}
		else if (deathType == KillScreen.STABBED) {
			getCell(img).size(Constants.DEATH_IMAGE_STABBED_WIDTH * 8, Constants.DEATH_IMAGE_STABBED_HEIGHT * 8);
		}
	}
	
	
}
