package com.mcprog.ragnar.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.lib.Assets;
import com.mcprog.ragnar.lib.RagnarConfig;

public class SettingsScreen extends ScreenDrawable {

	private Table table;
	private Stage stage;
	private Label header;
	private Label playerLabel;
	private Label highscoreLabel;
	private Label highscore;
	private LabelStyle labelHeaderStyle;
	private LabelStyle labelStyle;
	private TextButton buttonBack;
	private TextButton buttonPlayerType;
	private TextButtonStyle buttonStyle;
	private TextButtonStyle buttonSmallStyle;
	private ShapeRenderer debugLines;
	
	public SettingsScreen(Ragnar game) {
		super(game);
		table = new Table();
		table.setFillParent(true);
		debugLines = new ShapeRenderer();
		stage = new Stage();
		
		GuiStyles.init();
		
		buttonBack = new TextButton("Back", GuiStyles.largeButtonStyle);
		buttonBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Ragnar.gameInstance.setScreen(Ragnar.gameInstance.gameScreen);
			}
		});
		
		buttonPlayerType = new TextButton(getPlayerType(), GuiStyles.smallButtonStyle);
		buttonPlayerType.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (RagnarConfig.playerType == 0) {
					RagnarConfig.playerType = 1;
					RagnarConfig.updateFile();
				} else {
					RagnarConfig.playerType = 0;
					RagnarConfig.updateFile();
				}
				buttonPlayerType.setText(getPlayerType());
			}
		});
		
		header = new Label("Settings", GuiStyles.headerLabelStyle);
		playerLabel = new Label("Player Type:", GuiStyles.normalLabelStyle);
		highscoreLabel = new Label("Highscore:", GuiStyles.normalLabelStyle);
		highscore = new Label(Integer.toString(RagnarConfig.highScore), GuiStyles.normalLabelStyle);
		
		table.add(header).colspan(2);
		table.row();
		table.add(playerLabel).left();
		table.add(buttonPlayerType).right().pad(20);
		table.row();
		table.add(highscoreLabel).left();
		table.add(highscore).right().padRight(20);
		table.row();
		table.add(buttonBack).colspan(2).pad(20);
		stage.addActor(table);
		stage.setDebugAll(true);
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		highscore.setText(Integer.toString(RagnarConfig.highScore));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		fontBatch.setProjectionMatrix(fontCamera.combined);
		fontBatch.begin();
		Assets.ragnarFont.draw(fontBatch, "Settings", 0, fontCamera.viewportWidth / 4);
		Assets.ragnarFont.draw(fontBatch, "Player type: " + getPlayerType(), -fontCamera.viewportWidth / 4, 0);
		Assets.ragnarFont.draw(fontBatch, "Highscore: " + RagnarConfig.highScore, -fontCamera.viewportWidth / 4, -fontCamera.viewportHeight / 4);
		fontBatch.end();
		
		stage.act(delta);
		stage.draw();
		
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
	}
	
	public String getPlayerType () {
		return RagnarConfig.playerType == 0 ? "Ragnar" : "Lagertha";
	}

}
