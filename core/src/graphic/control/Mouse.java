package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import graphic.ActorExtension;
import graphic.Console;
import graphic.GameStage;
import graphic.entities.ConveyorG;
import graphic.entities.FactoryG;
import graphic.entities.HouseG;
import graphic.entities.InserterG;
import graphic.entities.MineG;
import graphic.entities.SmelterG;
import logic.Map;
import logic.Place;
import logic.entities.FactoryL;
/**
 * Class that handles related mouse events
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
	public FactoryL selected;
	/**
	 * If i want to remove things from map
	 */
	public boolean remove=false;
	/**
	 * Cosntructor for the class mouse
	 */
	public Mouse()
	{
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
		Texture texture = GameStage.singleton.getGame().getAssetManager().get(type);
		
		sprite = new Sprite(texture);
		sprite.setSize(width*GameStage.singleton.scale(), height*GameStage.singleton.scale());
		sprite.setOrigin(width*GameStage.singleton.scale()/2, height*GameStage.singleton.scale()/2);

		if(this.doorPosition!=4)
			sprite.rotate(-90*doorPosition);
		
		this.isSelected=true;
	}
	/**
	 * Add object to the map on the position of the mouse
	 */
	public void addObject()
	{
		if(isSelected)
		{
			int[] posXY = getMouseP();
			int x = posXY[0], y = posXY[1];
			
			if(y < 180) {
				return;
			}

			if(this.type.equals("factory.png"))
			{
				FactoryG fab= new FactoryG(x,y,this.doorPosition);
				if(GameStage.singleton.places().addPlace(fab))
				{
					this.isSelected=false;
					((Place)fab.getInstance()).lookForWorker();
				}
			}
			if(this.type.equals("iron_mine.png"))
			{
				MineG im= new MineG(x,y,this.doorPosition);
				if(GameStage.singleton.places().addPlace(im))
				{
					this.isSelected=false;
					((Place)im.getInstance()).lookForWorker();
				}
			}
			if(this.type.equals("conveyor1.png"))
			{
				ConveyorG c= new ConveyorG(x,y,this.doorPosition);
				GameStage.singleton.conveyors().addConveyor(c);
			}
			if(this.type.equals("inserter_direction.png"))
			{
				InserterG i= new InserterG(x,y,this.doorPosition);
				GameStage.singleton.inserters().addInserter(i);
			}
			if(this.type.equals("house.png"))
			{
				HouseG h= new HouseG(x,y,this.doorPosition);
				if(GameStage.singleton.places().addPlace(h))
					this.isSelected=false;
			}
			if(this.type.equals("smelter.png"))
			{
				SmelterG h= new SmelterG(x,y,this.doorPosition);
				if(GameStage.singleton.places().addPlace(h))
				{
					this.isSelected=false;
					((Place)h.getInstance()).lookForWorker();
				}
			}
			
			
		}
	}
	
	private int[] getMouseP() {
		Vector3 mouse_pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		GameStage.singleton.getViewport().unproject(mouse_pos);
		int numero=Map.division;
		int adicao_x=0;
		int adicao_y=0;
		if(this.width % numero!=0)
			adicao_x = (numero - (this.width % numero)) / 2;
		if(this.height % numero!=0)
			adicao_y = (numero - (this.height % numero)) / 2;
		int x = (int) (mouse_pos.x*GameStage.singleton.reverseScale()/numero) * numero + adicao_x;
		int y = (int) (mouse_pos.y*GameStage.singleton.reverseScale()/numero) * numero + adicao_y;
		
		x = (int) (x * GameStage.singleton.scale());
		y = (int) (y * GameStage.singleton.scale());
		
		int[] pos = {x,y};
		return pos;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (this.isSelected) {
			int[] posXY = getMouseP();
			int x = posXY[0], y = posXY[1];
			
			if(y < 180) {
				return;
			}
			
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
	@Override
	protected Texture[] createTexture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
