package logic.entities;

import com.badlogic.gdx.utils.Array;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;

import graphic.Console;
//import logic.AStar;
import logic.Entity;
import logic.Map;
import logic.Place;
//import logic.Node;

public class PersonL extends Entity{
	
	private int target_x, target_y;
	
	private int save_step = 7;
	private int cur_step = 0;
	
	private int[] prevX = new int[save_step];
	private int[] prevY = new int[save_step];
	
	
//	private String unique_id;
	
	/**
	 * ID. 0=visivel 1-invisivel
	 */
	public transient int id=1;
	
//	List<Node> path = new ArrayList<Node>();
	
	
	
	public PersonL(int posX, int posY) {
		super(posX,posY,5,5);

		// this.unique_id = UUID.randomUUID().toString();
		// (removed) a duplication checking at personList is already performed.

		Console.log("new PersonL(" +posX + "," + posY+ ");");
		
		for(int i=0; i<save_step; i++) {
			prevX[i] = posX;
			prevY[i] = posY;
		}		
		this.target_x = posX;
		this.target_y = posY;
	}
	
	public PersonL() {
		super();
		this.id=0;
	}
	
//	public String getId() {
//		return this.unique_id;
//	}
	
	public void setTarget(Place p) {
		this.target_x = p.getPosX();
		this.target_y = p.getPosY();
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
		
		/**
		 * Perferred orders for different angles.
		 */
		int order[][] = {
				{0, 1, 3, 2, 5, 6, 7, 8},	//   to left-top
				{1, 0, 2, 3, 5, 6, 8, 7},	// 1 to top
				{2, 1, 5, 0, 3, 8, 7, 6},	//   to right-top
				{3, 0, 6, 1, 7, 2, 8, 5},	//   to left
				{                      },	// 4 stay
				{5, 2, 8, 1, 7, 6, 0, 3},	//   to right
				{6, 3, 7, 8, 0, 5, 2, 1},	//   to left-bottom
				{7, 8, 6, 5, 3, 0, 2, 1},	// 7 to bottom
				{8, 5, 7, 6, 2, 1, 0, 3},	//   to right-bottom
		};
		
		/**
		 * determine which order should be taken.
		 */
		int take_order = 4;	// default, stay
		
		if(at_top) {
			take_order = 1;
		}
		else if(at_bottom) {
			take_order = 7;
		}
		
		if(at_left) {
			take_order--;
		}
		else if(at_right) {
			take_order++;
		}
		
		/**
		 * check grid one-by-one in the selected order
		 */
		int tmpX = posX,
			tmpY = posY;

		Array<Entity> tmp_array;
		int tmp_array_size;
		
		int selected_order[] = order[take_order];
		
		for(int i = 0; i < selected_order.length; i++) {

			// Console.log(": Checking " + ((selected_order[i] % 3) - 1)  + "," + ((selected_order[i]/3) - 1));

			tmpY = posY - (selected_order[i]/3) + 1;	// posY - [-1 | 0 | 1]
			tmpX = posX + (selected_order[i] % 3) - 1;	// posX + [-1 | 0 | 1]
			
			boolean should_skip = false;
			
			for(int s=0; s<save_step; s++) {
				if(prevX[s]==posX && prevY[s]==posY) {
					should_skip = true;
					break;
				}
			}
			
			if(should_skip) {
				// skip.
				// Console.log("Skipped.");
			}
			else {
				
				tmp_array = Map.singleton.getMapPercisionPixel(tmpX, tmpY);
				tmp_array_size = tmp_array.size;
				
				Console.log(tmp_array_size);
				
				if(tmp_array.contains(this, false)){
					tmp_array_size--;
				}
				
				if(tmp_array_size < 1) {

					prevX[cur_step] = posX;
					prevY[cur_step] = posY;
					cur_step++;
					
					if(cur_step >= save_step) {
						cur_step = 0;
					}

					Map.singleton.removeMap(this);
					this.posX = tmpX;
					this.posY = tmpY;
					Map.singleton.addMap(this);
					break;
				}
			}
		}
	}
	
	int step = 0;
	
	public void updatePersonPos() {
		if(step < 10) {
			step++;
		}
		else {
			step = 0;
			this.getPath();
		}
	}
	
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
	
//	public void sizePlace(float amountX,float amountY)
//	{
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}

}
