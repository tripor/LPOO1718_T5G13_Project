package Model.Place;

import Game.Model.Place;
import Game.Storage.Demand;

public class Mine extends Place {

	public Demand _demand = new Demand();

	public Mine(int top, int left, int width, int height) {
		super(top, left, width, height);
	}
	public Mine(int row, int col) {
		super(row, col);
	}

}
