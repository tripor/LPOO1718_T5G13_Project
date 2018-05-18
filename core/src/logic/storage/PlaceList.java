package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import logic.map.Map;
import place.Place;

public class PlaceList extends Group{

	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * List containing all the place in the game
	 */
	public HashMap<String, Place> placeMap = new HashMap<String, Place>();
	// public List<Place> placeSet = new ArrayList<Place>();
	/**
	 * Constructor for the Class Place List
	 */
	public PlaceList(GameStage game) {
		this.game=game;
		
	}
	/**
	 * Missing Description
	 * @param p
	 * @return
	 */
	public boolean addPlace(Place p) {
		
		if(placeMap.get(p.getUniqueId()) != null) {
			// unique Id repeated.
			
			return false;
			// don't let it add.
		}
		
		int left = p.getBoundLeft()/Map.division, right  = p.getBoundRight()/Map.division,
			top  = p.getBoundTop()/Map.division,  bottom = p.getBoundBottom()/Map.division;
		
		int col, row;
		
		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {
				
				// get map blocks.
				ArrayList<Actor> element_list = this.game.map().getMap(row, col);

				// loop through the block, find the place which overlaps with p.
				for(Object el : element_list) {
					if(Place.class.isAssignableFrom(el.getClass())) {
						
						// if found, return false (= addPlace not successful)
						if(((Place) el).overlapWith(p)) {
							return false;
						}
					}
				}
			}
		}

		// else (= success)
		this.game.map().addMap(
				p,
				p.getBoundTop(),
				p.getBoundLeft(),
				(p.getBoundRight() - p.getBoundLeft()),
				(p.getBoundBottom() - p.getBoundTop())
			);
		
		placeMap.put(p.getUniqueId(), p);
		// placeSet.add(p);
		this.addActor(p);
		
		return true;
	}
	/**
	 * Missing Description
	 * @param p
	 */
	public void removePlace(Place p) {

		placeMap.remove(p.getUniqueId());
		// placeSet.remove(p);
		this.game.map().removeMap(
				p,
				p.getBoundTop(),
				p.getBoundLeft(),
				(p.getBoundRight() - p.getBoundLeft()),
				(p.getBoundBottom() - p.getBoundTop())
			);
	}
	/**
	 * Get all the place in the map
	 * @return
	 */
	public HashMap<String, Place> getPlaceList(){
		return this.placeMap;
	}
	/**
	 * Randomly return a building on the map
	 */
	public Place getNthBuilding(int n) {
		
		Iterator<Entry<String, Place>> i = placeMap.entrySet().iterator();

		while(n-- >= 1) {
			if(i.hasNext()) {
				i.next();
			}
		}
		
	    java.util.Map.Entry<String, Place> entry = (java.util.Map.Entry<String, Place>) i.next();
		// index = 0.
	    
		return (Place) entry.getValue();
		
	}
	
	public Place getRandomPlace() {
		
		int index = (new Random()).nextInt(placeMap.size());
		return getNthBuilding(index);
	}
	/**
	 * Randomly return a building, but avoid the building `s`.
	 */
	public int getRandomNumAvoid(int num, int max) {
		
		if(max <= 1) {
			return -1;
		}
		int result = ((new Random()).nextInt(max-1));
		
		if(result >= num) {
			result++;
		}
		return result;
	}
	public Place getRandomPlace(Place s) {

		int index = -1,
			places_size = placeMap.size();
		Place result;
		
		while(true) {
			
			if(index >= 0) {
				// that means, a repeat happened before.
				index = getRandomNumAvoid(index, places_size);
			}
			else {
				index = ((new Random()).nextInt(places_size));
			}
			
			result = getNthBuilding(index);
			
			if(!result.equals(s)) {
				break;
			}
		}
		
		return result;
		
	}
	/**
	 * Missing Description
	 * @param row
	 * @param col
	 * @return
	 */
	public Place checkIfPointInBuilding(int row, int col) {

		int top = row/Map.division,
			left = col/Map.division;
		
		ArrayList<Actor> map = this.game.map().getMap(top, left);
		
		for(Object el : map) {
			
			if(Place.class.isAssignableFrom(el.getClass())) {
				
				if(((Place) el).including(row, col)) {
					return ((Place) el);
				}
			}
		}
		return null;
	}
}
