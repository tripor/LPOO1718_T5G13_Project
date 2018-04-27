package conveyor;

import place.Place;

public class Conveyor {
	
	// If a product passes through this pipe => add ? pixels to it's position.
	int movement_row = 0;
	int movement_col = 0;
	
	int row, col;

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
}
