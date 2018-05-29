package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import graphic.PlaceGraphical;
import logic.Place;
import logic.entities.MineL;
/**
 * Class that handle the graphical part of the Mines
 *
 */
public class MineG extends PlaceGraphical{
	/**
	 * Creates the sprite for the Mine
	 */
	private void createMine() {
		
		Texture texture = this.game.getGame().getAssetManager().get("iron_mine.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		int door_atBorder=((MineL)this.instance).getDoorAtBorder();
		if(door_atBorder!=4)
			sprite.rotate(-90*door_atBorder);
	}
	/**
	 * Constructor for the graphical Mine class
	 * @param game The game it belongs to 
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The door position
	 */
	public MineG(GameStage game,int posX,int posY,int doorAtBorder)
	{
		this.instance=new MineL(game.map(),posX*256/game.VIEWPORT_WIDTH,posY*256/game.VIEWPORT_WIDTH,doorAtBorder);
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.VIEWPORT_WIDTH/256);
		this.setHeight(this.instance.getHeight()*game.VIEWPORT_WIDTH/256);
		this.createMine();
		this.setPosition(this.instance.getPosX()*game.VIEWPORT_WIDTH/256, this.instance.getPosY()*game.VIEWPORT_WIDTH/256);
	}
	/**
	 * Second constructor for the class
	 * @param game The game it belongs to
	 * @param it The instance
	 */
	public MineG(GameStage game, Place it) {
		this.instance=it;
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.VIEWPORT_WIDTH/256);
		this.setHeight(this.instance.getHeight()*game.VIEWPORT_WIDTH/256);
		this.createMine();
		this.setPosition(this.instance.getPosX()*game.VIEWPORT_WIDTH/256, this.instance.getPosY()*game.VIEWPORT_WIDTH/256);
	}
	@Override
	public void update(float delta) {
		((MineL)this.instance).handler();
		
	}

}
