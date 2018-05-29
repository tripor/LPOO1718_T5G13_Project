package logic.entities;

import logic.Map;
import logic.Place;
/**
 * Class That handles the logic of the Mines
 *
 */
public class MineL extends Place{
	/**
	 * Price of the mines
	 */
	public static int price=150;
	/**
	 * Time it takes to make a new material
	 */
	private int time_make_material=100;
	/**
	 * Time
	 */
	private int time=0;
	/**
	 * The map this mine belong to mine the map materials
	 */
	private Map map;
	/**
	 * Constructor for the logic class mine
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The position of the door
	 */
	public MineL(Map map,int posX, int posY, int doorAtBorder) {
		super(posX, posY, 10,10, doorAtBorder);
		this.map=map;
	}
	
	public MineL()
	{
		super();
	}
	/**
	 * Goes one point in time
	 */
	public boolean handler()
	{
		time++;
		if(time>=this.time_make_material)
		{
			time=0;
			BackGroundL to_retrive=this.map.getBackGroundPoint(this.posX, this.posY);
			if(to_retrive.getType().equals("grass") || to_retrive.getType().equals("land"))
			{
				
			}
			else
			{
				if(to_retrive.getMaterial())
				{
					this.addToStorage(new MaterialL(0,0,to_retrive.getType()));
					return true;
				}
			}
		}
		return false;
	}

}
