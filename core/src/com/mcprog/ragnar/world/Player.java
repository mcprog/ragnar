package com.mcprog.ragnar.world;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.screens.GameScreen;
import com.mcprog.ragnar.screens.WinScreen;

public class Player {

	private BodyDef bodyDef;
	private World world;
	private int accelerometerLimit = 15;
	private Sprite glow;
	
	protected Body body;
	protected int direction;
	protected float invincibleTimer;
	protected TextureRegion frame;
	protected Sprite frameSprite;
	protected float playerSpeed = 8;
	
	public boolean invincible;
	
	public static final int LEFT = 			0;
	public static final int RIGHT = 		1;
	public static final int UP = 			2;
	public static final int DOWN = 			3;
	public static final int LEFT_IDLE = 	4;
	public static final int RIGHT_IDLE = 	5;
	public static final int UP_IDLE = 		6;
	public static final int DOWN_IDLE = 	7;
	public static final int LEFT_BLOCK = 	8;
	public static final int RIGHT_BLOCK = 	9;
	public static final int UP_BLOCK = 		10;
	public static final int DOWN_BLOCK = 	11;
	
	public Player(World world, Vector2 position) {
		this.world = world;
		
		
		
		glow = new Sprite(new Texture(Gdx.files.internal("glow.png")));
		
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(position);
		
		body = world.createBody(bodyDef);
		body.setUserData(Assets.playerAnimations);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(.6f, 1f);
		body.setFixedRotation(true);
		body.createFixture(shape, 1);
		
		shape.dispose();
	}
	
	public Sprite getDraw (float stateTime) {
		frame = Assets.playerAnimations[getDirection()].getKeyFrame(stateTime, true);
		frameSprite = new Sprite(frame);
		frameSprite.setCenter(getPosition().x, getPosition().y);
		frameSprite.setScale(.125f);
		
		return frameSprite;
	}
	
	public void update (float delta, ScreenAdapter screen) {
		checkInvincibility(delta);
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
			handleInputMobile();
		} else {
			handleInput((GameScreen) screen);
		}
	}
	
	private void checkInvincibility(float delta) {
		if (invincibleTimer <= 0) {
			invincible = false;
		}
		invincibleTimer -= delta;
		
	}
	
	public void handleInputMobile () {
		if (Gdx.input.getPitch() > accelerometerLimit) {
			setLinearVelocityWithDirection(LEFT);
		}
		if (Gdx.input.getPitch() < -accelerometerLimit) {
			setLinearVelocityWithDirection(RIGHT);
		}
		if (Gdx.input.getRoll() > accelerometerLimit) {
			setLinearVelocityWithDirection(UP);
		}
		if (Gdx.input.getRoll() < -accelerometerLimit) {
			setLinearVelocityWithDirection(DOWN);
		}
		if (Math.abs(Gdx.input.getPitch()) <= accelerometerLimit && Math.abs(Gdx.input.getRoll()) <= accelerometerLimit) {
			body.setLinearVelocity(Vector2.Zero);
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE) {
				setToIdle(direction);
			}
		}
	}

	public void handleInput (GameScreen screen) {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && screen.timeInGame > 7) {
			invincible = true;
			invincibleTimer = 2;
			screen.timeInGame -= 7;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			setLinearVelocityWithDirection(LEFT);
		}
		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			setLinearVelocityWithDirection(RIGHT);
		}
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			setLinearVelocityWithDirection(UP);
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			setLinearVelocityWithDirection(DOWN);
		}
		if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
			body.setLinearVelocity(Vector2.Zero);
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
				setToIdle(direction);
			}
		}
	}
	
	protected void setLinearVelocityWithDirection (int dir) {
		direction = dir;
		switch (dir) {
		case LEFT:
			body.setLinearVelocity(-playerSpeed, 0);
			break;
		case RIGHT:
			body.setLinearVelocity(playerSpeed, 0);
			break;
		case UP:
			body.setLinearVelocity(0, playerSpeed);
			break;
		case DOWN:
			body.setLinearVelocity(0, -playerSpeed);
			break;
		default:
			break;
		}
	}
	
	protected void setToIdle (int dir) {
		switch (dir) {
		case Player.LEFT:
			direction = Player.LEFT_IDLE;
			break;
		case Player.RIGHT:
			direction = Player.RIGHT_IDLE;
			break;
		case Player.UP:
			direction = Player.UP_IDLE;
			break;
		case Player.DOWN:
			direction = Player.DOWN_IDLE;
			break;
		default:
			break;
		}
	}
	
	
	public Vector2 getVelocity () {
		return body.getLinearVelocity();
	}
	
	public Vector2 getPosition () {
		return body.getPosition();
	}
	
	public int getDirection () {
		return direction;
	}
	
	public void setDirection (int dir) {
		direction = dir;
	}
	
	public Body getBody () {
		return body;
	}
	
	public Sprite getGlow () {
		final float width = frameSprite.getWidth() * frameSprite.getScaleX();
		glow.setBounds(getPosition().x - width, getPosition().y - width, width * 2, width * 2);
		return glow;
	}

}
