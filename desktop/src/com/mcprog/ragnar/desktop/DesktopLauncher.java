package com.mcprog.ragnar.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mcprog.ragnar.Ragnar;
import com.mcprog.ragnar.lib.Assets;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 640;
		config.resizable = false;
		config.addIcon("icon/icon-128.png", FileType.Internal);
		config.addIcon("icon/icon-16.png", FileType.Internal);
		config.addIcon("icon/icon-32.png", FileType.Internal);
		new LwjglApplication(new Ragnar(), config);
	}
}
