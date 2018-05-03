package logic.storage;

import java.util.HashMap;

import conveyor.Conveyor;

public class ConveyorList {
	
	public HashMap<String, Conveyor> conveyorMap = new HashMap<String, Conveyor>();
	// Usage: conveyorMap.get(index(ROW, COL));
	
	private static ConveyorList instance = new ConveyorList();
	
	private ConveyorList() {
		// TODO Auto-generated constructor stub
	}
	
	public static ConveyorList getInstance() {
		return instance;
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
}
