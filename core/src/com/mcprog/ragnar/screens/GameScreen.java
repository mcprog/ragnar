/*
 * Copyright 2015 Michael Curtis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    /*
    Physics and world
     */
	private World world;
    private Bounds bounds;
	private Player player;
	private ArrowSpawner spawner;
	private Array<Body> bodies;
	private Array<Body> bodiesToDelete;

    /*
    UI and Control
     */
    private Stage stage;
	private GameTable table;
	private PauseTable pauseTable;
    private MobileControls mobileControls;
    private InputMultiplexer inputMultiplexer;

    /*
    Timing and score
     */
	private float stateTime;
    public float timeInGame;
    private int winTime = 200;
    private boolean gamePaused;
    private boolean justPaused;

    /*
    Resources
     */
	private Texture treeTop;
	private Texture treeLeft;
	private SpriteBatch treeBatch;
	private OrthographicCamera treeCamera;
	private Sound arrowHit;

    /**
     * Instantiates the fields
     * Sets up stage and adds tables to it
     * @param gameInstance: Ragnar instance
     */
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
		stage.addActor(table);
		pauseTable = new PauseTable(this);
		stage.addActor(pauseTable);
		
		
		inputMultiplexer = new InputMultiplexer();
	}

    /**
     * Called every frame
     * Always calls the updateAlways() method
     * Handles pausing and call updateRunning() is not paused
     * @param delta
     */
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

    /**
     * Renders what always needs to be rendered
     * Pauses when back button is pressed
     * Draws GL stuff
     * Draws trees
     * @param delta: time passed from last frame in seconds (unused here)
     */
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

    /**
     * Renders what needs to be rendered when game is NOT paused
     * Updates timers
     * Steps world and updates physics in the world
     * Draws arrows and player
     * Renders debugger
     * @param delta: time passed from last frame in seconds (used here)
     */
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
		table.update((int)timeInGame, winTime);
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

    /**
     * Called when this screen is focused on
     * Renews physics objects
     * Renews input
     * Mobile unlocks play for the 1st time achievement
     */
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
            //game.adRefresher.hideBanner();
        }
	}

    /**
     * Updates cameras to adjust to current screen size
     * Resizes stage
     * @param width: current width of screen
     * @param height: current height of screen
     */
	@Override
	public void resize(int width, int height) {
        //TODO test width should be tree width etc
		treeCamera.viewportWidth = Constants.TEST_WIDTH;
		treeCamera.viewportHeight = Constants.TEST_HEIGHT;
		treeCamera.update();
		super.resize(width, height);
		stage.getViewport().update(width, height, true);
	}

    /**
     * Called when window lose focus or android process is hid
     * Pauses game
     */
	@Override
	public void pause() {
	    setGameToPaused(true);
        setJustPaused(true);
	}

    /**
     * Getter for the gamePaused field
     * @return the value of gamePaused
     */
	public boolean isPaused () {
		return gamePaused;
	}

    /**
     * Setter for gamePaused field
     * Sets whether game should be paused or not
     * @param state: state the gamePause field should be set to
     */
    public void setGameToPaused (boolean state) {
        gamePaused = state;
    }

    /**
     * Getter for justPaused field
     * Determines whether game was paused in the same frame as this method is called
     * @return if the game was just paused
     */
    public boolean justPaused () {
        return  justPaused;
    }

    /**
     * Setter for justPaused field
     * Sets whether the game was just pause in the current frame
     * @param state: value to set justPaused to
     */
    public void setJustPaused (boolean state) {
        justPaused = state;
    }

    /**
     * Safely destroys arrow that have been added to an array of bodies to remove
     * These are arrows that have either hit each other, the player, or the outside bounds
     * <li>1. sets the bodies inactive</li>
     * <li>2. destroys the inactive bodies</li>
     * This means that it takes 2 frames to completely delete the bodies
     */
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

    /**
     * Checks to make sure player has not gone offscreen<br>
     * Kills the player is player goes off<br>
     * Player enters valhalla is qualified<br>
     * Sounds and vibrates if settings permit<br>
     * Death type is stabbed
     */
	private void handleBarrierDeath () {
		if (player.getBody().getWorldCenter().x - player.getHalfWidth() < -camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().x + player.getHalfWidth() > camera.viewportWidth / 2 
				|| player.getBody().getWorldCenter().y + player.getHalfHeight() > camera.viewportHeight / 2 
				|| player.getBody().getWorldCenter().y - player.getHalfHeight() < -camera.viewportHeight / 2) {
			if (timeInGame > winTime) {
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


    /**
     * Gets the honor for display in the game table<br>
     * Caps honor to the max honor<br>
     * Apparently unused
     * @return the lesser of the honor limit and the score
     */
    private int getHonor () {
        return Math.min(winTime, (int)timeInGame);
    }

    /**
     * Called whenever two physics objects collide<br>
     * Adds arrows that collide to arrays of bodies that are to be deleted<br>
     * Handles player dies from being shot<br>
     * This is a time locked method so the bodies cannot be deleted here
     * @param contact
     */
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
				if (timeInGame > winTime) {
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

    /**
     * Just used to render debug information
     * @param batch: batch to draw info with
     */
	private void drawText (SpriteBatch batch) {
		Ragnar.debugger.textDebug(fontBatch, fontCamera);
	}

    /**
     * Called when physics objects stop contacting<br>
     *     I don't use this since I just destroy bodies
     * @param contact
     */
	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
