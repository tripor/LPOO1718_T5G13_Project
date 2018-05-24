package logic.enteties;

import java.util.ArrayList;

import logic.Entetie;
import logic.Map;
/**
 *	Class that handles the logic of the Material 
 */
public class MaterialL extends Entetie {
	/**
	 * The type of material
	 */
	private String type;
	/**
	 * ID
	 */
	public int id=0;
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
		ArrayList<Entetie> elements = map.getMapPercisionPixel(new_x+this.getWidth()/2, new_y+this.getHeight()/2);
		for (Entetie it : elements) {
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