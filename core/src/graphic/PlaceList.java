package graphic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;



public class PlaceList extends GroupExtension{

	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * List containing all the place in the game
	 */
	public HashMap<String, PlaceGraphical> placeMap = new HashMap<String, PlaceGraphical>();
	
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
	public boolean addPlace(PlaceGraphical p) {
		if(this.game.map.addMap(p.instance))
		{
			this.addActor(p);
			return true;
		}
		return false;
	}
	/**
	 * Removes the Place from the game and the group of actors
	 * @param p The place I want to remove
	 */
	public void removePlace(PlaceGraphical p) {
		this.removeActor(p);
		this.game.map.removeMap(p.instance);
	}
	/**
	 * Randomly return a building on the map
	 */
	/*public Place getNthBuilding(int n) {
		
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
	}*/
	/**
	 * Randomly return a building, but avoid the building `s`.
	 */
	/*public int getRandomNumAvoid(int num, int max) {
		
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
		
	}*/
	/**
	 * Missing Description
	 * @param row
	 * @param col
	 * @return
	 */
	/*public Place checkIfPointInBuilding(int row, int col) {
		
		ArrayList<Actor> map = this.game.map().getMap(row, col);
		
		for(Object el : map) {
			
			if(Place.class.isAssignableFrom(el.getClass())) {
				
				if(((Place) el).including(row, col)) {
					return ((Place) el);
				}
			}
		}
		return null;
	}*/
	
	
	
}
