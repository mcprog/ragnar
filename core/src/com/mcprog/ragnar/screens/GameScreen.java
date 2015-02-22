package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.utils.Array;
import com.mcprog.ragnar.Ragnar;
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
	
	private float stateTime;
	private Bounds bounds;
	private MobileControls mobileControls;
	public float timeInGame;
	private Texture treeTop;
	private SpriteBatch treeBatch;
	private OrthographicCamera treeCamera;
	
	public GameScreen(Ragnar gameInstance) {
		super(gameInstance);
		
		bodies = new Array<Body>();
		
		
		bodiesToDelete = new Array<Body>();
		
		mobileControls = new MobileControls();
		
		treeTop = new Texture(Gdx.files.internal("tree-top.png"));
		treeBatch = new SpriteBatch();
		treeCamera = new OrthographicCamera();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(.15f, .4f, .15f, 1);//Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		timeInGame += delta;
		world.step(1/60f, 8, 3);
		safelyDestroyBodies();
		world.getBodies(bodies);
		stateTime += delta;
		player.update(delta);
		if (player.getBody().getWorldCenter().x + player. < -camera.viewportWidth / 2 || player.getBody().getWorldCenter().x > camera.viewportWidth / 2 || player.getBody().getWorldCenter().y > camera.viewportHeight / 2 || player.getBody().getWorldCenter().y < -camera.viewportHeight / 2) {
			if (spawner.getWin()) {
				game.setScreen(game.winScreen);
			} else {
				game.setToKillScreen(KillScreen.STABBED);
			}
		}
		spawner.spawn(delta);
//		spawner.checkWin(game);
		batch.setProjectionMatrix(camera.combined);
		player.draw(stateTime, batch);
		Arrow.drawArrows(batch, bodies);
		batch.setProjectionMatrix(treeCamera.combined);
		batch.begin();
		batch.draw(treeTop, -treeCamera.viewportWidth / 2, treeCamera.viewportHeight / 2 - 16);
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
		
		Gdx.input.setInputProcessor(player);
	}
	
	@Override
	public void resize(int width, int height) {
		treeCamera.viewportWidth = Constants.TEST_WIDTH;
		treeCamera.viewportHeight = Constants.TEST_HEIGHT;
		treeCamera.update();
		System.out.println("GameScreen resized");
		super.resize(width, height);
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
					
					game.setToKillScreen(KillScreen.SHOT);
				}
			}
		}
		
		
	}
	
	private void drawText (SpriteBatch batch) {
		batch.setProjectionMatrix(fontCamera.combined);
		batch.begin();
		Assets.scoreFont.draw(batch, "Score: " + (int)(this.timeInGame), -fontCamera.viewportWidth / 2 * .97f, fontCamera.viewportHeight / 2 * .96f);
		Assets.scoreFont.draw(batch, "Dishonor: " + spawner.getArrowsLeft(), fontCamera.viewportWidth / 2 * .5f, fontCamera.viewportHeight / 2 * .96f);
		batch.end();
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
