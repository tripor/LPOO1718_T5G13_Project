package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.SmelterL;
/**
 * Class that handles the graphical part of the smelter
 *
 */
public class SmelterG extends ActorExtension{
	/**
	 * Constructor for the graphic smelter
	 * @param game The game it belowng to 
	 * @param posX The X position in pixels
	 * @param posY The y position in pixels
	 * @param doorAtBorder The position of the door. 1-top,2-right,3-bottom,4-left
	 */
	public SmelterG(int posX,int posY,int doorAtBorder)
	{
		this.instance =  new SmelterL((int)(posX*GameStage.singleton.reverseScale()),(int)(posY*GameStage.singleton.reverseScale()),doorAtBorder);
		super.create();
		
	}
	/**
	 * Second constructor
	 * @param it The instance
	 */
	public SmelterG(Entity it) {
		instance = it;
		super.create();
	}
	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("smelter.png");
		return frames;
	}
	

}
