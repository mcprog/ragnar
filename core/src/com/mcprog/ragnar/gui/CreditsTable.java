package com.mcprog.ragnar.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;

public class CreditsTable extends Table {
	
	private Label header;
	private ScrollPane scrollPane;
	private Table innerTable;
	private Label resourcesHeader;
	private PlayButton playButton;
	private MenuButton menuButton;
	
	public CreditsTable() {
		header = new Label("Credits", GuiStyles.headerLabelStyle);
		
		resourcesHeader = new Label("Resources", GuiStyles.normalLabelStyle);
		
		playButton = new PlayButton();
		menuButton = new MenuButton();
		
		innerTable = new Table();
		scrollPane = new ScrollPane(innerTable);
		
		addCredits("Creator", "mcprog", innerTable);
		innerTable.add(resourcesHeader).colspan(2).pad(10).padTop(20);
		innerTable.row();
		addCredits("Death Sound", "brquoon", innerTable);
		addCredits("Tree Textures", "Reiner \"Tiles\" Prokein", innerTable);
		addCredits("Font:", "DeNada Industries", innerTable);
		
		add(header).colspan(2);
		row();
		add(scrollPane).colspan(2).height(200);
		row();
		add(playButton).pad(20);
		add(menuButton).pad(20);
	}
	
	protected void addCredits (String key, String val, Table toAddTo) {
		Label labelKey = new Label(key, GuiStyles.normalLabelStyle);
		Label labelVal = new Label(val, GuiStyles.normalLabelStyle);
		
		toAddTo.add(labelKey).left();
		toAddTo.add(labelVal).right().padLeft(20);
		toAddTo.row();
	}

}
