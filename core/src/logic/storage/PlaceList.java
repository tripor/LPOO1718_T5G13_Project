package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import logic.console.Console;
import logic.map.Map;
import place.Place;

public class PlaceList {
	
	private static int GROUP_SIZE = 1500;
	// split the full map into groups, the size should be = Map.getInstance().getbuildingMaxSize() * 15
	
	public List<Place> placeSet = new ArrayList<Place>();
	public HashMap<String, List<Place>> placeMap = new HashMap<String, List<Place>>();
	// Usage: placeMap.get(index(ROW, COL));
	
	private static PlaceList instance = new PlaceList();
	
	private PlaceList() {
		// TODO Auto-generated constructor stub
	}
	
	public static PlaceList getInstance() {
		return instance;
	}
	
	public boolean addPlace(Place p) {
		
		int left = p.getBoundLeft()/GROUP_SIZE, right  = p.getBoundRight()/GROUP_SIZE,
			top  = p.getBoundTop()/GROUP_SIZE,  bottom = p.getBoundBottom()/GROUP_SIZE;
		
		int col, row;
		
		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {
				
				List<Place> place_list = placeMap.get(index(row, col));
				
				if(place_list == null) {
					// Console.log("> NULL LIST");
					continue;
				}

				// Console.log("> LIST WITH " + place_list.size() + " ELEMENTS");
				
				for(Place pl : place_list) {
					// Console.log("\n  > COMPARE PLACE " + pl.getUniqueId());
					// Console.log("  THIS=  " + p.toString());
					// Console.log("  " + pl.toString());
					
					if(pl.overlapWith(p)) {
						// Console.log(">> Overlap.");
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
	
	public Place checkIfPointInBuilding(int row, int col) {
		
		List<Place> map = getPlaceList(row, col);
		
		for(Place p : map) {
			if(p.including(row, col)) {
				return p;
			}
		}
		return null;
	}
}
