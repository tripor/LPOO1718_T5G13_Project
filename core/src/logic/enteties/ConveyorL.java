package logic.enteties;

import java.util.ArrayList;

import logic.Entetie;
import logic.Map;
/**
 * Class that handles the logic of the Conveyor
 *
 */
public class ConveyorL extends Entetie {
	/**
	 * The velocity of the Conveyor
	 */
	private int velocity=1;
	/**
	 * Movement in x position
	 */
	private int movement_x = 0;
	/**
	 * Movement in y position
	 */
	private int movement_y = 0;
	/**
	 * The direction of the conveyor
	 */
	private int direction;
	
	/**
	 * Constructor for the logic class Conveyor
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param direction The direction he is going to push the materials. 1-top,2-right,3-bottom,4-left
	 */
	public ConveyorL(int posX, int posY,int direction) {
		super(posX, posY, 10,10);
		this.direction=direction;
		if(direction == 1) {	// Upwards
			movement_y = +this.velocity;
		}
		else if(direction == 2) {	// go right
			movement_x = +this.velocity;
		}
		else if(direction == 3) {	// Downwards
			movement_y = -this.velocity;
		}
		else if(direction == 4) {	// go left
			movement_x = -this.velocity;
		}
	}
	/**
	 * Moves all the materials on top of the conveyor
	 * @param map The map this conveyor belongs
	 */
	public void moveMaterials(Map map)
	{
		ArrayList<Entetie> elements= map.getMapPixel(this.getPosX(),this.getPosY());
		ArrayList<MaterialL> to_move = new ArrayList<MaterialL>();
		for(Entetie it:elements)
		{
			if(MaterialL.class.isAssignableFrom(it.getClass()))
			{
				to_move.add((MaterialL) it);
			}
		}
		for (MaterialL it : to_move) {
			if (this.getPosX() <= it.getPosX() + it.getWidth() / 2
					&& it.getPosX() + it.getWidth() / 2 <= this.getPosX() + this.getWidth()
					&& this.getPosY() <= it.getPosY() + it.getHeight() / 2
					&& it.getPosY() + it.getHeight() / 2 <= this.getPosY() + this.getHeight())
			it.moveMaterial(this.movement_x,this.movement_y,map);
		}
	}
	/**
	 * 
	 * @return Returns the direction of the conveyor
	 */
	public int getDirection() {
		return direction;
	}
	
}