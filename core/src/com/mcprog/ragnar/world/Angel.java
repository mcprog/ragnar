package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;

public class Angel extends Player {

	private Sprite gust;
	private final float angelSpeed = 2;

	public Angel(World world, Vector2 position) {
		super(world, position);
		body.getFixtureList().first().setRestitution(.5f);
		gust = new Sprite(new Texture(Gdx.files.internal("gust.png")));
	}

	@Override
	public Sprite getDraw (float stateTime) {
		frame = Assets.playerAnimations[getDirection()].getKeyFrame(stateTime, true);
		frameSprite = new Sprite(frame);
		frameSprite.setCenter(getPosition().x, getPosition().y);
		frameSprite.setScale(.125f);

		return frameSprite;
	}

	@Override
	public void handleInput(GameScreen screen) {
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			setLinearImpulseWithDirection(UP);
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			setLinearImpulseWithDirection(LEFT);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			setLinearImpulseWithDirection(RIGHT);
		}
		if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				setToIdle(direction);
			}
		}
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
