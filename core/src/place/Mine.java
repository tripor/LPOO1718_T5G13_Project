package place;

import logic.storage.Demand;

public class Mine extends Place {

	public Demand _demand = new Demand();
	
	public Mine(int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
	}

	public Mine(int row, int col) {
		super(row, col);
	}

}
