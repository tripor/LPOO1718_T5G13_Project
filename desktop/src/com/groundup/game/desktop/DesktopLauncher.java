package com.groundup.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import graphic.GroundUpGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=400;
		config.height=300;
		config.title="Ground Up";
		//config.fullscreen=true;
		new LwjglApplication(new GroundUpGame(), config);
	}
}
