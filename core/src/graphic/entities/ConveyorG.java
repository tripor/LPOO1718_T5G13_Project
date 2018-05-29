package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GroundUpScreen;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.entities.ConveyorL;
/**
 * Class that handle the graphical part of the Conveyor
 *
 */
public class ConveyorG extends ActorExtension{
	
	/**
	 * Creates the animation of the conveyor
	 */
	private void createConveyor() {
		
		Texture[] frames=new Texture[12];
		
		frames[0] = this.game.getGame().getAssetManager().get("conveyor1.png");
		frames[1] = this.game.getGame().getAssetManager().get("conveyor2.png");
		frames[2] = this.game.getGame().getAssetManager().get("conveyor3.png");
		frames[3] = this.game.getGame().getAssetManager().get("conveyor4.png");
		frames[4] = this.game.getGame().getAssetManager().get("conveyor5.png");
		frames[5] = this.game.getGame().getAssetManager().get("conveyor6.png");
		frames[6] = this.game.getGame().getAssetManager().get("conveyor7.png");
		frames[7] = this.game.getGame().getAssetManager().get("conveyor8.png");
		frames[8] = this.game.getGame().getAssetManager().get("conveyor9.png");
		frames[9] = this.game.getGame().getAssetManager().get("conveyor10.png");
		frames[10] = this.game.getGame().getAssetManager().get("conveyor11.png");
		frames[11] = this.game.getGame().getAssetManager().get("conveyor12.png");
		
		this.animation= new Animation<Texture>(.05f,frames);
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		int direction=((ConveyorL)this.instance).getDirection();
		if(direction!=4)
			sprite.rotate(-90*direction);
	}
	/**
	 * Constructor for the graphical class Conveyor
	 * @param game The game it belowngs to
	 * @param posX The X position in pixels
	 * @param posY The y position in pixels
	 * @param direction The direction it's facing
	 */
	public ConveyorG(GameStage game,int posX,int posY,int direction)
	{
		this.instance=new ConveyorL((int)(posX*game.reverseScale()),(int)(posY*game.reverseScale()),direction);
		this.game=game;
		this.setWidth(this.instance.getWidth()*this.game.scale());
		this.setHeight(this.instance.getHeight()*this.game.scale());
		this.createConveyor();
		this.setPosition(this.instance.getPosX()*this.game.scale(), this.instance.getPosY()*this.game.scale());
	}
	/**
	 * Second constructor for the class
	 * @param game The game it belongs
	 * @param it The instance
	 */
	public ConveyorG(GameStage game, ConveyorL it) {
		this.instance=it;
		this.game=game;
		this.setWidth(this.instance.getWidth()*this.game.scale());
		this.setHeight(this.instance.getHeight()*this.game.scale());
		this.createConveyor();
		this.setPosition(this.instance.getPosX()*this.game.scale(), this.instance.getPosY()*this.game.scale());
	}
	@Override
	public void update(float delta) {
		((ConveyorL)this.instance).moveMaterials(this.game.map());
        sprite.setRegion(animation.getKeyFrame(((GroundUpScreen)this.game.game.getGameScreen()).conveyorStateTime, true));
		
	}

}
