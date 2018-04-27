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
	
	public boolean nextConveyor(Conveyor thisObj) {
		
		int row = thisObj.getRow() + thisObj.getRowMovement(),
			col = thisObj.getCol() + thisObj.getColMovement();
		
		// TODO: T-shape conveyors.
		
		int row_hash = row / GROUP_SIZE,
			col_hash = col / GROUP_SIZE;
		
		row -= row_hash;
		col -= col_hash;
		
		Conveyor[][] cs = getMap(row, col);
		
		if(cs[row][col] != null) {
			return true;
		}
		return false;
	}
}
