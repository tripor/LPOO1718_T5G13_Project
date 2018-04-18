package building;

import logic.storage.Demand;

public class Mine extends Place {

	public Demand _demand = new Demand();

	public Mine(int top, int left, int width, int height) {
		super(top, left, width, height);
	}
	public Mine(int row, int col) {
		super(row, col);
	}

}
