package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
//import logic.Place;
import logic.entities.MineL;
/**
 * Class that handle the graphical part of the Mines
 *
 */
public class MineG extends ActorExtension{
	/**
	 * Constructor for the graphical Mine class
	 * @param game The game it belongs to 
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The door position
	 */
	public MineG(int posX,int posY,int doorAtBorder)
	{
		this.instance=new MineL((int)(posX*GameStage.singleton.reverseScale()),(int)(posY*GameStage.singleton.reverseScale()),doorAtBorder);
		super.create();
	}
	/**
	 * Second constructor for the class
	 * @param game The game it belongs to
	 * @param it The instance
	 */
	public MineG(Entity it) {
		this.instance=it;
		super.create();
	}
	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("iron_mine.png");
		return frames;
	}

}
