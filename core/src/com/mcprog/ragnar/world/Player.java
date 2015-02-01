package com.mcprog.ragnar.world;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

	protected Body body;
	private BodyDef bodyDef;
	private World world;
	protected int direction;
	public boolean invincible;
	protected float invincibleTimer;
	private int accelerometerLimit = 15;
	private Sprite glow;
	
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
			body.setLinearVelocity(-8, 0);
			direction = Player.LEFT;
		}
		if (Gdx.input.getPitch() < -accelerometerLimit) {
			body.setLinearVelocity(8, 0);
			direction = Player.RIGHT;
		}
		if (Gdx.input.getRoll() > accelerometerLimit) {
			body.setLinearVelocity(0, 8);
			direction = Player.UP;
		}
		if (Gdx.input.getRoll() < -accelerometerLimit) {
			body.setLinearVelocity(0, -8);
			direction = Player.DOWN;
		}
		if (Math.abs(Gdx.input.getPitch()) <= accelerometerLimit && Math.abs(Gdx.input.getRoll()) <= accelerometerLimit) {
			body.setLinearVelocity(Vector2.Zero);
			if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE) {
					
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

	public void handleInput (GameScreen screen) {
		
			if (Gdx.input.isKeyJustPressed(Keys.SPACE) && screen.timeInGame > 10) {
				invincible = true;
				invincibleTimer = 2;
				screen.timeInGame -= 10;
			}
			if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
				body.setLinearVelocity(-8, 0);
				direction = Player.LEFT;
			}
			if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
				body.setLinearVelocity(8, 0);
				direction = Player.RIGHT;
			}
			if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
				body.setLinearVelocity(0, 8);
				direction = Player.UP;
			}
			if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
				body.setLinearVelocity(0, -8);
				direction = Player.DOWN;
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
	
	public Sprite getGlow (float width) {
		glow.setBounds(getPosition().x - width, getPosition().y - width, width * 2, width * 2);
		return glow;
	}

}
