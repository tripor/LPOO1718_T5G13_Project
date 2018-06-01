package logic.entities;

import logic.Entity;
import logic.Map;
import logic.Place;
/**
 * Handles the person logic
 *
 */
public class PersonL extends Entity{
	/**
	 * Width of the person
	 */
	public static int width=5;
	/**
	 * Height of the person
	 */
	public static int height=5;
	/**
	 * Target position
	 */
	private int target_x, target_y;
	/**
	 * Target of this person
	 */
	private Place target;
	/**
	 * ID. 0=visivel 1-invisivel
	 */
	private transient int id=1;
	/**
	 * Constructor for Person
	 * @param posX The X position
	 * @param posY The Y position
	 */
	public PersonL(int posX, int posY) {
		super(posX,posY,PersonL.width,PersonL.height);
		this.target_x = posX;
		this.target_y = posY;
		this.id=1;
	}
	/**
	 * Empty constructor
	 */
	public PersonL() {
		super();
		this.id=0;
	}
	/**
	 * Sets the person target
	 * @param p The place target
	 */
	public void setTarget(Place p) {
		this.target_x = p.doorXposition();
		this.target_y = p.doorYposition();
		this.target=p;
	}
	
	public void getPath() {
		
		if(target_x == this.posX && target_y == this.posY) {
			return;
		}
		/**
		 * Booleans for programming style.
		 */
		boolean at_left    = target_x < this.posX,
				at_right   = target_x > this.posX,
				at_top     = target_y > this.posY,
				at_bottom  = target_y < this.posY;
		
		int tmpX=posX, tmpY=posY;
		
		if      (at_left)    tmpX--;
		else if (at_right)   tmpX++;

		if      (at_top)     tmpY++;
		else if (at_bottom)  tmpY--;
		/**
		 *  MINUS   REMAIN   ADD
		 *    4   |   5   |   6
		 *   
		 * [tIndex=0] `6|5` = (X+1,    ) => GO RIGHT (road-block on bottom)
		 * [      =1] `5|6` = (   , Y+1) => GO UP    (road-block on right)
		 * [      =2] `4|5` = (X-1,    ) => GO LEFT  (road-block on top)
		 * [      =3] `5|4` = (   , Y-1) => GO DOWN  (road-block on left)
		 */
		int[] try_order = {65, 56, 45, 54};
		int tIndex = (
				at_left ? 3 
					:(at_right ? 1
						:(at_top ? 2
							: 0	//=else
			)));
		
		if(Map.singleton.pointIsOccupied(tmpX, tmpY, this)) {
			int trials=0;
			while(++trials<=4) {
				tmpX = posX + (try_order[tIndex] / 10) - 5;
				tmpY = posY + (try_order[tIndex] % 10) - 5;
				
				if(Map.singleton.pointIsOccupied(tmpX, tmpY, this)) {
					if(++tIndex >= try_order.length) {
						tIndex = 0;
					}
				}
				else {
					break;
				}
			}
		}
		Map.singleton.removeMap(this);
		this.posX = tmpX;
		this.posY = tmpY;
		Map.singleton.addMap(this);
	}
	/**
	 * Setp
	 */
	int step = 0;

	@Override
	public float handler() {

		if(step < 4) {
			step++;
		}
		else {
			step = 0;
			this.getPath();
		}
		if (this.getPosX() >= this.target.doorXposition() - 1 && this.getPosX() <= this.target.doorXposition() + 1
				&& this.getPosY() >= this.target.doorYposition() - 1
				&& this.getPosY() <= this.target.doorYposition() + 1) {
			target.acceptWorker(this);
		}
		return step;
	}

	@Override
	public boolean addEntity() {
		int money=Map.singleton.getMoney(),money_wasted=Map.singleton.getMoney_wasted();
		if (money >= this.getPrice()) {
			Map.singleton.setMoney(money- this.getPrice()); 
			Map.singleton.setMoney_wasted(money_wasted+ this.getPrice());
			Map.singleton.getList_person().add(this);
			return true;
		}
		return false;
	}

	@Override
	public int getPrice() {
		return 0;
	}

	@Override
	public void removeEntity() {
		Map.singleton.getList_person().removeValue(this, true);
		
	}
	/**
	 * 
	 * @return Return this person visibility
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets this person visibility
	 * @param id 0-visible 1-invisible
	 */
	public void setId(int id) {
		this.id = id;
	}
	/*
	 * Place target for the person
	 */
	public Place getTarget() {
		return target;
	}

}
