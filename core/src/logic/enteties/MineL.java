package logic.enteties;

import logic.Place;
/**
 * Class That handles the logic of the Mines
 *
 */
public class MineL extends Place{
	/**
	 * Time it takes to make a new material
	 */
	private int time_make_material=100;
	/**
	 * Time
	 */
	private int time=0;
	private String type;
	/**
	 * Constructor for the logic class mine
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The position of the door
	 */
	public MineL(int posX, int posY, int doorAtBorder) {
		super(posX, posY, 20,20, doorAtBorder);
	}
	
	public MineL()
	{
		super();
	}
	/**
	 * Goes one point in time
	 */
	public void handler()
	{
		time++;
		if(time>=this.time_make_material)
		{
			time=0;
			this.addToStorage(new MaterialL(0,0,"iron_plate"));
		}
	}

}
