package logic.entities;

import logic.Entity;

/**
 * Class that handles the logic of the background
 *
 */
public class BackGroundL extends Entity {
	/**
	 * Type of material in the land
	 */
	private String type;
	/**
	 * Amount of materials in the land
	 */
	private int quantity;
	/**
	 * Constructor for the logic part of the background
	 * @param type The type of material of the land
	 * @param quantity The quantity of the material in the land
	 */
	public BackGroundL(String type,int quantity)
	{
		this.type=type;
		this.quantity=quantity;
	}
	/**
	 * 
	 * @return Returns the type of material there is in the land
	 */
	public String getType() {
		return type;
	}
	/**
	 * Removes one material form the land
	 * @return True if there is a material or false otherwise
	 */
	public boolean getMaterial() {
		if (this.quantity > 0) {
			this.quantity--;
			return true;
		}
		else
			return false;
	}
	
	

}
