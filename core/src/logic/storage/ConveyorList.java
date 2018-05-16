package logic.storage;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.Group;

import conveyor.Conveyor;

public class ConveyorList extends Group{
	
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
			this.addActor(c);
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
}
