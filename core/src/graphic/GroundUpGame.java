package graphic;

import com.badlogic.gdx.Game;
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
	private GroundUpScreen screen;

	@Override
	public void create() {
		this.assetManager = new AssetManager();
		screen=new GroundUpScreen(this);
		this.setScreen(screen);
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
