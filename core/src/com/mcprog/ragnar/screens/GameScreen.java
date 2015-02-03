package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.world.ArrowSpawner;
import com.mcprog.ragnar.world.Bounds;
import com.mcprog.ragnar.world.Player;

public class GameScreen extends ScreenDrawable implements ContactListener {

	private World world;
	private Ragnar game;
	private SpriteBatch batch;
	private SpriteBatch fontBatch;
	private OrthographicCamera camera;
	private OrthographicCamera fontCamera;
	private Box2DDebugRenderer renderer;
	private Array<Body> bodies;
	private Player player;
	private ArrowSpawner spawner;
	public float stateTime;
	private float spawnTimer;
	private Array<Body> bodiesToDelete;
	public float timeBetweenArrows = 1;
	private Bounds bounds;
	private int arrowsLeft = 300;
	
	public float timeInGame;
	
	public GameScreen(Ragnar gameInstance) {
		super(gameInstance);
		game = gameInstance;
		batch = new SpriteBatch();
		fontBatch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		fontCamera = new OrthographicCamera();
		renderer = new Box2DDebugRenderer();
		bodies = new Array<Body>();
		
		
		bodiesToDelete = new Array<Body>();
		resetScreen();
		
	}
	
	public void resetScreen() {
		world = new World(Vector2.Zero, true);
		player = new Player(world, Vector2.Zero);
		spawner = new ArrowSpawner(world, player);
		bounds = new Bounds(world, Gdx.graphics.getWidth() / 8);
		world.setContactListener(this);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);//Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		if (Gdx.input.isKeyJustPressed(Keys.H)) {
			game.setScreen(game.winScreen);
		}
		
		timeInGame += delta;
		
		world.step(1/60f, 8, 3);
		world.getBodies(bodies);
		if (!world.isLocked()) {
			for (int i = 0; i < bodiesToDelete.size; ++i) {
				if (bodiesToDelete.get(i) != null) {
					world.destroyBody(bodiesToDelete.get(i));
				}
				bodiesToDelete.removeIndex(i);
			}
		}
		
		renderer.render(world, camera.combined);
		stateTime += delta;
		player.update(delta, this);
		
		spawnTimer += delta;
		if (spawnTimer > timeBetweenArrows) {
			spawner.spawn(arrowsLeft);
			spawnTimer = 0;
			if (timeBetweenArrows > .5) {
				timeBetweenArrows -= .025;
				System.out.println("Decreasing" + timeBetweenArrows);
			} else {
				System.out.println("Done");
			}
		}
		
		if (player.getBody().getWorldCenter().x < -camera.viewportWidth / 2 || player.getBody().getWorldCenter().x > camera.viewportWidth / 2 || player.getBody().getWorldCenter().y > camera.viewportHeight / 2 || player.getBody().getWorldCenter().y < -camera.viewportHeight / 2) {
			game.setToKillScreen("You got too close to the english and they speared you");
		}
		
		batch.setProjectionMatrix(fontCamera.combined);
		batch.begin();
		Assets.scoreFont.draw(batch, "Score: " + (int)(this.timeInGame), -fontCamera.viewportWidth / 2 * .97f, fontCamera.viewportHeight / 2 * .96f);
		Assets.scoreFont.draw(batch, "Arrows Left: " + (int)(arrowsLeft), fontCamera.viewportWidth / 2 * .6f, fontCamera.viewportHeight / 2 * .96f);
//		font.draw(batch, "Pitch: " + (int)(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer) ? Gdx.input.getPitch() : 7), -fontCamera.viewportWidth / 2 * .97f, fontCamera.viewportHeight / 2 * .9f);
//		font.draw(batch, "Roll: " + (int)(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer) ? Gdx.input.getRoll() : 7), -fontCamera.viewportWidth / 2 * .97f, fontCamera.viewportHeight / 2 * .85f);
//		font.draw(batch, "Azimuth: " + (int)(Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer) ? Gdx.input.getAzimuth() : 7), -fontCamera.viewportWidth / 2 * .97f, fontCamera.viewportHeight / 2 * .8f);
		batch.end();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (player.invincible) {
			player.getGlow().draw(batch);
		}
		player.getDraw(stateTime).draw(batch);
		for (Body b : bodies) {
			if (b.getUserData() instanceof Sprite) {
				
				Sprite spr = (Sprite) b.getUserData();
				if (spr != null) {
					spr.setOrigin(spr.getWidth() / 2, spr.getHeight() / 2);
					spr.setBounds(b.getPosition().x - spr.getWidth() / 2, b.getPosition().y - spr.getHeight() / 2, 3, .5f);
//					spr.setPosition(b.getPosition().x - spr.getWidth() / 2, b.getPosition().y - spr.getHeight() / 2);
					spr.setRotation(b.getAngle() * MathUtils.radiansToDegrees);
//					spr.setSize(3, .5f);
					spr.draw(batch);
				}
			}
//			else if (b.getUserData() instanceof Animation[]) {
//				Animation[] animations = (Animation[]) b.getUserData();
//				if (animations != null) {
//					frame = animations[player.getDirection()].getKeyFrame(stateTime, true);
//					frameSprite = new Sprite(frame);
//					frameSprite.setCenter(b.getPosition().x, b.getPosition().y);
//					frameSprite.setScale(.125f);
//					if (player.invincible) {
//						player.getGlow(frameSprite.getWidth() * frameSprite.getScaleX()).draw(batch);;
//					}
//					frameSprite.draw(batch);
//					
//				}
//			}
			
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS)) {
			camera.viewportWidth = width / 48;
			camera.viewportHeight = height / 48;
			camera.update();
			fontCamera.viewportWidth = width / 2;
			fontCamera.viewportHeight = height / 2;
			fontCamera.update();
		} else {
			camera.viewportWidth = width / 16;
			camera.viewportHeight = height / 16;
			camera.update();
			fontCamera.viewportWidth = width;
			fontCamera.viewportHeight = height;
			fontCamera.update();
		}
	}

	@Override
	public void show() {
		
		
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		
		if (a.getBody() != null && b.getBody() != null && a.getBody().getUserData() instanceof Sprite) {
			bodiesToDelete.add(a.getBody());
		}
		if (a.getBody() != null && b.getBody() != null && b.getBody().getUserData() instanceof Sprite) {
			bodiesToDelete.add(b.getBody());
		}
		if (a.getBody().getUserData() != null) {
			if (a.getBody().getUserData().equals("bounds")) {
				--arrowsLeft;
			}
		}
		if (b.getBody().getUserData() != null) {
			if (b.getBody().getUserData().equals("bounds")) {
				--arrowsLeft;
			}
		}
		if (a.getBody().getUserData() instanceof Animation[] || b.getBody().getUserData() instanceof Animation[]) {
			if (!player.invincible) {
				
//				game.setToKillScreen("You got shot by the bowmen");
			}
		}
		
		
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
