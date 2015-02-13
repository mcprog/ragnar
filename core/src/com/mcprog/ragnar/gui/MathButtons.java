package com.mcprog.ragnar.gui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MathButtons {
	
	private Rectangle rect;
	private Sprite image;
	
	public MathButtons (float x, float y, float width, float height) {
		rect = new Rectangle(x, y, width, height);
		image.setBounds(x, y, width, height);
	}
	
	public void setCenter (float x, float y) {
		rect.setCenter(x, y);
		image.setCenter(x, y);
	}
	
	
	public boolean isWithInBounds (float x, float y) {
		return rect.contains(x, y);
	}
	
	public void render (SpriteBatch batch) {
		
	}

}
