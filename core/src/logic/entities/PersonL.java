package logic.entities;

import java.util.ArrayList;

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
	
	public static int width=5;
	public static int height=5;
	
	private int target_x, target_y;
	
	private int current_step;
	private ArrayList<String> prevSteps = new ArrayList<String>();
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
	
	private String posString(int posX, int posY) {
		return posX + "," + posY;
	}
	
	private void pushStep(int posX, int posY) {
		prevSteps.add(this.current_step, posString(posX, posY));
		this.current_step += 1;
		this.posX = posX;
		this.posY = posY;
		Console.log("Steps=" + prevSteps.size() + ", Cur=" + (this.current_step));
	}
	
	private void reinitStep() {
		current_step = 0;
		prevSteps = new ArrayList<String>();
		pushStep(this.posX, this.posY);
	}
	
	public PersonL(int posX, int posY) {
		super(posX,posY,PersonL.width,PersonL.height);

		// this.unique_id = UUID.randomUUID().toString();
		// (removed) a duplication checking at personList is already performed.

		Console.log("new PersonL(" +posX + "," + posY+ ");");
		
		reinitStep();
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
		reinitStep();
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

		Array<Entity> target_pixel_elements;
		int target_pixel_elements_size;
		
		int selected_order[] = order[take_order];
		
	//	String s = "";
	//	for(int i=0; i<selected_order.length; i++) {
	//		s += selected_order[i];
	//	}
	//	
	//	Console.log("Check Order = " + s);
		
		for(int i = 0; i < selected_order.length; i++) {

			Console.log(": Checking " + ((selected_order[i] % 3) - 1)  + "," + ((selected_order[i]/3) - 1));

			tmpY = posY - (selected_order[i]/3) + 1;	// posY - [-1 | 0 | 1]
			tmpX = posX + (selected_order[i] % 3) - 1;	// posX + [-1 | 0 | 1]
			
			boolean should_skip = false;
			
			int repeatStepIndex = prevSteps.indexOf(posString(tmpX, posY));
			
			if(repeatStepIndex <= -1) {
				// didn't repeat = pass.
			}
			else if(repeatStepIndex >= (current_step - 1)) {
				should_skip = true;
				Console.log("Repeated - " + repeatStepIndex + ">=" + current_step);
			}
			else {
				Console.log("Changed - Index=" + repeatStepIndex + "<" + current_step + " - Str=" + posString(posX, posY));
				current_step = repeatStepIndex;
			}
			
			if(should_skip) {
				// skip.
				// Console.log("Skipped.");
			}
			else {
				
				target_pixel_elements = Map.singleton.getMapPercisionPixel(tmpX, tmpY);
				target_pixel_elements_size = target_pixel_elements.size;
				
				// Console.log(tmp_array_size);
				
				if(target_pixel_elements.contains(this, false)){
					target_pixel_elements_size--;
				}
				
				if(target_pixel_elements_size < 1) {

					pushStep(tmpX, tmpY);
					Map.singleton.removeMap(this);
					Map.singleton.addMap(this);
					break;
				}
			}
		}
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

		if(step < 1) {
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
	
	
	
//	public void sizePlace(float amountX,float amountY)
//	{
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}

}
