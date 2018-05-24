package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;

import graphic.GroupExtension;
import logic.console.Console;
import logic.map.Map;
import place.Place;

public class PlaceList extends GroupExtension{

	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * List containing all the place in the game
	 */
	public HashMap<String, Place> placeMap = new HashMap<String, Place>();
	
	private ArrayList<Place> lista;
	// public List<Place> placeSet = new ArrayList<Place>();
	/**
	 * Constructor for the Class Place List
	 */
	public PlaceList(GameStage game) {
		this.game=game;
		this.lista= new ArrayList<Place>();
	}
	/**
	 * Missing Description
	 * @param p
	 * @return
	 */
	public boolean addPlace(Place p) {
		
		Console.log("addPlace();");
		
		if(placeMap.get(p.getUniqueId()) != null) {
			// unique Id repeated.
			
			Console.log("-> false. Unique ID repeated.");
			return false;
			// don't let it add.
		}
		
		boolean success = this.game.map().tryAdd(p, this.game);
		
		if(!success) {
			Console.log("-> false. tryAdd() not success.");
			return false;
		}

		// else (= success)
		this.game.map().addMap(
				p,
				p.getX(),
				p.getY(),
				p.getWidth(),
				p.getHeight()
			);
		
		placeMap.put(p.getUniqueId(), p);
		// placeSet.add(p);
		this.addActor(p);
		this.lista.add(p);
		return true;
	}
	/**
	 * Removes the Place from the game and the group of actors
	 * @param p The place I want to remove
	 */
	public void removePlace(Place p) {
		
		Console.log(p.getX() + "," + p.getY());

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
		
		ArrayList<Actor> map = this.game.map().getPixelMap(col, row);
		
		for(Object el : map) {
			
			if(Place.class.isAssignableFrom(el.getClass())) {
				return ((Place) el);
			}
		}
		return null;
	}
	/**
	 * 
	 * @return Returns all the places of the game
	 */
	public ArrayList<Place> getLista() {
		return lista;
	}
	/**
	 * Readds all the place to the group of actors
	 */
	public void reAdd()
	{
		for(Place it:this.lista)
		{
			this.addActor(it);
		}
	}
	
	
	
}
