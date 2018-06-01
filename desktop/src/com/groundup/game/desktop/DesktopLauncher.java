package com.groundup.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groundup.game.GroundUpGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1920/2;
		config.height=1080/2;
		config.title="Ground Up";
		new LwjglApplication(new GroundUpGame(), config);
	}
}
