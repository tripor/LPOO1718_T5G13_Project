package logic.entities;

import com.badlogic.gdx.utils.Array;

import logic.Entity;
import logic.Map;
/**
 * Class that takes care of the logic of the Inserters
 *
 */
public class InserterL extends Entity {
	/**
	 * If the inserter is rotating
	 */
	private transient boolean isRotating = false;
	/**
	 * Clock or counterclock wise direction
	 */
	private transient boolean rotationDirection=true;
	/**
	 * The degree of how much it has rotated
	 */
	private transient int rotating_quantity=0;
	/**
	 * Rotating velocity
	 */
	private int rotating_velocity=7;
	/**
	 * Material the inserter had picked up
	 */
	private transient MaterialL pickup;
	/**
	 * If the inserter has been blocked from placing the block
	 */
	private transient boolean blocked;
	/**
	 * Inserter hand width
	 */
	public int width_hand=7;
	/**
	 * Inserter hand height
	 */
	public int height_hand=4;
	/**
	 * Inserter price
	 */
	public static int price=20;
	/**
	 * Width of the inserter
	 */
	public static int width=4;
	/**
	 * Height of the inserter
	 */
	public static int height=4;
	/**
	 * Constructor for the logic class Inserter
	 * @param posX
	 * @param posY
	 * @param direction
	 */
	public InserterL(int posX, int posY,int direction) {
		super(posX, posY, InserterL.width,InserterL.height);
		this.direction=direction;
		this.blocked=false;
	}
	
	public InserterL()
	{
		super();
		this.isRotating=false;
		this.rotationDirection=true;
		this.rotating_quantity=0;
		this.pickup=null;
		this.blocked=false;
	}
	
	/**
	 * Delivers the material to the map
	 * @param map The Map I want to deliver the material
	 */
	private void deliverMaterial()
	{
		Array<Entity> element = Map.singleton.getMapPercisionPixel(this.pickup.getPosX()+this.pickup.getWidth()/2,this.pickup.getPosY()+this.pickup.getHeight()/2);
		this.blocked=false;
		if(element.size==0)
		{ 
			Map.singleton.addMap(pickup);
			this.pickup = null;
			this.blocked = false;
		} else {
			for(Entity it:element)
			{
				if(MaterialL.class.isAssignableFrom(it.getClass()))
				{
					this.blocked=true;
				}
			}
			if(!this.blocked)
			{
				for(Entity it:element)
				{
					if(it.receiveMaterial(this.pickup))
					{
						this.pickup = null;
						this.blocked = false;
						return;
					}
					else
					{
						this.blocked=true;
					}
				}
			}
		 }
	}
	/**
	 * See if there is something for the inserter to pick up
	 * @param map The Map I want to map the modificiation
	 */
	public void tryPickUp() {
		Array<Entity> elements = null;
		this.pickup = null;
		switch (this.direction) {
		case 1:
			elements = Map.singleton.getMapPixel(this.posX, this.posY + Map.division);
			break;
		case 2:
			elements = Map.singleton.getMapPixel(this.posX + Map.division, this.posY);
			break;
		case 3:
			elements = Map.singleton.getMapPixel(this.posX, this.posY - Map.division);
			break;
		case 4:
			elements = Map.singleton.getMapPixel(this.posX - Map.division, this.posY);
			break;
		}
		if (elements.size == 0) {
			return;
		} else {
			for (Entity it : elements) {
				if ((this.pickup = it.pickUp("any")) != null) {
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
	 * 
	 * @return Returns the direction of the inserter
	 */
	public int getDirection() {
		return direction;
	}
	/**
	 * Rotates the hand and tries to deliver the pickup
	 * @param retornar Current rotation
	 * @return The new rotation
	 */
	private float algoritm(float retornar)
	{
		int error = 0;
		if (this.direction != 4) {
			error = 90 * direction;
		}
		if (this.rotationDirection) {
			this.pickup.setPosition(
					(int) (this.posX - (this.width_hand * Math.cos(rotating_quantity * Math.PI / 180))),
					(int) (this.posY - (this.width_hand * Math.sin(rotating_quantity * Math.PI / 180))));
			retornar -= this.rotating_velocity;
			this.rotating_quantity -= this.rotating_velocity;
			if (this.rotating_quantity <= -(180 + error)) {
				this.rotationDirection = false;
				retornar -= this.rotating_quantity + 180 + error;
				this.rotating_quantity = -(180 + error);
				this.pickup.setPosition(
						(int) (this.posX - (this.width_hand * Math.cos(rotating_quantity * Math.PI / 180))),
						(int) (this.posY - (this.width_hand * Math.sin(rotating_quantity * Math.PI / 180))));
				this.deliverMaterial();
			}
		} else {
			retornar += rotating_velocity;
			this.rotating_quantity += this.rotating_velocity;
			if (this.rotating_quantity >= -error) {
				this.isRotating = false;
				retornar -= rotating_quantity + error;
				this.rotating_quantity = -error;
			}
		}
		return retornar;
	}
	

	@Override
	public float handler() {
		float retornar = 0;
		if (this.blocked) {
			this.deliverMaterial();
		} else if (this.isRotating) {
			retornar = this.algoritm(retornar);
		} else {
			this.tryPickUp();
			if (this.pickup != null) {
				this.rotateHand();
			}
		}
		return retornar;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		return false;
	}


	@Override
	public int getPrice() {
		return InserterL.price;
	}
	
	

}
