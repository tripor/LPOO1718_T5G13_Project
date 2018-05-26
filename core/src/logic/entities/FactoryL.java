package logic.entities;

import logic.Place;
/**
 * Class that handle the logic of the Factory
 *
 */
public class FactoryL extends Place {
	/**
	 * Constructor of the class Factory Logic with width 40 and height 40
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public FactoryL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,40,40,doorAtBorder);
	}

}
