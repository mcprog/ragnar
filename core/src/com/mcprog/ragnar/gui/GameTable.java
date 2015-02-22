package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameTable extends Table {
	
	private Label score;
	private Label dishonor;
	
	public GameTable() {
		GuiStyles.init();
		
		score = new Label("Score: ", GuiStyles.tinyLabelStyleWhite);
		dishonor = new Label("Dishonor:", GuiStyles.tinyLabelStyleWhite);
		
		add(score).left().expandX();
		add(dishonor).right().expandX();
		
		pad(60);
//		padTop(60);
		top();
	}
	
	public void update (float score, int dishonor) {
		this.score.setText("Score: " + (int)score);
		this.dishonor.setText("Dishonor: " + dishonor);
	}

}
