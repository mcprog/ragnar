package com.mcprog.ragnar.gui;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mcprog.ragnar.utility.RagnarMath;
import com.mcprog.ragnar.world.Player;

public class MobileControls {
	
	private boolean isMobile;
	private ShapeRenderer controlRenderer;
    private Vector2 radiusVector;
    private float radius;
    private float radiusSmall;
	
	public MobileControls() {
		isMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		controlRenderer = new ShapeRenderer();
        radiusVector = new Vector2();
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
        radius = Gdx.graphics.getWidth() / 25f;
        radiusSmall = radius / 2;
        radiusVector.set(dragVector).setLength(radius - radiusSmall);
		controlRenderer.circle(originX, originY, radius, 36);
        //controlRenderer.setColor(0, .5f, 1, .3f);
        controlRenderer.circle(originX + RagnarMath.minLength(dragVector.x, radiusVector.x), originY + RagnarMath.minLength(dragVector.y, radiusVector.y), radiusSmall, 36);
		controlRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

}
