package logic.storage;

import java.util.HashMap;

import conveyor.Conveyor;
import place.Place;

public class ConveyorList {
	
	public HashMap<String, Conveyor> conveyorMap = new HashMap<String, Conveyor>();
	// Usage: conveyorMap.get(index(ROW, COL));
	
	public ConveyorList() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addConveyor(Conveyor c) {
		
		int row = c.getRow();
		int col = c.getCol();
		
		if(getConveyor(row, col) == null) {
			conveyorMap.put(index(row, col), c);
			return true;
		}
		return false;
	}
	
	public Conveyor getConveyor(int row, int col) {
		return conveyorMap.get(index(row, col));
	}
	
	public String index(int row, int col) {
		return row + "/" + col;
	}
	
	public int[] checkNextGrid(Conveyor thisObj, int row_delta, int col_delta) {

		int row = thisObj.getRow() + row_delta,
			col = thisObj.getCol() + col_delta;
		
		if(conveyorMap.get(index(row, col)) != null) {
			int[] result = {row, col};
			return result;
		}
		return null;
	}
	
	public int[] next(Conveyor thisObj) {
		
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
