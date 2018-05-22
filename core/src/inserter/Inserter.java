package inserter;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import graphic.ActorExtension;
import logic.map.Map;
import material.Material;
import place.Place;
/**
 * Class that handle the inserter logic
 *
 */
public class Inserter extends ActorExtension{
	/**
	 * Inserter width
	 */
	public static int width=4;
	/**
	 * Inserter height
	 */
	public static int height=4;
	/**
	 * Inserter hand width
	 */
	public static int width_hand=7;
	/**
	 * Inserter hand height
	 */
	public static int height_hand=4;
	/**
	 * Direction the inserter is facing
	 */
	private int direction;
	/**
	 * If the inserter is rotating
	 */
	private boolean isRotating = false;
	/**
	 * Clock or counterclock wise direction
	 */
	private boolean rotationDirection=true;
	/**
	 * The degree of how much it has rotated
	 */
	private int rotating_quantity=0;
	/**
	 * Rotating velocity
	 */
	private int rotating_velocity=7;
	/**
	 * Sprite for the hand
	 */
	private Sprite sprite2;
	/**
	 * Material the inserter had picked up
	 */
	private Material pickup;
	/**
	 * If the inserter has been blocked from placing the block
	 */
	private boolean blocked;
	
	private int middle_x;
	private int middle_y;
	
	/**
	 * Creates the sprite for the class
	 */
	private void createInserter() {
		
		
		Texture texture = this.game.getGame().getAssetManager().get("inserter_base.png");
		Texture texture2 = this.game.getGame().getAssetManager().get("inserter_hand.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		
		sprite2 =  new Sprite(texture2);
		sprite2.setSize(width_hand, height_hand);
		sprite2.setOrigin(width_hand, height_hand/2);
		
		if(this.direction!=4)
		{
			sprite.rotate(-90*direction);
			sprite2.rotate(-90*direction);
		}
	}
	/**
	 * Constructor for the Class inserter
	 * @param game The game it belongs to
	 * @param col The colum or y position in pixels
	 * @param row The row or x position in pixels
	 * @param width The width in pixels
	 * @param height The height in pixels
	 * @param direction The direction it is facing
	 */
	public Inserter(GameStage game,int row, int col, int width, int height, int direction) {
		this.game=game;
		this.setWidth(width);
		this.setHeight(height);
		this.direction=direction;
		this.createInserter();
		this.setPosition(row, col);
		this.sprite2.setPosition(row-(Inserter.width_hand-2), (float) (col));
		this.isRotating=false;
		this.middle_x=row;
		this.middle_y=col;
		
	}

	/**
	 * Rotates de Hand of the inserter 180 degree clockwise and returns
	 */
	private void rotateHand()
	{
		this.isRotating=true;
		this.rotating_quantity=0;
		if(this.direction!=4)
		{
			this.rotating_quantity=-90*direction;
		}
		this.rotationDirection=true;
	}
	/**
	 * See if there is something for the inserter to pick up
	 */
	public void tryPickUp()
	{
		ArrayList<Actor> elements = null;
		this.pickup=null;
		switch(this.direction)
		{
		case 1:
			elements = this.game.map().getMap((int)this.getX(), (int)this.getY()+Map.division);
			break;
		case 2:
			elements = this.game.map().getMap((int)this.getX()+Map.division, (int)this.getY());
			break;
		case 3:
			elements = this.game.map().getMap((int)this.getX(), (int)this.getY()-Map.division);
			break;
		case 4:
			elements = this.game.map().getMap((int)this.getX()-Map.division, (int)this.getY());
			break;
		}
		if(elements.isEmpty())
			return;
		else
		{
			for(Actor it:elements)
			{
				
				if(Place.class.isAssignableFrom(it.getClass()))
				{
					Material removed = ((Place)it).removeMaterial("any");
					if(removed==null)
						continue;
					else
					{
						this.pickup=removed;
						this.game.materials().addMaterial(this.pickup);
						this.pickup.setVisible(true);
						return;
					}
				}
				else if(it.getClass().equals(Material.class))
				{
					this.pickup=(Material) it;
					this.game.materials().removeMaterialFromMap(this.pickup);
					return;
				}
			}
		}
	}
	
	private void deliverMaterial()
	{
		ArrayList<Actor> element = this.game.map().getPixelMap((int)(this.pickup.getX()+this.pickup.getWidth()/2),(int)(this.pickup.getY()+this.pickup.getHeight()/2));
		if(element.isEmpty())
		{
			System.out.println("here");
			this.game.materials().addMaterialToMap(this.pickup);
			this.pickup = null;
			this.blocked = false;
		} else {
			for(Actor it:element)
			{
				if(Material.class.isAssignableFrom(it.getClass()))
				{ 
					this.blocked=true;
					return;
				}
				else if(Inserter.class.isAssignableFrom(it.getClass()))
				{
					this.blocked=true;
				}
			}
			for(Actor it:element)
			{
				if(Conveyor.class.isAssignableFrom(it.getClass()))
				{
					//this.pickup.setPosition(this.pickup.getX()-(this.pickup.getX()%10)+1, this.pickup.getY());
					this.game.materials().addMaterialToMap(this.pickup);
					this.pickup = null;
					this.blocked = false;
					return;
				}
				else if(Place.class.isAssignableFrom(it.getClass()))
				{
					((Place)it).addToStorage(this.pickup);
					this.pickup=null;
					this.blocked=false;
					return;
				}
			}
		}
	}

	@Override
	public void update(float delta) {
		if(this.blocked)
		{
			this.deliverMaterial();
		}
		else if(this.isRotating)
		{
			int error=0;
			if(this.direction!=4)
			{
				error=90*direction;
			}
			if(this.rotationDirection)
			{
				this.pickup.setPosition((float) (this.middle_x-(this.sprite2.getWidth() * Math.cos(rotating_quantity*Math.PI/180))),(float)(this.middle_y-(this.sprite2.getWidth() * Math.sin(rotating_quantity*Math.PI/180))));
				this.sprite2.rotate(-rotating_velocity);
				this.rotating_quantity-=this.rotating_velocity;
				if(this.rotating_quantity<=-(180+error))
				{	
					this.rotationDirection=false;
					this.sprite2.rotate(-(this.sprite2.getRotation()+180+error));
					this.rotating_quantity=-(180+error);
					this.pickup.setPosition((float) (this.middle_x-(this.sprite2.getWidth() * Math.cos(rotating_quantity*Math.PI/180))),(float)(this.middle_y-(this.sprite2.getWidth() * Math.sin(rotating_quantity*Math.PI/180))));
					this.deliverMaterial();
				}
			}
			else
			{
				this.sprite2.rotate(rotating_velocity);
				this.rotating_quantity+=this.rotating_velocity;
				if(this.rotating_quantity>=-error)
				{	
					this.isRotating=false;
					this.sprite2.rotate(-(this.sprite2.getRotation()+error));
					this.rotating_quantity=-error;
				}
			}
		}
		else
		{
			this.tryPickUp();
			if(this.pickup!=null)
			{
				this.rotateHand();
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
		sprite2.draw(batch);
		this.update(Gdx.graphics.getDeltaTime());
	}
}
