package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.gui.GuiStyles;

public class GameTable extends RagnarTable {
	
	private Label score;
	private Label dishonor;
	private TextButton pause;
	
	public GameTable() {
		GuiStyles.init();
		
		score = new Label("Score: ", GuiStyles.tinyLabelStyleWhite);
		dishonor = new Label("Dishonor:", GuiStyles.tinyLabelStyleWhite);
		pause = new TextButton("||", GuiStyles.smallButtonStyle);
		
		add(score).left().expandX();
		add(dishonor).right().expandX();
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
	
	public void update (float score, int dishonor) {
		this.score.setText("Score: " + (int)score);
		this.dishonor.setText("Dishonor: " + dishonor);
	}

}
