package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.MenuButton;
import com.mcprog.ragnar.gui.buttons.PlayButton;

public class CreditsTable extends NavigationTable {
	
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
        Texture bg = new Texture(Gdx.files.internal("border.png"));
        /*TextureRegion leftCorner = new TextureRegion(bg, 0, 0, 16, 16);
        TextureRegion top = new TextureRegion(bg, 16, 0, 64, 16);
        Texture topTex = top;*/
        scrollPaneStyle.background = new NinePatchDrawable(new NinePatch(new TextureRegion(bg), 16, 16, 16, 16));
        scrollPaneStyle.vScrollKnob = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal("knob.png")), 3, 3, 3, 3));
        scrollPane.setFadeScrollBars(false);
		
		addCreditsHeader("Developers", innerTable);
		addCredits("Creator:", "mcprog", innerTable);
		
		addCreditsHeader("Resources", innerTable);
		addCredits("Death Sound:", "braqoon", innerTable);
		addCredits("Trees:", "Reiner \"Tiles\" Prokein", innerTable);
		addCredits("Font:", "DeNada Industries", innerTable);
		
		addCreditsHeader("Shout Out", innerTable);
		addShoutOut("CSWS", innerTable);
		addShoutOut("History Channel's Vikings", innerTable);
		addShoutOut("ime990", innerTable);
		addShoutOut("Kai_Valhalla", innerTable);
		addShoutOut("Scandinavia", innerTable);
		
		add(header).colspan(2);
		row();
		add(scrollPane).colspan(2).height(300).width(900);
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
