package logic.entities;

import logic.Place;

public class HouseL extends Place {
	/**
	 * Constructor of the class House Logic with width 30 and height 30
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public HouseL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,30,30,doorAtBorder);
	}

}
