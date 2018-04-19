package logic.map;

import java.util.List;
import java.util.Random;

import place.Place;
import logic.path.Node;
import logic.storage.PlaceList;

public class Map {
	
	PlaceList placeList;

	public int mapWidth;
	public int mapHeight;

	public Map() {
		// TODO Auto-generated constructor stub
		
		placeList = new PlaceList();
	}
	
	public boolean addPlace(Place p) {
		boolean result = placeList.addPlace(p);
		
		if(!result) {
			System.out.println("Place not added.");
		}
		return result;
	}
	public void removePlace(Place p) {
		placeList.removePlace(p);
	}
	public List<Place> getPlaceList(){
		return placeList.getPlaceList();
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
