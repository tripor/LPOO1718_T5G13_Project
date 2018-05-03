package conveyor;

import logic.storage.ConveyorList;
import place.Place;

public class Conveyor {
	
	// If a product passes through this pipe => add ? pixels to it's position.
	int movement_row = 0;
	int movement_col = 0;
	
	int row, col;
	
	ConveyorList conveyorList = ConveyorList.getInstance();

	public Conveyor(int row, int col, int direction) {
		
		this.row = row;
		this.col = col;
		
		if(direction == 1) {	// Upwards
			movement_row = -1;
		}
		else if(direction == 2) {	// go right
			movement_col = +1;
		}
		else if(direction == 3) {	// Downwards
			movement_row = +1;
		}
		else if(direction == 4) {	// go left
			movement_col = -1;
		}
	}

	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int getRowMovement() {
		return movement_row;
	}
	public int getColMovement() {
		return movement_col;
	}
	
	public boolean connectedFrom(Place p) {
		
		// we need to get it's REVERSED direction
		// in order to know whether this conveyor can get things from the building
		
		int movement_row = 0 - this.movement_row;
		int movement_col = 0 - this.movement_col;
		
		if(p.including(this.row - movement_row, this.col - movement_col)) {
			return true;
		}
		return false;
	}

	
	public int[] checkNextGrid(Conveyor thisObj, int row_delta, int col_delta) {

		int row = thisObj.getRow() + row_delta,
			col = thisObj.getCol() + col_delta;
		
		if(conveyorList.getConveyor(row, col) != null) {
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
