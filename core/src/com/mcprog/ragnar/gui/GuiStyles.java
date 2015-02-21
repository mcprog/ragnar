package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mcprog.ragnar.lib.Assets;

public class GuiStyles {
	
	public static LabelStyle headerLabelStyle;
	public static LabelStyle normalLabelStyle;
	public static LabelStyle headerLabelStyleWhite;
	public static LabelStyle normalLabelStyleWhite;
	public static TextButtonStyle largeButtonStyle;
	public static TextButtonStyle largeButtonStyleLight;
	public static TextButtonStyle smallButtonStyle;
	
	public static void init () {
		headerLabelStyle = new LabelStyle(Assets.ragnarFont, Color.DARK_GRAY);
		normalLabelStyle = new LabelStyle(Assets.smallFont, Color.DARK_GRAY);
		headerLabelStyleWhite = new LabelStyle(Assets.ragnarFont, Color.WHITE);
		normalLabelStyleWhite = new LabelStyle(Assets.smallFont, Color.WHITE);
		
		largeButtonStyle = new TextButtonStyle();
		largeButtonStyle.fontColor = Color.DARK_GRAY;
		largeButtonStyle.downFontColor = Color.LIGHT_GRAY;
		largeButtonStyle.overFontColor = Color.LIGHT_GRAY;
		largeButtonStyle.font = Assets.scoreFont;
		
		largeButtonStyleLight = new TextButtonStyle();
		largeButtonStyleLight.fontColor = Color.LIGHT_GRAY;
		largeButtonStyleLight.downFontColor = Color.WHITE;
		largeButtonStyleLight.overFontColor = Color.WHITE;
		largeButtonStyleLight.font = Assets.scoreFont;
		
		smallButtonStyle = new TextButtonStyle();
		smallButtonStyle.fontColor = Color.DARK_GRAY;
		smallButtonStyle.downFontColor = Color.LIGHT_GRAY;
		smallButtonStyle.overFontColor = Color.LIGHT_GRAY;
		smallButtonStyle.font = Assets.smallFont;
	}

}
