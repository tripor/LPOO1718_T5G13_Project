package person;

import java.util.ArrayList;
import java.util.List;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.console.Console;
import logic.path.byAStar.AStar;
import logic.path.byAStar.Node;

/**
 * Class that the top layer of all the persons in the game
 * @author figor
 *
 */
public class Person extends ActorExtension{
	
	public int current_row;
	public int current_col;
	
	public int target_row;
	public int target_col;
	
	public String unique_id = "undefined";
	
	List<Node> path = new ArrayList<Node>();
	
	public Person(GameStage game,int row, int col) {
		this.game=game;
		this.current_col = col;
		this.current_row = row;
	}
	
	public String getId() {
		return this.unique_id;
	}
	public void setId(String id) {
		this.unique_id = id;
	}
	
	public List<Node> getPath(int _target_row, int _target_col, boolean should_replace_global) {

		// Console.log(":: CALC_PATH " + current_row+","+current_col
		//				+ " - " + _target_row+","+_target_col);
		
		Node initialNode = new Node(current_row, current_col);
        Node finalNode = new Node(_target_row, _target_col);
        
        AStar aStar = new AStar(initialNode, finalNode);
        aStar.setBlocks(this.game.getPlace_list().getPlaceList());
        
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
	
	public void setCurrentRow(int row) {
		this.current_row = row;
	}
	public void setCurrentCol(int col) {
		this.current_col = col;
	}
	
	public void setCurrentPos(int row, int col) {
		setCurrentRow(row);
		setCurrentCol(col);
		this.setPosition(col, row);
	}
	
	public Node popPath() {
		
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
		
		while (Math.abs(latestNode.getCol() - current_col) > 1
			|| Math.abs(latestNode.getRow() - current_row) > 1) {
			
			List<Node> smallerPath = this.getPath(latestNode.getRow(), latestNode.getCol(), false);
			
			if(smallerPath.size() < 1) {
				// prevent infinite loop.

				Console.log("\nBREAK > ID=" + this.getId() + " - PATHSIZE=" + path.size()
				
					+ " CUR=" + current_row
					+ "," + current_col

					+ " NEXT#" + latestNode.getRow()
					+ "," + latestNode.getCol()
				
					+ " DELTA@" + Math.abs(latestNode.getRow() - current_row)
					+ "/" + Math.abs(latestNode.getCol() - current_col));
				
				break;
			}
			
			for(Node n : path) {
				smallerPath.add(n);
			}
			// performance VS smallerPath.addAll(path);
			
			path = smallerPath;
			latestNode = path.remove(0);
		}
		
		this.setCurrentPos(latestNode.getRow(), latestNode.getCol());
		// Console.log("POP > " + this.toString());

		return latestNode;
	}

	public String toString() {
		return "[Person " + unique_id + "]"
				+ " Row " + current_row
				+ " Col " + current_col;
	}
	
	public int getRow() {
		return this.current_row;
	}
	public int getCol() {
		return this.current_col;
	}
//	public void sizePlace(float amountX,float amountY)
//	{
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}
}
