package com.mcprog.ragnar.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.tables.KillTable;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.lib.RagnarConfig;

public class KillScreen extends ScreenDrawable {

//	private Ragnar game;
	public static final int SHOT = 0;
	public static final int STABBED = 1;
	public static final String SHOT_MSG = "You got shot by the bowmen";
	public static final String STABBED_MSG = "You got too close to the english and they speared you";
	private String deathMsgSuffix;
	private boolean newTouchUp;
	private Stage stage;
	private KillTable killTable;
	
	public int deathType = -1;
	
	public KillScreen(Ragnar gameInstance) {
		super(gameInstance);
//		game = gameInstance;
		GuiStyles.init();
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		killTable = new KillTable();
		if (Ragnar.isMobile) {
			deathMsgSuffix = "\nTap screen to retry\n";
		} else {
			deathMsgSuffix = "\nHit \"R\" to retry\n";
		}
		stage.addActor(killTable);
	}
	
	@Override
	public void show() {
		newTouchUp = false;
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		if (RagnarConfig.highScore < (int) (game.gameScreen.timeInGame)) {
			RagnarConfig.highScore = (int) (game.gameScreen.timeInGame);
		}
		RagnarConfig.updateFile();
		killTable.show(assembleMessage(), "You lasted " + (int)(game.gameScreen.timeInGame) + " seconds", "Highscore: " + RagnarConfig.highScore, deathType);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(.1f, .1f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			game.setScreen(game.gameScreen);
		}
		
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	public String assembleMessage () {
		return deathType == SHOT ? SHOT_MSG : STABBED_MSG;
	}
	
	private void drawDeath (SpriteBatch batch) {
		Assets.deadPlayerSprite.setSize(24 * fontCamera.viewportHeight / 96, 16 * fontCamera.viewportHeight / 96);
		Assets.deadPlayerSprite.setCenter(0, -fontCamera.viewportHeight / 4);
		Assets.deadPlayerStabbedSprite.setSize(21 * fontCamera.viewportHeight / 84, 28 * fontCamera.viewportHeight / 84);
		Assets.deadPlayerStabbedSprite.setCenter(0, -fontCamera.viewportHeight / 4);
		if (deathType == SHOT) {
			Assets.deadPlayerSprite.draw(batch);
		}
		else if (deathType == STABBED) {
			Assets.deadPlayerStabbedSprite.draw(batch);
		}
	}

}
