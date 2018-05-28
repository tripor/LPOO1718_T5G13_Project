package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import graphic.PlaceGraphical;
import logic.Place;
import logic.entities.FactoryL;
import logic.entities.HouseL;
/**
 * Class that handles the graphical part of the House
 */
public class HouseG extends PlaceGraphical{
	/**
	 * Creates the Sprite for the Factory
	 */
	private void createHouse() {
		
		Texture texture = this.game.getGame().getAssetManager().get("house.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		int door_atBorder=((HouseL)this.instance).getDoorAtBorder();
		if(door_atBorder!=4)
			sprite.rotate(-90*door_atBorder);
	}
	/**
	 * Constructor for the graphic factory
	 * @param game The game it belowng to 
	 * @param posX The X position in pixels
	 * @param posY The y position in pixels
	 * @param doorAtBorder The position of the door. 1-top,2-right,3-bottom,4-left
	 */
	public HouseG(GameStage game,int posX,int posY,int doorAtBorder)
	{
		instance =  new HouseL(posX*256/game.VIEWPORT_WIDTH,posY*256/game.VIEWPORT_WIDTH,doorAtBorder);
		this.game=game;
		this.setWidth(instance.getWidth()*game.VIEWPORT_WIDTH/256);
		this.setHeight(instance.getHeight()*game.VIEWPORT_WIDTH/256);
		this.createHouse();
		this.setPosition(this.instance.getPosX()*game.VIEWPORT_WIDTH/256, this.instance.getPosY()*game.VIEWPORT_WIDTH/256);
		
	}
	/**
	 * Second constructor
	 * @param game The game it belongs to
	 * @param it The instance
	 */
	public HouseG(GameStage game, Place it) {
		instance =  it;
		this.game=game;
		this.setWidth(instance.getWidth()*game.VIEWPORT_WIDTH/256);
		this.setHeight(instance.getHeight()*game.VIEWPORT_WIDTH/256);
		this.createHouse();
		this.setPosition(this.instance.getPosX()*game.VIEWPORT_WIDTH/256, this.instance.getPosY()*game.VIEWPORT_WIDTH/256);
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
