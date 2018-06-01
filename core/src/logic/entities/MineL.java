package logic.entities;

import logic.Map;
import logic.Place;
/**
 * Class That handles the logic of the Mines
 *
 */
public class MineL extends Place{
	/**
	 * Width of ht mine
	 */
	public static int width=10;
	/**
	 * Height of the mine
	 */
	public static int height=10;
	/**
	 * Price of the mines
	 */
	public static int price=150;
	/**
	 * Constructor for the logic class mine
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The position of the door
	 */
	public MineL(int posX, int posY, int doorAtBorder) {
		super(posX, posY, MineL.width,MineL.height, doorAtBorder);
	}
	
	public MineL()
	{
		super();
	}

	@Override
	public float handler() {
		time++;
		if (time >= this.work_time) {
			time = 0;
			BackgroundL to_retrive = Map.singleton.getBackgroundPoint(this.posX, this.posY);
			if (to_retrive.getType().equals("grass") || to_retrive.getType().equals("land")) {

			} else {
				if (to_retrive.getMaterial()) {
					this.addToExternalStorage(new MaterialL(0, 0, to_retrive.getType()));
					return 1;
				}
			}
		}
		return 0;
	}

	@Override
	public int getPrice() {
		return MineL.price;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		return false;
	}

}
