package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import logic.console.Console;
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
	public List<Place> placeSet = new ArrayList<Place>();
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
		
		int left = p.getBoundLeft()/Map.division, right  = p.getBoundRight()/Map.division,
			top  = p.getBoundTop()/Map.division,  bottom = p.getBoundBottom()/Map.division;
		
		int col, row;
		
		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {
				
				ArrayList<Object> element_list = this.game.map().getMap(row, col);
				
				// todo: check point, but not check block.
				if(element_list.size() > 0) {
					return false;
				}
			}
		}

		p.setUniqueId("R" + p.getBoundTop() + "C" + p.getBoundLeft());
		this.game.map().addMap(
				p,
				p.getBoundTop(),
				p.getBoundLeft(),
				(p.getBoundRight() - p.getBoundLeft()),
				(p.getBoundBottom() - p.getBoundTop())
			);
		
		placeSet.add(p);
		this.addActor(p);
		
		return true;
	}
	/**
	 * Missing Description
	 * @param p
	 */
	public void removePlace(Place p) {
		
		placeSet.remove(p);
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
	public List<Place> getPlaceList(){
		return placeSet;
	}
	/**
	 * Randomly return a building on the map
	 */
	public Place getRandomPlace() {
		return placeSet.get((new Random()).nextInt(placeSet.size()));
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
			places_size = placeSet.size();
		Place result;
		
		while(true) {
			
			if(index >= 0) {
				// that means, a repeat happened before.
				index = getRandomNumAvoid(index, places_size);
			}
			else {
				index = ((new Random()).nextInt(places_size));
			}
			
			result = placeSet.get(index);
			
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
		
		ArrayList<Object> map = this.game.map().getMap(top, left);
		
		for(Object el : map) {
			
			if(el.getClass().equals(Place.class)) {
				
				if(((Place) el).including(row, col)) {
					return ((Place) el);
				}
			}
		}
		return null;
	}
}
