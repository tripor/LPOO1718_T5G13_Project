package place.type;

import place.Place;

public class Factory extends Place {
	
	public Factory(int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
	}
	
	public Factory(int row, int col) {
		super(row, col);
	}

}
