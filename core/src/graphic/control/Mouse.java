package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import graphic.ActorExtension;
import graphic.GameStage;
import graphic.enteties.ConveyorG;
import graphic.enteties.FactoryG;
import graphic.enteties.InserterG;
import graphic.enteties.MineG;
import logic.Map;
/**
 * Class that handles related mouse evets
 *
 */
public class Mouse extends ActorExtension {
	/**
	 * If there is something selected to place
	 */
	public boolean isSelected=false;
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
	/**
	 * Add object to the map on the position of the mouse
	 */
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
			int x = (int) (mouse_pos.x/Map.division) * 10 + adicao_x;
			int y = (int) (mouse_pos.y/Map.division) * 10 + adicao_y;
			if(this.type.equals("factory.png"))
			{
				FactoryG fab= new FactoryG(this.game,x,y,this.doorPosition);
				if(this.game.places().addPlace(fab))
					this.isSelected=false;
			}
			if(this.type.equals("iron_mine.png"))
			{
				MineG im= new MineG(this.game,x,y,this.doorPosition);
				if(this.game.places().addPlace(im))
					this.isSelected=false;
			}
			if(this.type.equals("conveyor1.png"))
			{
				ConveyorG c= new ConveyorG(this.game,x,y,this.doorPosition);
				this.game.conveyors().addConveyor(c);
			}
			if(this.type.equals("inserter_direction.png"))
			{
				InserterG i= new InserterG(this.game,x,y,this.doorPosition);
				this.game.inserters().addInserter(i);
			}
			/*if(this.type.equals("nothing.png"))
			{
				House h= new House(this.game,x,y,this.width,this.height,this.doorPosition,10);
				if(this.game.places().addPlace(h))
					this.isSelected=false;
			}*/
			
			
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
			int x = (int) (mouse_pos.x/Map.division) * 10 + adicao_x;
			int y = (int) (mouse_pos.y/Map.division) * 10 + adicao_y;
			this.setPosition(x, y);
			sprite.draw(batch);
		}
	}
	/**
	 * Rotates the sprite selected on the mouse
	 */
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
	/**
	 * 
	 * @return Returns the type of object selected by mouse 
	 */
	public String getType() {
		return type;
	}
	
	

}
