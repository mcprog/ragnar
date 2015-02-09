package com.mcprog.ragnar.utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class DebugUtility {
	
	private Box2DDebugRenderer box2DRenderer;
	private FPSLogger fpsLogger;
	private Map<String, Integer> debugFields;
	private HashMap<String, Float> timers;
	
	public boolean on;
	
	public DebugUtility() {
		box2DRenderer = new Box2DDebugRenderer();
		fpsLogger = new FPSLogger();
		debugFields = new HashMap<String, Integer>();
		timers = new HashMap<String, Float>();
	}
	
	public void renderDebug (World world, Matrix4 projection) {
		if (!on) {
			return;
		}
		fpsLogger.log();
		box2DRenderer.render(world, projection);
	}
	
	public void handleInput (Ragnar game) {
		if (!on) {
			return;
		}
		if (Gdx.input.isKeyJustPressed(Keys.H)) {
			game.setScreen(game.winScreen);
		}
		if (Gdx.input.isKeyJustPressed(Keys.K)) {
			game.setScreen(game.killScreen);
		}
		if (Gdx.input.isKeyJustPressed(Keys.G)) {
			game.setScreen(game.gameScreen);
		}
	}
	
	public void textDebug (SpriteBatch batch, OrthographicCamera camera) {
		if (!on) {
			return;
		}
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		int i = 0;
		Iterator<Map.Entry<String, Integer>> iterator =  debugFields.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> field = iterator.next();
			Assets.scoreFont.draw(batch, field.getKey() + ": " + field.getValue(), -camera.viewportWidth / 2 * .97f, camera.viewportHeight / 2 * .9f - (camera.viewportHeight / 2 * .1f * i));
			++i;
		}
		debugFields.clear();
		batch.end();
	}
	
	public void logDebug (String txt) {
		Gdx.app.log(">DEBUG<", txt);
	}
	
	public void addDebug (String label, int val) {
		debugFields.put(label, val);
	}
	
	public void setTimer (String label, float newTime) {
		if (!on) {
			return;
		}
		timers.put(label, newTime);
	}
	
	public float getTimeFromTimer (String label) {
		return timers.get(label);
	}
	
	public void addTimeToTimer (String label, float delta) {
		if (!on) {
			return;
		}
		timers.put(label, getTimeFromTimer(label) + delta);
	}
	
	public void logTimer (String before, String label, String suffix) {
		if (!on) {
			return;
		}
		logDebug(before + " " + getTimeFromTimer(label) + " ");
	}
	
	public void on () {
		on = true;
	}
	
	public void off () {
		on = false;
	}

}
