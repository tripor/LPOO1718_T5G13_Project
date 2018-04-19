package logic.storage;

import java.util.ArrayList;
import java.util.List;

import building.Place;

public class PlaceList {
	
	public List<Place> place = new ArrayList<Place>();
	
	public PlaceList() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addPlace(Place p) {
		
		for(Place pl : this.place) {
			if(pl.overlapWith(p)) {
				return false;
			}
		}
		place.add(p);
		return true;
	}
	public void removePlace(Place p) {
		place.remove(p);
	}
	public List<Place> getPlaceList(){
		return place;
	}

}
