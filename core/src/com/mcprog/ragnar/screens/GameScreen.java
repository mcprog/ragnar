package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import com.mcprog.ragnar.gui.MobileControls;
import com.mcprog.ragnar.gui.tables.GameTable;
import com.mcprog.ragnar.gui.tables.PauseTable;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.Constants;
import com.mcprog.ragnar.lib.RagnarConfig;
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
	private PauseTable pauseTable;
	
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
    private boolean justPaused;
	private InputMultiplexer inputMultiplexer;
	private ShapeRenderer shapeRenderer;
	private String actionToResume;
	
	public GameScreen(Ragnar gameInstance) {
		super(gameInstance);
		
		bodies = new Array<Body>();
		
		if (Ragnar.isMobile) {
			actionToResume = "Hold";
		} else {
			actionToResume = "Click";
		}
		
		bodiesToDelete = new Array<Body>();
		
		mobileControls = new MobileControls();
		
		treeBatch = new SpriteBatch();
		treeCamera = new OrthographicCamera();
		
		arrowHit = Gdx.audio.newSound(Gdx.files.internal("sounds/arrow-hit.mp3"));
		stage = new Stage();
		stage.setViewport(new ExtendViewport(Constants.IDEAL_WIDTH, Constants.IDEAL_HEIGHT));
		table = new GameTable();
		stage.addActor(table);
		
		shapeRenderer = new ShapeRenderer();
		pauseTable = new PauseTable(this);
		stage.addActor(pauseTable);
		
		
		inputMultiplexer = new InputMultiplexer();
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		updateAlways(delta);
		
		if (gamePaused) {
            if (justPaused()) {
                pauseTable.showPaused();
                setJustPaused(false);
            }
			stage.act(delta);
			stage.draw();
		} else {
            if (justPaused()) {
                pauseTable.show();
                setJustPaused(false);
            }
			updateRunning(delta);
		}
	}
	
	protected void updateAlways (float delta) {
		if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            setGameToPaused(true);
            setJustPaused(true);
        }
        Gdx.gl.glClearColor(.15f, .4f, .15f, 1);//Black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(treeCamera.combined);
		batch.begin();
		batch.draw(Assets.treeLeft, -treeCamera.viewportWidth / 2 - 16, -treeCamera.viewportHeight / 2);
		batch.draw(Assets.treeLeft, treeCamera.viewportWidth / 2 - 16, -treeCamera.viewportHeight / 2);
		batch.draw(Assets.treeTop, -treeCamera.viewportWidth / 2, treeCamera.viewportHeight / 2 - 16);
		batch.draw(Assets.treeTop, -treeCamera.viewportWidth / 2, -treeCamera.viewportHeight / 2 - 16);
		batch.end();
	}
	
	protected void updateRunning (float delta) {
		timeInGame += delta;
		stateTime += delta;
		world.step(1/60f, 8, 3);
		safelyDestroyBodies();
		world.getBodies(bodies);
		player.update(delta);
		handleBarrierDeath();
		spawner.spawn(delta);
		batch.setProjectionMatrix(camera.combined);
		table.update(timeInGame, spawner.getArrowsLeft());
		stage.act(delta);
		stage.draw();
		player.draw(stateTime, batch);
		Arrow.drawArrows(batch, bodies);
		drawText(fontBatch);
		Ragnar.debugger.addDebug("FPS", Gdx.graphics.getFramesPerSecond());
		Ragnar.debugger.addDebug("Control Angle", (int) (MathUtils.radiansToDegrees * player.dragAngle));
		Ragnar.debugger.renderDebug(world, camera.combined);
		mobileControls.update(delta, player.dragVector);
	}

	@Override
	public void show() {
		inputMultiplexer.clear();
		timeInGame = 0;
		world = new World(Vector2.Zero, true);
		player = new Player(world, Vector2.Zero, camera, this);
        player.init();
		spawner = new ArrowSpawner(world, player);
		bounds = new Bounds(world, Gdx.graphics.getWidth() / 8);

		world.setContactListener(this);
		if (Ragnar.debugger.on) {
			stage.setDebugAll(true);
		}
		gamePaused = false;
        pauseTable.show();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(player);
		Gdx.input.setInputProcessor(inputMultiplexer);
        if (Ragnar.isMobile) {
            game.gpgs.unlockAchievement(1);
        }
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
	    setGameToPaused(true);
        setJustPaused(true);
	}
	
	@Override
	public void resume() {
	}
	
	public boolean isPaused () {
		return gamePaused;
	}

    public boolean justPaused () {
        return  justPaused;
    }

    public void setJustPaused (boolean state) {
        justPaused = state;
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
	
	private void handleBarrierDeath () {
		if (player.getBody().getWorldCenter().x - player.getHalfWidth() < -camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().x + player.getHalfWidth() > camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().y + player.getHalfHeight() > camera.viewportHeight / 2 
				|| player.getBody().getWorldCenter().y - player.getHalfHeight() < -camera.viewportHeight / 2) {
			if (spawner.getWin()) {
				game.setScreen(game.winScreen);
			} else {
				if (RagnarConfig.sound) {
					
					arrowHit.play();
				}
				if (Ragnar.isMobile && RagnarConfig.vibrate) {
					Gdx.input.vibrate(300);
				}
				game.setToKillScreen(KillScreen.STABBED);
			}
		}
	}

    public void setGameToPaused (boolean state) {
        gamePaused = state;
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
					if (RagnarConfig.sound) {
						
						arrowHit.play();
					}
					if (Ragnar.isMobile) {
						Gdx.input.vibrate(300);
					}
					game.setToKillScreen(KillScreen.SHOT);
				}
			}
		}
		
		
	}
	
	private void drawText (SpriteBatch batch) {
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
