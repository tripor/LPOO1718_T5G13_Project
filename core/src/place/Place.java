package place;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

import person.Person;

public class Place extends Actor {
	
	public List<Person> _people_here = new ArrayList<Person>();
	public int	bound_top,
				bound_bottom,
				bound_left,
				bound_right,
				door_col,
				door_row;
	
	public Place(int row, int col) {
		// for single dot place
		
		this.bound_top    = row;
		this.bound_bottom = row;
		this.door_row     = row;
		
		this.bound_right  = col;
		this.bound_left   = col;
		this.door_col     = col;
	}
	
	/**
	 * @param doorAtBorder
	 *  1 = TOP
	 *  2 = RIGHT
	 *  3 = BOTTOM
	 *  4 = LEFT
	 *  
	 * @param doorAtPx
	 *  Clockwise - for easier object rotation.
	 *  i.e.,
	 *    for [TOP, 2] = 3rd pixel from left. (0, 1, {2}<=Third)
	 *        [RIGHT, ...
	 *        [LEFT, 5] = 6th pixel from bottom.
	 *        [BOTTOM, ...
	 */
	public Place(int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		// for rectangle place
		this.bound_top    = top;
		this.bound_right  = left + width;
		this.bound_bottom = top + height;
		this.bound_left   = left;
		
		if(doorAtBorder == 1) {
			// at top, count from left.
			this.door_col = this.bound_left + doorAtPx;
			this.door_row = this.bound_top;
		}
		else if(doorAtBorder == 2) {
			// at right, count from top
			this.door_col = this.bound_right;
			this.door_row = this.bound_top + doorAtPx;
		}
		else if(doorAtBorder == 3) {
			// at bottom, count from right
			this.door_col = this.bound_right - doorAtPx;
			this.door_row = this.bound_bottom;
		}
		else if(doorAtBorder == 4) {
			// at left, count from bottom
			this.door_col = this.bound_left;
			this.door_row = this.bound_bottom - doorAtPx;
		}
	}

	public int getBoundTop() {
		return bound_top;
	}

	public int getBoundBottom() {
		return bound_bottom;
	}

	public int getBoundLeft() {
		return bound_left;
	}

	public int getBoundRight() {
		return bound_right;
	}

	public int getDoorRow() {
		return door_row;
	}

	public int getDoorCol() {
		return door_col;
	}
	
	public boolean overlapWith(Place p) {
		return p.getBoundLeft() <= this.getBoundRight()
				&& p.getBoundRight() >= this.getBoundLeft()
				&& p.getBoundBottom() >= this.getBoundTop()
				&& p.getBoundTop() <= this.getBoundBottom();
	}
	
	public boolean including(int row, int col) {
		return col <= this.getBoundRight()
				&& col >= this.getBoundLeft()
				&& col >= this.getBoundTop()
				&& col >= this.getBoundBottom();
	}

}