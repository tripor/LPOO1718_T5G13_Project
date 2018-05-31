package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.ConveyorL;
/**
 * Class that handle the graphical part of the Conveyor
 *
 */
public class ConveyorG extends ActorExtension{
	/**
	 * Constructor for the graphical class Conveyor
	 * @param game The game it belowngs to
	 * @param posX The X position in pixels
	 * @param posY The y position in pixels
	 * @param direction The direction it's facing
	 */
	public ConveyorG(int posX,int posY,int direction)
	{
		this.instance=new ConveyorL((int)(posX*GameStage.singleton.reverseScale()),(int)(posY*GameStage.singleton.reverseScale()),direction);
		super.create();
	}
	/**
	 * Second constructor for the class
	 * @param game The game it belongs
	 * @param it The instance
	 */
	public ConveyorG(Entity it) {
		this.instance=it;
		super.create();
	}
	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[12];
		
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("conveyor1.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("conveyor2.png");
		frames[2] = GameStage.singleton.getGame().getAssetManager().get("conveyor3.png");
		frames[3] = GameStage.singleton.getGame().getAssetManager().get("conveyor4.png");
		frames[4] = GameStage.singleton.getGame().getAssetManager().get("conveyor5.png");
		frames[5] = GameStage.singleton.getGame().getAssetManager().get("conveyor6.png");
		frames[6] = GameStage.singleton.getGame().getAssetManager().get("conveyor7.png");
		frames[7] = GameStage.singleton.getGame().getAssetManager().get("conveyor8.png");
		frames[8] = GameStage.singleton.getGame().getAssetManager().get("conveyor9.png");
		frames[9] = GameStage.singleton.getGame().getAssetManager().get("conveyor10.png");
		frames[10] = GameStage.singleton.getGame().getAssetManager().get("conveyor11.png");
		frames[11] = GameStage.singleton.getGame().getAssetManager().get("conveyor12.png");
		
		return frames;
	}

}
