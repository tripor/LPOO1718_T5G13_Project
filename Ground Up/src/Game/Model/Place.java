package Game.Model;

import java.util.ArrayList;
import java.util.List;

public class Place {
	
	public List<Person> _people_here = new ArrayList<Person>();
	public int bound_top;
	public int bound_bottom;
	public int bound_left;
	public int bound_right;
	
	public Place(int row, int col) {
		// for single dot place
		this.bound_top    = row;
		this.bound_right  = col;
		this.bound_bottom = row;
		this.bound_left   = col;
	}
	
	public Place(int top, int left, int width, int height) {
		// for rectangle place
		this.bound_top    = top;
		this.bound_right  = left + width;
		this.bound_bottom = top + height;
		this.bound_left   = left;
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
	
	public boolean overlapWith(Place p) {
		return p.getBoundLeft() <= this.getBoundRight()
				&& p.getBoundRight() >= this.getBoundLeft()
				&& p.getBoundBottom() >= this.getBoundTop()
				&& p.getBoundTop() <= this.getBoundBottom();
	}

}
