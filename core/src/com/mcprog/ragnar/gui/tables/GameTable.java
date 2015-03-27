package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;

public class GameTable extends RagnarTable {
	
	private Label score;
	private Label honor;
	private TextButton pause;
	
	public GameTable() {
		GuiStyles.init();
		
		score = new Label("Score: ", GuiStyles.tinyLabelStyleWhite);
		honor = new Label("Honor:", GuiStyles.tinyLabelStyleWhite);
		pause = new TextButton("||", GuiStyles.smallButtonStyle);
		
		add(score).left().expandX();
		add(honor).right().expandX();
		row();
		
		pad(60);
		top();
		
		pause.addListener(new ClickListener () {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (Ragnar.gameInstance.gameScreen.isPaused()) {
					Ragnar.gameInstance.gameScreen.resume();
				} else {
					Ragnar.gameInstance.gameScreen.pause();
				}
			}
		});
		
	}
	
	public void pause () {
		pause.setText("|>");
	}
	
	public void update (int score, int limit) {
		this.score.setText("Score: " + score);
		this.honor.setText("Honor: " + Math.min(score, limit) + "/200");
	}

}
