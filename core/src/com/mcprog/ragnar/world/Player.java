package com.mcprog.ragnar.world;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.lib.Assets;

public class Player {

	private Body body;
	private BodyDef bodyDef;
	private Vector2 position;
	private World world;
	private int direction;
	private Vector2 touch;
	private float stamina;
	private float invincibleTimer = 10;
	public boolean invincible;
	private ShapeRenderer shapeRenderer;
	private int accelerometerLimit = 15;
	
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
		this.position = position;
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setAutoShapeType(true);
		
		touch = position;
		
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
	
	public void update (float delta) {
		handleInput();
	}
	
	public void handleInput () {
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
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
			}
		} else {
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
//			}
			if (!Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.S) && !Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.RIGHT)) {
				body.setLinearVelocity(Vector2.Zero);
				if (direction != Player.LEFT_IDLE && direction != Player.RIGHT_IDLE && direction != Player.UP_IDLE && direction != Player.DOWN_IDLE && !Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
					switch (direction) {
					case Player.LEFT:
						direction = Player.LEFT_IDLE;
						break;
					case Player.LEFT_BLOCK:
						direction = Player.LEFT_IDLE;
						break;
					case Player.RIGHT:
						direction = Player.RIGHT_IDLE;
						break;
					case Player.RIGHT_BLOCK:
						direction = Player.RIGHT_IDLE;
						break;
					case Player.UP:
						direction = Player.UP_IDLE;
						break;
					case Player.UP_BLOCK:
						direction = Player.UP_IDLE;
						break;
					case Player.DOWN:
						direction = Player.DOWN_IDLE;
						break;
					case Player.DOWN_BLOCK:
						direction = Player.DOWN_IDLE;
						break;
					default:
						break;
					}
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
	
	public Body getBody () {
		return body;
	}

}
