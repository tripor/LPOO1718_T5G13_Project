package logic.entities;

import logic.Entity;
import logic.Map;

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
	public BackGroundL(int posX,int posY,String type,int quantity)
	{
		super(posX,posY,Map.division,Map.division);
		this.type=type;
		this.quantity=quantity;
	}
	/**
	 * Empty constructor for Json
	 */
	public BackGroundL() {}
	/**
	 * 
	 * @return Returns the type of material there is in the land
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @return Returns the name of the land.png
	 */
	public String getTypeLand() {
		if(this.type.equals("iron_ore"))
		{
			return "land_iron.png";
		}
		else if(this.type.equals("grass"))
		{
			return "grass.png";
		}
		else if(this.type.equals("land"))
		{
			return "land.png";
		}
		else if(this.type.equals("copper_ore"))
		{
			return "land_copper.png";
		}
		return "";
	}
	/**
	 * Removes one material form the land if there is one available
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
	@Override
	public float handler() {
		return 0;
		
	}
	@Override
	public boolean addEntity() {
		return true;
	}
	@Override
	public int getPrice() {
		return 0;
	}
	@Override
	public void removeEntity() {
	}
	/**
	 * 
	 * @return The quantity of materials in the land
	 */
	public int getQuantity() {
		return quantity;
	}
	
	
	
	

}
