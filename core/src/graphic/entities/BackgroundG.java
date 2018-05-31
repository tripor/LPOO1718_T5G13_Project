package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.BackGroundL;

/**
 * Class that handles the creation of the BackGround
 *
 */
public class BackgroundG extends ActorExtension {
	/**
	 * Constructor for the graphical part of Background
	 * @param top The X position
	 * @param left The Y position
	 * @param width The width
	 * @param height The height
	 */
	public BackgroundG(Entity inst) {
		this.instance=inst;
		super.create();
	}
	
	@Override
	public String toString()
	{
		return "BackGround";
	}
	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get(((BackGroundL)this.instance).getTypeLand());
		return frames;
	}

}
