package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class KillScreen extends ScreenDrawable implements InputProcessor {

	private Ragnar game;
	public static final int SHOT = 0;
	public static final int STABBED = 1;
	public static final String SHOT_MSG = "You got shot by the bowmen";
	public static final String STABBED_MSG = "You got too close to the english and they speared you";
	private String deathMsgSuffix;
	private boolean newTouchUp;
	
	public int deathType = -1;
	
	public KillScreen(Ragnar gameInstance) {
		super(gameInstance);
		game = gameInstance;
		if (Ragnar.isMobile) {
			deathMsgSuffix = "\nTap screen to retry\n";
		} else {
			deathMsgSuffix = "\nHit \"R\" to retry\n";
		}
	}
	
	@Override
	public void show() {
		newTouchUp = false;
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		if (RagnarConfig.highScore < (int) (game.gameScreen.timeInGame)) {
			
			RagnarConfig.highScore = (int) (game.gameScreen.timeInGame);
		}
		RagnarConfig.updateFile();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(.1f, .1f, .2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
		Assets.ragnarFont.drawWrapped(fontBatch, assembleMessage() + deathMsgSuffix + "You lasted " + (int)(game.gameScreen.timeInGame) + " seconds\nHighscore: " + RagnarConfig.highScore, -fontCamera.viewportWidth * .375f, fontCamera.viewportHeight * .375f, fontCamera.viewportWidth * .75f, HAlignment.CENTER);
		drawDeath(fontBatch);
		fontBatch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			game.setScreen(game.gameScreen);
		}
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

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			game.setScreen(game.gameScreen);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		newTouchUp = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (newTouchUp) {
			
			game.setScreen(game.gameScreen);
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
