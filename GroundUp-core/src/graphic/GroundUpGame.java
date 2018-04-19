package graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundUpGame extends Game {
	/**
	 * Manages the game assets
	 */
	private AssetManager assetManager;
	/**
	 * The sprite batch used for drawing to the screen
	 */
	private SpriteBatch batch;
	

	@Override
	public void create() {
		this.assetManager= new AssetManager();
		
		this.batch= new SpriteBatch();
		
		
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

	/**
	 * Gets the Sprite Batch of the game
	 * @return returns the spritebatch
	 */
	public SpriteBatch getBatch() {
		return batch;
	}

	/**
	 * Sets the Sprite Batch of the game
	 * @param batch the spriteBatch I want to change to
	 */
	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

}
