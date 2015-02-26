package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GameTable;
import com.mcprog.ragnar.gui.MobileControls;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.utility.DebugUtility;
import com.mcprog.ragnar.world.Arrow;
import com.mcprog.ragnar.world.ArrowSpawner;
import com.mcprog.ragnar.world.Bounds;
import com.mcprog.ragnar.world.Player;

public class GameScreen extends ScreenDrawable implements ContactListener {

	private World world;
	private Player player;
	private ArrowSpawner spawner;
	private Array<Body> bodies;
	private Array<Body> bodiesToDelete;
	private Stage stage;
	private GameTable table;
	
	private float stateTime;
	private Bounds bounds;
	private MobileControls mobileControls;
	public float timeInGame;
	private Texture treeTop;
	private Texture treeLeft;
	private SpriteBatch treeBatch;
	private OrthographicCamera treeCamera;
	private Sound arrowHit;
	private boolean gamePaused;
	
	public GameScreen(Ragnar gameInstance) {
		super(gameInstance);
		
		bodies = new Array<Body>();
		
		
		bodiesToDelete = new Array<Body>();
		
		mobileControls = new MobileControls();
		
		treeBatch = new SpriteBatch();
		treeCamera = new OrthographicCamera();
		
		arrowHit = Gdx.audio.newSound(Gdx.files.internal("sounds/arrow-hit.mp3"));
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		table = new GameTable();
		table.setFillParent(true);
		stage.addActor(table);
	}
	
	@Override
	public void render(float delta) {
		if (gamePaused) {
			return;
		}
		super.render(delta);
		Gdx.gl.glClearColor(.15f, .4f, .15f, 1);//Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		timeInGame += delta;
		world.step(1/60f, 8, 3);
		safelyDestroyBodies();
		world.getBodies(bodies);
		stateTime += delta;
		player.update(delta);
		if (player.getBody().getWorldCenter().x - player.getHalfWidth() < -camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().x + player.getHalfWidth() > camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().y + player.getHalfHeight() > camera.viewportHeight / 2 
				|| player.getBody().getWorldCenter().y - player.getHalfHeight() < -camera.viewportHeight / 2) {
			if (spawner.getWin()) {
				game.setScreen(game.winScreen);
			} else {
				arrowHit.play();
				if (Ragnar.isMobile) {
					Gdx.input.vibrate(300);
				}
				game.setToKillScreen(KillScreen.STABBED);
			}
		}
		spawner.spawn(delta);
		batch.setProjectionMatrix(camera.combined);
		table.update(timeInGame, spawner.getArrowsLeft());
		stage.act(delta);
		stage.draw();
		player.draw(stateTime, batch);
		Arrow.drawArrows(batch, bodies);
		batch.setProjectionMatrix(treeCamera.combined);
		batch.begin();
		batch.draw(Assets.treeLeft, -treeCamera.viewportWidth / 2 - 16, -treeCamera.viewportHeight / 2);
		batch.draw(Assets.treeLeft, treeCamera.viewportWidth / 2 - 16, -treeCamera.viewportHeight / 2);
		batch.draw(Assets.treeTop, -treeCamera.viewportWidth / 2, treeCamera.viewportHeight / 2 - 16);
		batch.draw(Assets.treeTop, -treeCamera.viewportWidth / 2, -treeCamera.viewportHeight / 2 - 16);
		batch.end();
		drawText(fontBatch);
		Ragnar.debugger.addDebug("FPS", Gdx.graphics.getFramesPerSecond());
		Ragnar.debugger.addDebug("Control Angle", (int) (MathUtils.radiansToDegrees * player.dragAngle));
		Ragnar.debugger.renderDebug(world, camera.combined);
		mobileControls.update(delta);
	}

	@Override
	public void show() {
		timeInGame = 0;
		world = new World(Vector2.Zero, true);
		player = new Player(world, Vector2.Zero, camera);
		spawner = new ArrowSpawner(world, player);
		bounds = new Bounds(world, Gdx.graphics.getWidth() / 8);
		world.setContactListener(this);
		if (Ragnar.debugger.on) {
			stage.setDebugAll(true);
		}
		Gdx.input.setInputProcessor(player);
	}
	
	@Override
	public void resize(int width, int height) {
		treeCamera.viewportWidth = Constants.TEST_WIDTH;
		treeCamera.viewportHeight = Constants.TEST_HEIGHT;
		treeCamera.update();
		System.out.println("GameScreen resized");
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void pause() {
		gamePaused = true;
	}
	
	@Override
	public void resume() {
		gamePaused  = false;
	}
	
	private void safelyDestroyBodies () {
		world.getBodies(bodies);
		if (!world.isLocked()) {
			for (Body b : bodiesToDelete) {
				b.setActive(false);
			}
			bodiesToDelete.clear();
			for (Body i : bodies) {
				if (!i.isActive()) {
					world.destroyBody(i);
				}
			}
		}
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		
		if (a.getBody() != null && a.getBody().getUserData() != null && a.getBody().getUserData().equals("arrow")) {
			bodiesToDelete.add(a.getBody());
		}
		if (b.getBody() != null && b.getBody().getUserData() != null && b.getBody().getUserData().equals("arrow")) {
			bodiesToDelete.add(b.getBody());
		}
		if (a.getBody().getUserData() instanceof Animation[] || b.getBody().getUserData() instanceof Animation[]) {
			if (!player.invincible) {
				if (spawner.getWin()) {
					game.setScreen(game.winScreen);
				} else {
					arrowHit.play();
					if (Ragnar.isMobile) {
						Gdx.input.vibrate(300);
					}
					game.setToKillScreen(KillScreen.SHOT);
				}
			}
		}
		
		
	}
	
	private void drawText (SpriteBatch batch) {
//		batch.setProjectionMatrix(fontCamera.combined);
//		batch.begin();
//		Assets.scoreFont.draw(batch, "Score: " + (int)(this.timeInGame), -fontCamera.viewportWidth / 2 * .9f, fontCamera.viewportHeight / 2 * .8f);
//		Assets.scoreFont.draw(batch, "Dishonor: " + spawner.getArrowsLeft(), fontCamera.viewportWidth / 2 * .65f, fontCamera.viewportHeight / 2 * .8f);
//		batch.end();
		Ragnar.debugger.textDebug(fontBatch, fontCamera);
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
		
	}

}
