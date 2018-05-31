package logic.entities;

import com.badlogic.gdx.utils.Array;

import logic.Entity;
import logic.Map;
/**
 *	Class that handles the logic of the Material 
 */
public class MaterialL extends Entity {
	/**
	 * The type of material
	 */
	private String type;
	/**
	 * ID. 0=no actor,1-has actor,2-has actor but hidden
	 */
	private transient int id=1;
	/**
	 * Constructor for the logic class Material
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param type The type of material
	 */
	public MaterialL(int posX, int posY,String type) {
		super(posX, posY, 4, 4);
		this.type=type;
	}
	
	public MaterialL()
	{
		super();
		this.id=0;
	}
	/**
	 * 
	 * @return Return the type of material this is
	 */
	public String getType() {
		return type;
	}
	/**
	 * Sets this type of material
	 * @param type A String with the type I want to change to
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * Moves the Material in the map
	 * @param x_movement How much I want to move in pixels
	 * @param y_movement How much I want to move in pixels
	 * @param map The map I want the material to move
	 */
	public void moveMaterial(int x_movement, int y_movement) {
		int new_x = this.getPosX() + x_movement;
		int new_y = this.getPosY() + y_movement;
		Array<Entity> elements = Map.singleton.getMapPercisionPixel(new_x+this.getWidth()/2, new_y+this.getHeight()/2);
		for (Entity it : elements) {
			if (ConveyorL.class.isAssignableFrom(it.getClass()) || this==it) {
			} else {
				return;
			}
		}
		Map.singleton.removeMap(this);
		this.posX=new_x;
		this.posY=new_y;
		Map.singleton.addMap(this);
	}

	@Override
	public float handler() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		return false;
	}
	
	@Override
	public MaterialL pickUp(String type) {
		if(this.type.equals(type) || type.equals("any"))
		{
			Map.singleton.removeMap(this);
			return this;
		}
		else
			return null;
	}

	@Override
	public int getPrice() {
		return 0;
	}
	
	@Override
	public boolean addEntity() {
		int money=Map.singleton.getMoney(),money_wasted=Map.singleton.getMoney_wasted();
		if (money >= this.getPrice()) {
			Map.singleton.setMoney(money- this.getPrice()); 
			Map.singleton.setMoney_wasted(money_wasted+ this.getPrice());
			Map.singleton.getLista_material().add(this);
			return true;
		}
		return false;
	}

	@Override
	public void removeEntity() {
		Map.singleton.getLista_material().removeValue(this, true);
	}
	/**
	 * 
	 * @return 0 if visible or 1 if invisible
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets the id
	 * @param id 0 if visible of 1 if invisible
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
