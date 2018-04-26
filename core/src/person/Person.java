package person;

import java.util.List;

import logic.map.Map;
import logic.path.byAStar.AStar;
import logic.path.byAStar.Node;

public class Person {
	
	public int current_row;
	public int current_col;
	
	public int target_row;
	public int target_col;
	
	private Map map;
	
	public Person(Map map) {
		this.map = map;
	}
	
	public List<Node> getPath(int _target_row, int _target_col){
		
		this.target_row = _target_row;
		this.target_col = _target_col;
		
		Node initialNode = new Node(current_row, current_col);
        Node finalNode = new Node(target_row, target_col);
        int rows = map.getMapHeight();
        int cols = map.getMapWidth();
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        aStar.setBlocks(map.getPlaceList());
		return aStar.findPath();
	}
	
	public void setCurrentRow(int row) {
		this.current_row = row;
	}
	public void setCurrentCol(int col) {
		this.current_col = col;
	}

}
