package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Map;
import logic.entities.BackGroundL;

/**
 * Class that handles the creation of the BackGround
 *
 */
public class BackgroundG extends ActorExtension {
	
	/**
	 * Create a Graphic design of this Actor
	 */
	private void createBackground() {
		Texture texture=null;
		if(((BackGroundL)this.instance).getType().equals("iron_ore"))
		{
			texture = this.game.getGame().getAssetManager().get("land_iron.png");
		}
		else
		{
			texture = this.game.getGame().getAssetManager().get("grass.png");
		}
		

		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		this.setDebug(true);
	}
	/**
	 * Constructor for the graphical part of Background
	 * @param top The X position
	 * @param left The Y position
	 * @param width The width
	 * @param height The height
	 */
	public BackgroundG(GameStage game,int x, int y,BackGroundL inst) {
		this.instance=inst;
		this.game=game;
		this.setWidth(Map.division*this.game.VIEWPORT_WIDTH/256);
		this.setHeight(Map.division*this.game.VIEWPORT_WIDTH/256);
		this.createBackground();
		this.setPosition(x, y);
	}
	
	@Override
	public String toString()
	{
		return "BackGround";
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
