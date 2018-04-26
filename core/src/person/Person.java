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
	
	private static int grid_size = 100;
	private Map map;
	
	public Person(Map map) {
		this.map = map;
	}
	
	public List<Node> getPath(int _target_row, int _target_col){
		
		this.target_row = _target_row;
		this.target_col = _target_col;
		
		Node initialNode = new Node(current_row/grid_size, current_col/grid_size);
        Node finalNode = new Node(target_row/grid_size, target_col/grid_size);
        int rows = map.getMapHeight()/grid_size;
        int cols = map.getMapWidth()/grid_size;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        aStar.setBlocks(map.getPlaceList(), grid_size);
        
        List<Node> path = aStar.findPath();
        for(Node n : path) {
        		n.setRow(n.getRow() * grid_size);
        		n.setCol(n.getCol() * grid_size);
        }
        
        return path;
	}
	
	public void setCurrentRow(int row) {
		this.current_row = row;
	}
	public void setCurrentCol(int col) {
		this.current_col = col;
	}

}
