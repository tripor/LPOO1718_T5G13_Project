package graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

public class GroundUpGame extends Game {
	/**
	 * Manages the game assets
	 */
	private AssetManager assetManager;

	@Override
	public void create() {
		this.assetManager = new AssetManager();
		this.setScreen(new GroundUpScreen(this));
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
