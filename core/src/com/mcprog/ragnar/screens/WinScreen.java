package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.world.Angel;
import com.mcprog.ragnar.world.Bounds;

public class WinScreen extends ScreenDrawable {
	
	private World world;
	private Angel angel;
	private float stateTime;
	private Bounds bounds;
	private Sprite leftSidebar;
	private Sprite rightSidebar;
	private SpriteBatch fontBatch;
	private ShapeRenderer shapeRenderer;
	
	public WinScreen(Ragnar game) {
		super(game);
		world = new World(new Vector2(0, -9.81f), true);
		angel = new Angel(world, Vector2.Zero, camera);
		bounds = new Bounds(world, Gdx.graphics.getWidth() / 32f, Gdx.graphics.getHeight() / 32f);
		leftSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
		rightSidebar = new Sprite(new Texture(Gdx.files.internal("heaven_sidebar.png")));
		fontBatch = new SpriteBatch();
		Gdx.input.setInputProcessor(angel);
		Gdx.input.setCatchBackKey(true);
	}

    @Override
    public void show() {
        if (Ragnar.isMobile) {
            game.gpgs.unlockAchievement(4);
        }
    }

    @Override
	public void render(float delta) {
		super.render(delta);
		Gdx.gl.glClearColor(0, .69f, 1, 1);//Color of Valhalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Keys.BACK)) {
			game.setScreen(game.gameScreen);
		}
		
		stateTime += delta;
		world.step(1/60f, 8, 3);
		angel.update(delta);
		
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
//		Assets.ragnarFont.draw(fontBatch, "You Win Entrance to Valhalla", -fontCamera.viewportWidth / 4, fontCamera.viewportHeight / 4);
		Assets.ragnarFont.drawWrapped(fontBatch, "You Win Entrance to Valhalla", -fontCamera.viewportWidth * .375f, fontCamera.viewportHeight * .375f, fontCamera.viewportWidth * .75f, HAlignment.CENTER);
		Assets.scoreFont.drawWrapped(fontBatch, "Score " + (int)(game.gameScreen.timeInGame) + " seconds", -fontCamera.viewportWidth * .375f, -fontCamera.viewportHeight * .375f, fontCamera.viewportWidth * .75f, HAlignment.CENTER);
		fontBatch.end();
		
		angel.draw(stateTime, batch);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (Gdx.input.isKeyPressed(Keys.W)) {
			angel.getGust().draw(batch);
		}
		leftSidebar.draw(batch);
		rightSidebar.draw(batch);
		batch.end();
	}
	
//	@Override
//	public void resize(int width, int height) {
//		super.resize(width, height);
//		camera.viewportWidth = width / 16;
//		camera.viewportHeight = height / 16;
//		camera.update();
//		leftSidebar.setBounds(-camera.viewportWidth / 2, -camera.viewportHeight / 2, (camera.viewportWidth - camera.viewportHeight) / 2, camera.viewportHeight);
//		rightSidebar.setBounds(camera.viewportWidth / 2 - (camera.viewportWidth - camera.viewportHeight) / 2, -camera.viewportHeight / 2, (camera.viewportWidth - camera.viewportHeight) / 2, camera.viewportHeight);
//	}
	
	

}
