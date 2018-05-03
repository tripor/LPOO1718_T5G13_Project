package logic.map;

import java.util.List;
import java.util.Random;

import place.Place;
import logic.console.Console;
import logic.storage.ConveyorList;
import logic.storage.PlaceList;

public class Map {
	
	PlaceList placeList;
	ConveyorList conveyorList;

	public int mapWidth = 0;
	public int mapHeight = 0;
	
	private static Map instance = new Map();

	private Map() {
		// TODO Auto-generated constructor stub
		
		placeList = PlaceList.getInstance();
		conveyorList = ConveyorList.getInstance();
	}
	
	public static Map getInstance () {
		return instance;
	}
	
	// i - just for testing.
	int i = 0;
	
	public boolean addPlace(Place p) {
		boolean result = placeList.addPlace(p);
		
		// for testing.
		i++;
		if(!result) {Console.log("Place " + i + " not added.");}
		
		return result;
	}
	public void removePlace(Place p) {
		placeList.removePlace(p);
	}
	public List<Place> getPlaceList(){
		return placeList.getPlaceList();
	}
	public List<Place> getPlaceList(int row, int col){
		return placeList.getPlaceList(row, col);
	}
	
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public int randRow() {
		return (new Random()).nextInt(this.getMapHeight());
	}
	public int randCol() {
		return (new Random()).nextInt(this.getMapWidth());
	}

}
