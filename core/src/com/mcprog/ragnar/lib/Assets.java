package com.mcprog.ragnar.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class Assets {
	
	public static AssetManager assetManager = new AssetManager();
	
	private static TextureRegion[][] map;
	public static Animation[] playerAnimations;
	public static Animation[] playerGirlAnimations;
    public static Animation wingsAnimation;
	
	public static BitmapFont ragnarFont;
	public static BitmapFont scoreFont;
	public static BitmapFont smallFont;
	public static BitmapFont tinyFont;
	
	public static Sprite arrowSprite;
	public static Sprite deadPlayerSprite;
	public static Sprite deadPlayerStabbedSprite;
	public static Sprite deadPlayerGirlSprite;
	public static Sprite deadPlayerStabbedGirlSprite;
    public static Sprite meteorSprite;
	
	public static Texture treeTop;
	public static Texture treeLeft;
	
	public static final String PLAYER_PATH = "player.png";
	public static final String PLAYER_GIRL_PATH = "player-girl.png";
	public static final String ARROW_PATH = "arrow.png";
	public static final String DEAD_PLAYER_PATH = "dead-player.png";
	public static final String DEAD_PLAYER_STABBED_PATH = "dead-player-stabbed.png";
	public static final String DEAD_PLAYER_GIRL_PATH = "dead-player-girl.png";
	public static final String DEAD_PLAYER_STABBED_GIRL_PATH = "dead-player-stabbed-girl.png";
    public static final String METEOR_PATH = "meteor.png";
	
	public static final String TREE_TOP_PATH = "tree-top.png";
	public static final String TREE_LEFT_PATH = "tree-left.png";
    public static final String WINGS_PATH = "wings.png";
	
	public static final String FONT_DIR = "font/";
	public static final String VIKING_FONT_PATH = FONT_DIR + "viking128.fnt";
	public static final String SCORE_FONT_PATH = FONT_DIR + "viking64.fnt";
	public static final String SMALL_FONT_PATH = FONT_DIR + "viking32.fnt";
	public static final String TINY_FONT_PATH = FONT_DIR + "viking16.fnt";
	
	public static I18NBundle mainBundle;
	
	public static void testLoadI18N () {
		
		switch (Locale.getDefault().toString()) {
		case "es":
			mainBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/MainBundle_es"), new Locale("es"));
			break;
		default:
			mainBundle = I18NBundle.createBundle(Gdx.files.internal("i18n/MainBundle"), new Locale("en"));
			break;
		}
	}
	
	public static void queueAll () {

		queueFonts();
		queueTextures();
	}
	
	public static void queueTextures () {
		assetManager.load(PLAYER_PATH, Texture.class);
		assetManager.load(PLAYER_GIRL_PATH, Texture.class);
		assetManager.load(ARROW_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_STABBED_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_GIRL_PATH, Texture.class);
		assetManager.load(DEAD_PLAYER_STABBED_GIRL_PATH, Texture.class);
		assetManager.load(TREE_TOP_PATH, Texture.class);
		assetManager.load(TREE_LEFT_PATH, Texture.class);
        assetManager.load(WINGS_PATH, Texture.class);
        assetManager.load(METEOR_PATH, Texture.class);
	}
	
	public static void queueFonts () {
		assetManager.load(VIKING_FONT_PATH, BitmapFont.class);
		assetManager.load(SCORE_FONT_PATH, BitmapFont.class);
		assetManager.load(SMALL_FONT_PATH, BitmapFont.class);
		assetManager.load(TINY_FONT_PATH, BitmapFont.class);
	}
	
	public static Texture getLoadedTexture (String path) {
		return assetManager.get(path);
	}
	
	public static BitmapFont getLoadedFont (String path) {
		return assetManager.get(path);
	}

    public static Animation loadWings () {
        map = TextureRegion.split(getLoadedTexture(WINGS_PATH), 24, 24);

        Array<TextureRegion> holder = new Array<TextureRegion>();
        holder.add(map[0][0]);
        holder.add(map[1][0]);
        holder.add(map[2][0]);
        return new Animation(.2f, holder, Animation.PlayMode.LOOP_PINGPONG);
    }
	
	public static Animation[] loadAnimation (String path) {
		map = TextureRegion.split(getLoadedTexture(path), 16, 24);
		
		/*
		 * Pseudo animations (TextureRegions that fit in array)
		 */
		TextureRegion[] holder = new TextureRegion[1];
		holder[0] = map[0][0];
		Animation idleRight = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[1][0];
		Animation idleLeft = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[2][0];
		Animation idleUp = new Animation(1, holder);
		holder = new TextureRegion[1];//Needs to be initialized every time
		holder[0] = map[3][0];
		Animation idleDown = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[0][5];
		Animation blockingRight = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[1][5];
		Animation blockingLeft = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[2][4];
		Animation blockingUp = new Animation(1, holder);
		holder = new TextureRegion[1];
		holder[0] = map[3][4];
		Animation blockingDown = new Animation(1, holder);
		/*
		 * Now the real animations start
		 */
		holder = new TextureRegion[4];
		holder[0] = map [0][1];
		holder[1] = map [0][2];
		holder[2] = map [0][3];
		holder[3] = map [0][4];
		Animation walkingRight = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [1][1];
		holder[1] = map [1][2];
		holder[2] = map [1][3];
		holder[3] = map [1][4];
		Animation walkingLeft = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [2][0];
		holder[1] = map [2][1];
		holder[2] = map [2][2];
		holder[3] = map [2][3];
		Animation walkingUp = new Animation(.25f, holder);
		holder = new TextureRegion[4];
		holder[0] = map [3][0];
		holder[1] = map [3][1];
		holder[2] = map [3][2];
		holder[3] = map [3][3];
		Animation walkingDown = new Animation(.25f, holder);
		
		Animation[] animations = {
				walkingLeft,
				walkingRight, 
				walkingUp,
				walkingDown,
				idleLeft,
				idleRight, 
				idleUp,
				idleDown, 
				blockingLeft, 
				blockingRight, 
				blockingUp, 
				blockingDown 
				};
		return animations;
	}
	
	public static void assignLoadedAssets () {
		scoreFont = getLoadedFont(SCORE_FONT_PATH);
		smallFont = getLoadedFont(SMALL_FONT_PATH);
		tinyFont = getLoadedFont(TINY_FONT_PATH);
		arrowSprite = new Sprite(getLoadedTexture(ARROW_PATH));
		deadPlayerSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_PATH));
		deadPlayerStabbedSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_STABBED_PATH));
		deadPlayerGirlSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_GIRL_PATH));
		deadPlayerStabbedGirlSprite = new Sprite(getLoadedTexture(DEAD_PLAYER_STABBED_GIRL_PATH));
		treeTop = getLoadedTexture(TREE_TOP_PATH);
		treeLeft = getLoadedTexture(TREE_LEFT_PATH);
        meteorSprite = new Sprite(getLoadedTexture(METEOR_PATH));
        meteorSprite.setSize(1, 1);
	}
	
	public static float getLoadingProgress () {
		return assetManager.getProgress() * 100;
	}
}
