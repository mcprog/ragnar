package com.mcprog.ragnar.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Constants;

public class ScreenDrawable extends ScreenAdapter {
	
	protected Ragnar game;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected SpriteBatch fontBatch;
	protected OrthographicCamera fontCamera;
	
	
	public ScreenDrawable(Ragnar game) {
		this.game = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		fontBatch = new SpriteBatch();
		fontCamera = new OrthographicCamera();
	}
	
	@Override
	public void resize(int width, int height) {
		fontCamera.viewportWidth = width;
		fontCamera.viewportHeight = height;
		fontCamera.update();
		camera.viewportWidth = Constants.SCALED_WIDTH;
		camera.viewportHeight = Constants.SCALED_HEIGHT;
		camera.update();
	}
	
	@Override
	public void render(float delta) {
		Ragnar.debugger.handleInput(game);
	}

}
