package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.map.Map;
import place.type.Factory;
/**
 * Class that handles related mouse evets
 *
 */
public class Mouse extends ActorExtension {
	
	private boolean isSelected=false;
	private String type;
	private int width;
	private int height;
	/**
	 * Cosntructor for the class mouse
	 * @param game the game the mouse belongs to 
	 */
	public Mouse(GameStage game)
	{
		this.game=game;
	}
	/**
	 * Creates a image in the mouse
	 * @param type String with the png
	 * @param width width of the image
	 * @param height height of the image
	 */
	public void createMouse(String type,int width, int height) {
		this.type=type;
		this.width=width;
		this.height=height;
		Texture texture = this.game.getGame().getAssetManager().get(type);
		
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		
		this.setDebug(true);
		
		this.isSelected=true;
	}
	
	public void addObject()
	{
		if(isSelected)
		{
			if(this.type.equals("factory.png"))
			{
				Vector3 mouse_pos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
				this.game.getViewport().unproject(mouse_pos);
				Factory fab= new Factory(this.game,((int)(mouse_pos.y/Map.division))*10,((int)(mouse_pos.x/Map.division))*10,this.width,this.height,10,10);
				this.game.getPlace_list().addPlace(fab);
			}
			
			
			
			this.isSelected=false;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(this.isSelected)
		{
			Vector3 mouse_pos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			this.game.getViewport().unproject(mouse_pos);
			this.setPosition(((int)(mouse_pos.x/Map.division))*10,((int)(mouse_pos.y/Map.division))*10);
			sprite.draw(batch);
		}
	}

}
