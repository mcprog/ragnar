package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.lib.Assets;

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
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPane.setStyle(scrollPaneStyle);
		
		addCreditsHeader("Developers", innerTable);
		addCredits("Creator:", "mcprog", innerTable);
		
		addCreditsHeader("Resources", innerTable);
		addCredits("Death Sound:", "braqoon", innerTable);
		addCredits("Tree Textures:", "Reiner \"Tiles\" Prokein", innerTable);
		addCredits("Font:", "DeNada Industries", innerTable);
		
		addCreditsHeader("Shout Out", innerTable);
		addShoutOut("CSWS", innerTable);
		addShoutOut("History Channel's Vikings", innerTable);
		addShoutOut("ime990", innerTable);
		addShoutOut("Kai_Valhalla", innerTable);
		addShoutOut("Scandinavia", innerTable);
		
		add(header).colspan(2);
		row();
		add(scrollPane).colspan(2).height(300);
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
	
	protected void addShoutOut (String name, Table toAddTo) {
		Label labelShoutOut = new Label(name, GuiStyles.normalLabelStyle);
		
		toAddTo.add(labelShoutOut).colspan(2);
		toAddTo.row();
	}
	
	protected void addCreditsHeader (String header, Table toAddTo) {
		Label labelHeader = new Label(header, GuiStyles.normalLabelStyle);
		
		toAddTo.add(labelHeader).colspan(2).padTop(40);
		toAddTo.row();
	}

}
