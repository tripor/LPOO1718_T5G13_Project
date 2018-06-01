package logic.entities;

import graphic.Console;
//import logic.AStar;
//import logic.Node;
import logic.Entity;
import logic.Map;
import logic.Place;

public class PersonL extends Entity{
	
	public static int width=5;
	public static int height=5;
	
	private int target_x, target_y;
	
//	private int current_step;
//	private ArrayList<String> prevSteps = new ArrayList<String>();
	/**
	 * Target of this person
	 */
	private Place target;
	
	
//	private String unique_id;
	
	/**
	 * ID. 0=visivel 1-invisivel
	 */
	private transient int id=1;
	
//	List<Node> path = new ArrayList<Node>();
	
	public PersonL(int posX, int posY) {
		super(posX,posY,PersonL.width,PersonL.height);

		// this.unique_id = UUID.randomUUID().toString();
		// (removed) a duplication checking at personList is already performed.

		Console.log("new PersonL(" +posX + "," + posY+ ");");
		
		this.target_x = posX;
		this.target_y = posY;
		this.id=1;
	}
	
	public PersonL() {
		super();
		this.id=0;
	}
	
//	public String getId() {
//		return this.unique_id;
//	}
	
	public void setTarget(Place p) {
		this.target_x = p.doorXposition();
		this.target_y = p.doorYposition();
		this.target=p;
		Console.log("this.setTarget(" +target_x + "," + target_y+ ");");
	}
	
	public void getPath() {
		
		if(target_x == this.posX && target_y == this.posY) {
			return;
		}
		
		Console.log("getPath(" +posX+ ","+posY+ " to "+target_x+ ","+target_y+");");
		
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
		
		Console.log(
				(tmpX < posX ? "LEFT " : (tmpX > posX ? "RIGHT " : ""))
				+ (tmpY > posY ? "TOP" : (tmpY < posY ? "BOT" : ""))
			);
		
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
			while(true) {
				tmpX = posX + (try_order[tIndex] / 10) - 5;
				tmpY = posY + (try_order[tIndex] % 10) - 5;
				
				if(Map.singleton.pointIsOccupied(tmpX, tmpY, this)) {
					if(++tIndex >= try_order.length) {
						tIndex = 0;
					}
				}
				else {
				//	Console.log("FINAL==" +
				//			(tmpX < posX ? "LEFT " : (tmpX > posX ? "RIGHT " : ""))
				//			+ (tmpY > posY ? "TOP" : (tmpY < posY ? "BOT" : ""))
				//		);
					break;
				}
			}
		}
		Map.singleton.removeMap(this);
		this.posX = tmpX;
		this.posY = tmpY;
		Map.singleton.addMap(this);
	}
	
	int step = 0;
	
//	public List<Node> getPath(int _target_row, int _target_col, boolean should_replace_global) {
//
//		// Console.log(":: CALC_PATH " + current_row+","+current_col+ " - " + _target_row+","+_target_col);
//		
//		Node initialNode = new Node(current_row, current_col);
//        Node finalNode = new Node(_target_row, _target_col);
//        
//        AStar aStar = new AStar(map, initialNode, finalNode);
//        // aStar.setBlocks(this.game.places().getPlaceList());
//        
//        List<Node> thisPath = aStar.findPath();
//        
//        if(should_replace_global) {
//            this.path = thisPath;
//	    		this.target_row = _target_row;
//	    		this.target_col = _target_col;
//        }
//        
//        // Console.log(":: FINISH => Person " + this.getId() + " " + thisPath.size() + " steps.");
//        
//        return thisPath;
//	}
//	
//	public List<Node> getPath(int _target_row, int _target_col){
//		return getPath(_target_row, _target_col, true);
//	}
//	
//	public Node popPath() {
//		
//		// Console.log("Path Size = " + path.size());
//		
//		if(path.size() < 1) {
//			// Console.log(this.getId() + " path-end");
//			return null;
//		}
//		
//		Node latestNode = path.remove(0);
//		
////		Place nextStep_building = PlaceList.getInstance().checkIfPointInBuilding(latestNode.getRow(), latestNode.getCol());
//		
////		if(nextStep_building != null) {
////			
////			this.current_col = nextStep_building.getDoorCol();
////			this.current_row = nextStep_building.getDoorRow();
////			
////			// Console.log("Person " + this.getId() + "crushed to the wall, at "+current_row+","+current_col+" for target "+target_row+","+target_col+".");
////			
////			this.getPath(target_row, target_col);
////			latestNode = path.remove(0);
////		}
//		
//		while (latestNode.isJumping() == false && (Math.abs(latestNode.getCol() - current_col) > 1
//			|| Math.abs(latestNode.getRow() - current_row) > 1)) {
//			
//			List<Node> smallerPath = this.getPath(latestNode.getRow(), latestNode.getCol(), false);
//			
//			if(smallerPath.size() < 1) {
//				// prevent infinite loop.
//
//				//	Console.log("BREAK > ID=" + this.getId() + " - PATHSIZE=" + path.size()
//				//	
//				//		+ " CUR=" + current_row
//				//		+ "," + current_col
//				//
//				//		+ " NEXT#" + latestNode.getRow()
//				//		+ "," + latestNode.getCol()
//				//	
//				//		+ " DELTA@" + Math.abs(latestNode.getRow() - current_row)
//				//		+ "/" + Math.abs(latestNode.getCol() - current_col));
//				//	
//				break;
//			}
//			
//			for(Node n : path) {
//				smallerPath.add(n);
//			}
//			// performance VS smallerPath.addAll(path);
//			
//			path = smallerPath;
//			latestNode = path.remove(0);
//		}
//
//		map.removeMap(this);
//		this.posX = latestNode.getCol();
//		this.posY = latestNode.getRow();
//		map.addMap(this);
//
//		// Console.log("POP > " + this.toString());
//
//		return latestNode;
//	}

	public String toString() {
		return "[Person]"
				+ " Row " + this.posX
				+ " Col " + this.posY;
	}

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
		
		return 0;
	}

	@Override
	public boolean addEntity() {
		int money=Map.singleton.getMoney(),money_wasted=Map.singleton.getMoney_wasted();
		if (money >= this.getPrice()) {
			Map.singleton.setMoney(money- this.getPrice()); 
			Map.singleton.setMoney_wasted(money_wasted+ this.getPrice());
			Map.singleton.getLista_person().add(this);
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
		Map.singleton.getLista_person().removeValue(this, true);
		
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
	
	
	
	
	
//	public void sizePlace(float amountX,float amountY)
//	{
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}

}
