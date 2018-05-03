package graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import logic.storage.PersonList;

public class GroundUpGame extends Game {
	/**
	 * Manages the game assets
	 */
	private AssetManager assetManager;

	@Override
	public void create() {
		this.assetManager= new AssetManager();
		this.setScreen(new GroundUpScreen(this));
	}

	private static GroundUpGame instance = new GroundUpGame();
	
	private GroundUpGame() {
		// TODO Auto-generated constructor stub
	}
	
	public static GroundUpGame getInstance() {
		return instance;
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

}
