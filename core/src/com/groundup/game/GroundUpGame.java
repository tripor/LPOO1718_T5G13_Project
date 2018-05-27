package com.groundup.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
/**
 * 
 * @author figor
 *
 */
public class GroundUpGame extends Game {
	/**
	 * Manages the game assets
	 */
	private AssetManager assetManager;
	/**
	 * The screen of the game
	 */
	private ScreenAdapter GameScreen;

	@Override
	public void create() {
		this.assetManager = new AssetManager();
		GameScreen= new MenuScreen(this);
		this.setScreen(GameScreen);
	}

	/**
	 * Gets the assetManager of GroundUpGame
	 * @return the game assetManager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Sets the game AssetManager
	 * @param assetManager the game assetManager I want to change to
	 */
	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}
	/**
	 * 
	 * @return Returns the game screen
	 */
	public ScreenAdapter getGameScreen() {
		return GameScreen;
	}
	/**
	 * Sets the screen
	 * @param screen The screen I want to change to 
	 */
	public void setGameScreen(ScreenAdapter screen) {
		this.GameScreen=screen;
		this.setScreen(screen);
	}

}
