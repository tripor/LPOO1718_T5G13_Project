package logic.entities;

import java.util.ArrayList;
import java.util.UUID;

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
	public transient int id=0;
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
	public void moveMaterial(int x_movement, int y_movement,Map map) {
		int new_x = this.getPosX() + x_movement;
		int new_y = this.getPosY() + y_movement;
		Array<Entity> elements = map.getMapPercisionPixel(new_x+this.getWidth()/2, new_y+this.getHeight()/2);
		for (Entity it : elements) {
			if (ConveyorL.class.isAssignableFrom(it.getClass()) || this==it) {
			} else {
				return;
			}
		}
		map.removeMap(this);
		this.posX=new_x;
		this.posY=new_y;
		map.addMap(this);
	}
	
	

}
