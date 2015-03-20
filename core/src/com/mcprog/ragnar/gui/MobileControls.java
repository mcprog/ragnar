package com.mcprog.ragnar.gui;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
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
	
	public void update (float delta, Vector2 dragVector) {
		if (!isMobile) {
			return;
		}
		handleInput(dragVector);
	}

	private void handleInput(Vector2 dragVector) {
		if (Gdx.input.isTouched()) {
			drawMobileControls(dragVector);
		}
		
	}

	private void drawMobileControls(Vector2 dragVector) {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Vector3 fixedTouchCoords = Player.touchCoords;
        float originX = fixedTouchCoords.x;
        float originY = Gdx.graphics.getHeight() - fixedTouchCoords.y;
		controlRenderer.begin(ShapeType.Filled);
		controlRenderer.setColor(1, 1, 1, .2f);
        float radius = Gdx.graphics.getWidth() / 25f;
		controlRenderer.circle(originX, originY, radius, 36);
        controlRenderer.circle(originX + dragVector.x * (radius + radius / 3), originY + dragVector.y * (radius + radius / 3), radius / 5, 36);
		controlRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

}
