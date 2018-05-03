package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import place.Place;

public class PlaceList {
	
	private static int GROUP_SIZE = 40;
	// split the full map into 40x40 groups.
	
	public List<Place> placeSet = new ArrayList<Place>();
	public HashMap<String, List<Place>> placeMap = new HashMap<String, List<Place>>();
	// Usage: placeMap.get(index(ROW, COL));
	
	public PlaceList() {
		
	}
	
	public boolean addPlace(Place p) {
		
		int left = p.getBoundLeft()/GROUP_SIZE, right  = p.getBoundRight()/GROUP_SIZE,
			top  = p.getBoundTop()/GROUP_SIZE,  bottom = p.getBoundBottom()/GROUP_SIZE;
		
		int col, row;
		
		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {
				
				List<Place> place_list = placeMap.get(index(row, col));
				
				if(place_list == null) {
					continue;
				}
				for(Place pl : place_list) {
					if(pl.overlapWith(p)) {
						return false;
					}
				}
			}
		}

		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {

				List<Place> pl = getMap(row, col);
				pl.add(p);
				
				placeMap.put(index(row, col), pl);
			}
		}
		placeSet.add(p);
		p.setUniqueId("R" + p.getBoundTop() + "C" + p.getBoundLeft());
		return true;
	}
	
	public void removePlace(Place p) {
		
		int left = p.getBoundLeft()/GROUP_SIZE, right  = p.getBoundRight()/GROUP_SIZE,
			top  = p.getBoundTop()/GROUP_SIZE,  bottom = p.getBoundBottom()/GROUP_SIZE;
		
		int row, col;

		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {
				placeMap.get(index(row, col)).remove(p);
			}
		}
		placeSet.remove(p);
	}
	
	public List<Place> getPlaceList(){
		return placeSet;
	}
	
	public List<Place> getPlaceList(int row, int col) {
		
		row = row / GROUP_SIZE;
		col = col / GROUP_SIZE;
		
		return getMap(row, col);
	}
	
	public String index(int row, int col) {
		return row + "/" + col;
	}
	
	// this function has already implemented in latest Java.
	public List<Place> getMap(int row, int col) {
		
		List<Place> p = placeMap.get(index(row, col));
		
		if(p == null) {
			p = new ArrayList<Place>();
		}
		return p;
	}
}
