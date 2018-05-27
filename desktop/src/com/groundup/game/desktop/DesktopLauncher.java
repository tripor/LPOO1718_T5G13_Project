package com.groundup.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groundup.game.GroundUpGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1024;
		config.height=768;
		config.title="Ground Up";
		config.forceExit=false;
		//config.fullscreen=true;
		new LwjglApplication(new GroundUpGame(), config);
	}
}
