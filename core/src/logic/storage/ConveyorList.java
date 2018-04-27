package logic.storage;

import java.util.HashMap;

import conveyor.Conveyor;

public class ConveyorList {
	
	private static int GROUP_SIZE = 40;
	
	public HashMap<String, Conveyor[][]> conveyorMap = new HashMap<String, Conveyor[][]>();
	// Usage: conveyorMap.get(index(ROW, COL));
	
	public ConveyorList() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addConveyor(Conveyor c) {
		
		int row = c.getRow();
		int col = c.getCol();
		
		int row_hash = row / GROUP_SIZE,
			col_hash = col / GROUP_SIZE;
		
		row -= row_hash;
		col -= col_hash;
		
		Conveyor[][] cs = getMap(row_hash, col_hash);
		cs[row][col] = c;
		
		conveyorMap.put(index(row_hash, col_hash), cs);
		
		return true;
	}
	
	public String index(int row, int col) {
		return row + "/" + col;
	}
	
	// this function has already implemented in latest Java.
	public Conveyor[][] getMap(int row, int col) {
		
		Conveyor[][] c = conveyorMap.get(index(row, col));
		
		if(c == null) {
			c = new Conveyor[GROUP_SIZE][GROUP_SIZE];
		}
		return c;
	}
	
	public int[] checkNextGrid(Conveyor thisObj, int row_delta, int col_delta) {

		int row = thisObj.getRow() + row_delta,
			col = thisObj.getCol() + col_delta;
		
		int row_hash = row / GROUP_SIZE,
			col_hash = col / GROUP_SIZE;
		
		int row_rest = row - row_hash,
			col_rest = col - col_hash;
		
		Conveyor[][] cs = getMap(row_hash, col_hash);
		
		if(cs[row_rest][col_rest] != null) {
			int[] result = {row, col};
			return result;
		}
		return null;
	}
	
	public int[] canMoveNext(Conveyor thisObj) {
		
		boolean is_intersection = thisObj.getRowMovement() == 0
									&& thisObj.getColMovement() == 0;
		
		if(is_intersection) {
			
			int[] tmp = null;
			
			for(int row = -1; row <=1; row++) {
				for(int col = -1; col<=1; col++) {

					// TODO: T-shape conveyors, need to make product self-balancing.
					tmp = checkNextGrid(thisObj, row, col);
					if(tmp != null) {
						return tmp;
					}
				}
			}
			return null;
		}
		else {
			return checkNextGrid(thisObj, thisObj.getRowMovement(), thisObj.getColMovement());
		}
		
	}
}
