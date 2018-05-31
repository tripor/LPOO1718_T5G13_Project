package logic.entities;

import logic.Place;
/**
 * Class that handle the logic of the Factory
 *
 */
public class FactoryL extends Place {
	/**
	 * Price of the factory
	 */
	public static int price=200;
	/**
	 * Width of the factory
	 */
	public static int width=40;
	/**
	 * Height of the factory
	 */
	public static int height=40;
	/**
	 * Constructor of the class Factory Logic with width 40 and height 40
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public FactoryL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,FactoryL.width,FactoryL.height,doorAtBorder);
	}
	/**
	 * Empty constructor
	 */
	public FactoryL()
	{
		super();
	}

	@Override
	public float handler() {
		return 0;
	}
	@Override
	public int getPrice() {
		return FactoryL.price;
	}
	

}
