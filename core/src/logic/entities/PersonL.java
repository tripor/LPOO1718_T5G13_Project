package logic.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import logic.AStar;
import logic.Entity;
import logic.Map;
import logic.Node;

public class PersonL extends Entity{
	
	public int current_row, current_col;
	public int target_row, target_col;
	
	public String unique_id;
	
	List<Node> path = new ArrayList<Node>();
	
	private Map map;
	
	public PersonL(int posX, int posY, Map map) {
		super(posX,posY,5,5);

		this.map = map;
		this.unique_id = UUID.randomUUID().toString();
		// a duplication checking at personList is already performed.
	}
	
	public PersonL() {super();}
	
	public String getId() {
		return this.unique_id;
	}
	
	public List<Node> getPath(int _target_row, int _target_col, boolean should_replace_global) {

		// Console.log(":: CALC_PATH " + current_row+","+current_col+ " - " + _target_row+","+_target_col);
		
		Node initialNode = new Node(current_row, current_col);
        Node finalNode = new Node(_target_row, _target_col);
        
        AStar aStar = new AStar(map, initialNode, finalNode);
        // aStar.setBlocks(this.game.places().getPlaceList());
        
        List<Node> thisPath = aStar.findPath();
        
        if(should_replace_global) {
            this.path = thisPath;
	    		this.target_row = _target_row;
	    		this.target_col = _target_col;
        }
        
        // Console.log(":: FINISH => Person " + this.getId() + " " + thisPath.size() + " steps.");
        
        return thisPath;
	}
	
	public List<Node> getPath(int _target_row, int _target_col){
		return getPath(_target_row, _target_col, true);
	}
	
	public Node popPath() {
		
		// Console.log("Path Size = " + path.size());
		
		if(path.size() < 1) {
			// Console.log(this.getId() + " path-end");
			return null;
		}
		
		Node latestNode = path.remove(0);
		
//		Place nextStep_building = PlaceList.getInstance().checkIfPointInBuilding(latestNode.getRow(), latestNode.getCol());
		
//		if(nextStep_building != null) {
//			
//			this.current_col = nextStep_building.getDoorCol();
//			this.current_row = nextStep_building.getDoorRow();
//			
//			// Console.log("Person " + this.getId() + "crushed to the wall, at "+current_row+","+current_col+" for target "+target_row+","+target_col+".");
//			
//			this.getPath(target_row, target_col);
//			latestNode = path.remove(0);
//		}
		
		while (latestNode.isJumping() == false && (Math.abs(latestNode.getCol() - current_col) > 1
			|| Math.abs(latestNode.getRow() - current_row) > 1)) {
			
			List<Node> smallerPath = this.getPath(latestNode.getRow(), latestNode.getCol(), false);
			
			if(smallerPath.size() < 1) {
				// prevent infinite loop.

				//	Console.log("BREAK > ID=" + this.getId() + " - PATHSIZE=" + path.size()
				//	
				//		+ " CUR=" + current_row
				//		+ "," + current_col
				//
				//		+ " NEXT#" + latestNode.getRow()
				//		+ "," + latestNode.getCol()
				//	
				//		+ " DELTA@" + Math.abs(latestNode.getRow() - current_row)
				//		+ "/" + Math.abs(latestNode.getCol() - current_col));
				//	
				break;
			}
			
			for(Node n : path) {
				smallerPath.add(n);
			}
			// performance VS smallerPath.addAll(path);
			
			path = smallerPath;
			latestNode = path.remove(0);
		}

		map.removeMap(this);
		this.posX = latestNode.getCol();
		this.posY = latestNode.getRow();
		map.addMap(this);

		// Console.log("POP > " + this.toString());

		return latestNode;
	}

	public String toString() {
		return "[Person " + unique_id + "]"
				+ " Row " + this.posX
				+ " Col " + this.posY;
	}
//	public void sizePlace(float amountX,float amountY)
//	{
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}

}
