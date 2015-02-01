package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.screens.GameScreen;

public class Angel extends Player {

	public Angel(World world, Vector2 position) {
		super(world, position);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handleInput(GameScreen screen) {
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			body.setLinearVelocity(-8, 0);
			direction = Player.LEFT;
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			body.setLinearVelocity(8, 0);
			direction = Player.RIGHT;
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			body.applyLinearImpulse(new Vector2(0, 8), body.getWorldCenter(), true);
			direction = Player.UP;
		}
		if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			body.setLinearVelocity(Vector2.Zero);
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				switch (direction) {
				case Player.LEFT | Player.LEFT_BLOCK:
					direction = Player.LEFT_IDLE;
					break;
				case Player.RIGHT | RIGHT_BLOCK:
					direction = Player.RIGHT_IDLE;
					break;
				case Player.UP | Player.UP_BLOCK:
					direction = Player.UP_IDLE;
					break;
				case Player.DOWN | Player.DOWN_BLOCK:
					direction = Player.DOWN_IDLE;
					break;
				default:
					break;
				}
			}
		}
	}

}
