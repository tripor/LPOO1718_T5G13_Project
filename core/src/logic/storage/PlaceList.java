package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import graphic.ActorExtension;
import logic.map.Map;
import person.Person;
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
		
		boolean success = this.game.map().tryAdd(p, this.game);
		
		if(!success) {
			return false;
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
	 * Removes the Place from the game and the group of actors
	 * @param p The place I want to remove
	 */
	public void removePlace(Place p) {

		placeMap.remove(p.getUniqueId());
		// placeSet.remove(p);
		this.game.map().removeMap(
				p,
				(int)p.getX(),
				(int)p.getY(),
				(int)p.getWidth(),
				(int)p.getHeight()
			);
		this.removeActor(p);
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

		int top = Map.getBlockIndex(row),
			left = Map.getBlockIndex(col);
		
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
