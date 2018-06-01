package logic.entities;

import logic.Map;
import logic.Place;
/**
 * Class that handle the logic of the Smelter
 *
 */
public class SmelterL extends Place {
	/**
	 * Price of the smelter
	 */
	public static int price=100;
	/**
	 * Width of the smelter
	 */
	public static int width=20;
	/**
	 * Height of the smelter
	 */
	public static int height=20;
	/**
	 * Constructor of the class smelter logic
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public SmelterL(int posX,int posY,int doorAtBorder) {
		super(posX,posY,SmelterL.width,SmelterL.height,doorAtBorder);
	}
	/**
	 * Empty constructor
	 */
	public SmelterL()
	{
		super();
	}

	@Override
	public float handler() {
		time++;
		if (time >= this.work_time) {
			time = 0;
			if (this.getInternalStorage().isEmpty())
				time = this.work_time;
			else {
				MaterialL trans = this.getInternalStorage().get(0);
				trans.smelt();
				this.moveToExternal(trans);
			}
		}
		return 0;
	}

	@Override
	public int getPrice() {
		return SmelterL.price;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		if (mat.getType().equals("iron_ore")) {
			this.addToStorage(mat);
			Map.singleton.removeMap(mat);
			return true;
		}
		if (mat.getType().equals("copper_ore")) {
			this.addToStorage(mat);
			Map.singleton.removeMap(mat);
			return true;
		}

		return false;
	}

}
