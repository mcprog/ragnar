package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class SettingsScreen extends ScreenDrawable implements InputProcessor {

	
	
	public SettingsScreen(Ragnar game) {
		super(game);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
		Assets.ragnarFont.draw(fontBatch, "Settings", 0, fontCamera.viewportWidth / 4);
		Assets.ragnarFont.draw(fontBatch, "Player type: " + RagnarConfig.playerType, -fontCamera.viewportWidth / 4, 0);
		Assets.ragnarFont.draw(fontBatch, "Highscore: " + RagnarConfig.highScore, -fontCamera.viewportWidth / 4, -fontCamera.viewportHeight / 4);
		fontBatch.end();

		
	}
	
	public String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.P) {
			if (RagnarConfig.playerType == 0) {
				RagnarConfig.playerType = 1;
				RagnarConfig.updateFile();
			} else {
				RagnarConfig.playerType = 0;
				RagnarConfig.updateFile();
			}
		}
		else if (keycode == Keys.G) {
			game.setScreen(game.gameScreen);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
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
