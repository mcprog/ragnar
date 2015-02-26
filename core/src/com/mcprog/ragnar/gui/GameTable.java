package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.Ragnar;

public class GameTable extends Table {
	
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
		add(pause);
		
		pad(60);
		top();
		
	}
	
	
	public void update (float score, int dishonor) {
		this.score.setText("Score: " + (int)score);
		this.dishonor.setText("Dishonor: " + dishonor);
		if (pause.isPressed()) {
			Ragnar.gameInstance.gameScreen.pause();
		}
	}

}
