package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
		
		largeButtonStyle = makeTextButtonStyle(Assets.scoreFont, Color.DARK_GRAY, Color.LIGHT_GRAY);
		largeButtonStyleLight = makeTextButtonStyle(Assets.scoreFont, Color.LIGHT_GRAY, Color.WHITE);
		smallButtonStyle = makeTextButtonStyle(Assets.smallFont, Color.DARK_GRAY, Color.LIGHT_GRAY);
	}
	
	private static TextButtonStyle makeTextButtonStyle (BitmapFont font, Color color, Color active) {
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font = font;
		tbs.fontColor = color;
		tbs.downFontColor = active;
		tbs.overFontColor = active;
		return tbs;
	}

}
