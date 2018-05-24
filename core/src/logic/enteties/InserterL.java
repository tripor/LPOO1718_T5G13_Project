package logic.enteties;

import java.util.ArrayList;

import logic.Entetie;
import logic.Map;
import logic.Place;
/**
 * Class that takes care of the logic of the Inserters
 *
 */
public class InserterL extends Entetie{
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
	 * Material the inserter had picked up
	 */
	private MaterialL pickup;
	/**
	 * If the inserter has been blocked from placing the block
	 */
	private boolean blocked;
	/**
	 * Inserter hand width
	 */
	public int width_hand=7;
	/**
	 * Inserter hand height
	 */
	public int height_hand=4;
	/**
	 * Constructor for the logic class Inserter
	 * @param posX
	 * @param posY
	 * @param direction
	 */
	public InserterL(int posX, int posY,int direction) {
		super(posX, posY, 4,4);
		this.direction=direction;
	}
	
	
	/**
	 * Delivers the material to the map
	 * @param map The Map I want to deliver the material
	 */
	private void deliverMaterial(Map map)
	{
		ArrayList<Entetie> element = map.getMapPercisionPixel(this.pickup.getPosX()+this.pickup.getWidth()/2,this.pickup.getPosY()+this.pickup.getHeight()/2);
		if(element.isEmpty())
		{
			map.addMap(pickup);
			this.pickup = null;
			this.blocked = false;
		} else {
			for(Entetie it:element)
			{
				if(MaterialL.class.isAssignableFrom(it.getClass()))
				{ 
					this.blocked=true;
					return;
				}
				else if(InserterL.class.isAssignableFrom(it.getClass()))
				{
					this.blocked=true;
				}
			}
			for(Entetie it:element)
			{
				if(ConveyorL.class.isAssignableFrom(it.getClass()))
				{
					//this.pickup.setPosition(this.pickup.getX()-(this.pickup.getX()%10)+1, this.pickup.getY());
					map.addMap(pickup);
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
	/**
	 * See if there is something for the inserter to pick up
	 * @param map The Map I want to map the modificiation
	 */
	public void tryPickUp(Map map)
	{
		ArrayList<Entetie> elements = null;
		this.pickup=null;
		switch(this.direction)
		{
		case 1:
			elements = map.getMapPixel(this.posX,this.posY+Map.division);
			break;
		case 2:
			elements = map.getMapPixel(this.posX+Map.division, this.posY);
			break;
		case 3:
			elements = map.getMapPixel(this.posX,this.posY-Map.division);
			break;
		case 4:
			elements = map.getMapPixel(this.posX-Map.division,this.posY);
			break;
		}
		if(elements.isEmpty())
			return;
		else
		{
			for(Entetie it:elements)
			{
				
				if(Place.class.isAssignableFrom(it.getClass()))
				{
					MaterialL removed = ((Place)it).removeMaterial("any");
					if(removed==null)
						continue;
					else
					{
						this.pickup=removed;
						map.lista_material.add(removed);
						return;
					}
				}
				else if(MaterialL.class.isAssignableFrom(it.getClass()))
				{
					this.pickup=(MaterialL) it;
					map.removeMap(it);
					return;
				}
			}
		}
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
	 * Make one movement of the inserter
	 * @param map The map I want to make the modification
	 * @return What to add to the angel
	 */
	public float handler(Map map)
	{
		float retornar=0;
		if(this.blocked)
		{
			this.deliverMaterial(map);
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
				this.pickup.setPosition((int) ((this.posX+this.width/2)-(this.width_hand * Math.cos(rotating_quantity*Math.PI/180))),(int) ((this.posY+this.height/2)-(this.width_hand * Math.sin(rotating_quantity*Math.PI/180))));
				retornar-=this.rotating_velocity;
				this.rotating_quantity-=this.rotating_velocity;
				if(this.rotating_quantity<=-(180+error))
				{	
					this.rotationDirection=false;
					retornar-=this.rotating_quantity+180+error;
					this.rotating_quantity=-(180+error);
					this.pickup.setPosition((int) ((this.posX+this.width/2)-(this.width_hand * Math.cos(rotating_quantity*Math.PI/180))),(int) ((this.posY+this.height/2)-(this.width_hand * Math.sin(rotating_quantity*Math.PI/180))));
					this.deliverMaterial(map);
				}
			}
			else
			{
				retornar+=rotating_velocity;
				this.rotating_quantity+=this.rotating_velocity;
				if(this.rotating_quantity>=-error)
				{	
					this.isRotating=false;
					retornar-=rotating_quantity+error;
					this.rotating_quantity=-error;
				}
			}
		}
		else
		{
			this.tryPickUp(map);
			if(this.pickup!=null)
			{
				this.rotateHand();
			}
		}
		return retornar;
	}
	/**
	 * 
	 * @return Returns the direction of the inserter
	 */
	public int getDirection() {
		return direction;
	}
	
	

}
