package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;

public class Angel extends Player {

	private Sprite gust;
	private final float angelSpeed = 2;

	public Angel(World world, Vector2 position, OrthographicCamera camera) {
		super(world, position, camera, new GameScreen(Ragnar.gameInstance));
		body.getFixtureList().first().setRestitution(.5f);
		gust = new Sprite(new Texture(Gdx.files.internal("gust.png")));
	}

	@Override
	public void draw (float stateTime, SpriteBatch batch) {
		frame = Assets.playerAnimations[getDirection()].getKeyFrame(stateTime, true);
		frameSprite = new Sprite(frame);
		frameSprite.setCenter(getPosition().x, getPosition().y);
		frameSprite.setScale(.125f);

		batch.begin();
		frameSprite.draw(batch);
		batch.end();
	}

	public Sprite getGust() {
		gust.setBounds(getPosition().x - frameSprite.getWidth() / 2 * .125f + .5f, getPosition().y - frameSprite.getHeight() * .125f - 2f, 1, 4);
		gust.setAlpha(.3f);
		return gust;
	}
	
	private void setLinearImpulseWithDirection (int dir) {
		direction = dir;
		Vector2 vel = new Vector2();
		switch (dir) {
		case LEFT:
			vel.set(-angelSpeed, 0);
			break;
		case RIGHT:
			vel.set(angelSpeed, 0);
			break;
		case UP:
			vel.set(0, angelSpeed);
			break;
		case DOWN:
			vel.set(0, -angelSpeed);
			break;
		default:
			break;
		}
		body.applyLinearImpulse(vel, getPosition(), true);
	}

}
