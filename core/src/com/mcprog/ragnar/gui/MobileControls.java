package com.mcprog.ragnar.gui;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.mcprog.ragnar.world.Player;

public class MobileControls {
	
	private boolean isMobile;
	private ShapeRenderer controlRenderer;
	
	public MobileControls() {
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		controlRenderer = new ShapeRenderer();
	}
	
	public void update (float delta) {
		if (!isMobile) {
			return;
		}
		handleInput();
	}

	private void handleInput() {
		if (Gdx.input.isTouched()) {
			drawMobileControls();
		}
		
	}

	private void drawMobileControls() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Vector3 fixedTouchCoords = Player.touchCoords;
		controlRenderer.begin(ShapeType.Filled);
		controlRenderer.setColor(1, 1, 1, .2f);
		controlRenderer.circle(fixedTouchCoords.x, (Gdx.graphics.getHeight() - fixedTouchCoords.y), 100, 36);
		controlRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

}
