package com.mcprog.ragnar.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;

public class Angel extends Player {

	private TextureRegion frame;
	private Sprite frameSprite;
	private Sprite gust;
	
	public Angel(World world, Vector2 position) {
		super(world, position);
		body.getFixtureList().first().setRestitution(.5f);
		gust = new Sprite(new Texture(Gdx.files.internal("glow.png")));
	}
	
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
			body.applyLinearImpulse(new Vector2(0, 1), body.getWorldCenter(), true);
			direction = Player.DOWN;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			body.applyLinearImpulse(new Vector2(-1, 0), body.getWorldCenter(), true);
			direction = Player.LEFT;
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			body.applyLinearImpulse(new Vector2(1, 0), body.getWorldCenter(), true);
			direction = Player.RIGHT;
		}
		if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				
				switch (direction) {
				case Player.LEFT/* | Player.LEFT_BLOCK*/:
					direction = Player.LEFT_IDLE;
					break;
				case Player.RIGHT/* | RIGHT_BLOCK*/:
					direction = Player.RIGHT_IDLE;
					break;
				case Player.UP/* | Player.UP_BLOCK*/:
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
	
	public Sprite getGust () {
		gust.setBounds(getPosition().x - frameSprite.getWidth() / 2 * .125f, getPosition().y - frameSprite.getHeight() / 2 * .125f - 2, 2, 2);
		return gust;
	}

}
