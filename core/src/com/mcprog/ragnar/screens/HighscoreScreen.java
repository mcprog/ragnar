package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.HighscoreTable;
import com.mcprog.ragnar.lib.Constants;

public class HighscoreScreen extends ScreenDrawable {

	private Stage stage;
	private HighscoreTable highscoreTable;
	
	public HighscoreScreen(Ragnar game) {
		super(game);
		highscoreTable = new HighscoreTable();
		highscoreTable.setFillParent(true);
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		
		stage.addActor(highscoreTable);
		
		if (Ragnar.debugger.on) {
			stage.setDebugAll(true);
		}
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		highscoreTable.show();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}

}
