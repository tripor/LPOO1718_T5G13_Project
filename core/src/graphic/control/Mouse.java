package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import graphic.ActorExtension;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import logic.map.Map;
import place.type.Factory;
import place.type.IronMine;
/**
 * Class that handles related mouse evets
 *
 */
public class Mouse extends ActorExtension {
	
	private boolean isSelected=false;
	private String type;
	private int width;
	private int height;
	private int doorPosition=4;
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
		sprite.setOrigin(width/2, height/2);

		if(this.doorPosition!=4)
			sprite.rotate(-90*doorPosition);
		
		this.setDebug(true);
		
		this.isSelected=true;
	}
	
	public void addObject()
	{
		if(isSelected)
		{
			Vector3 mouse_pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.game.getViewport().unproject(mouse_pos);
			int adicao_x=0;
			int adicao_y=0;
			if(this.width % 10!=0)
				adicao_x = (10 - (this.width % 10)) / 2;
			if(this.height % 10!=0)
				adicao_y = (10 - (this.height % 10)) / 2;
			int x = ((int) (mouse_pos.x / Map.division)) * 10 + adicao_x;
			int y = ((int) (mouse_pos.y / Map.division)) * 10 + adicao_y;
			if(this.type.equals("factory.png"))
			{
				Factory fab= new Factory(this.game,y,x,this.width,this.height,this.doorPosition,10);
				this.game.places().addPlace(fab);
			}
			if(this.type.equals("nothing.png"))
			{
				IronMine im= new IronMine(this.game,y,x,this.width,this.height,this.doorPosition,5);
				this.game.places().addPlace(im);
			}
			if(this.type.equals("conveyor1.png"))
			{
				Conveyor c= new Conveyor(this.game,y,x,this.width,this.height,this.doorPosition);
				this.game.conveyors().addConveyor(c);
			}
			
			
			this.isSelected=false;
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (this.isSelected) {
			Vector3 mouse_pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			this.game.getViewport().unproject(mouse_pos);
			int adicao_x=0;
			int adicao_y=0;
			if(this.width % 10!=0)
				adicao_x = (10 - (this.width % 10)) / 2;
			if(this.height % 10!=0)
				adicao_y = (10 - (this.height % 10)) / 2;
			int x = ((int) (mouse_pos.x / Map.division)) * 10 + adicao_x;
			int y = ((int) (mouse_pos.y / Map.division)) * 10 + adicao_y;
			this.setPosition(x, y);
			sprite.draw(batch);
		}
	}

	public void rotateMouse()
	{
		if(this.isSelected)
		{
			this.sprite.rotate(-90);
			this.doorPosition++;
			if(this.doorPosition>=5)
				this.doorPosition=1;
		}
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
