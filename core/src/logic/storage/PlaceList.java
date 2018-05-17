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
	 * split the full map into groups, the size should be = Map.getInstance().getbuildingMaxSize() * 15
	 */
	private static int GROUP_SIZE = 1500;
	
	/**
	 * List containing all the place in the game
	 */
	public List<Place> placeSet = new ArrayList<Place>();
	/**
	 * Grouped place list,
	 * for better performance on getting a building by coordinates.
	 * @usage placeMap.get(index(ROW, COL));
	 */
	public HashMap<String, List<Place>> placeMap = new HashMap<String, List<Place>>();
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
		
		this.addActor(p);
		
		return true;
	}
	/**
	 * Missing Description
	 * @param p
	 */
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
	 * Get all the place nearby a given coordinate.
	 * @param row
	 * @param col
	 * @return
	 */
	public List<Place> getPlaceList(int row, int col) {
		
		row = row / GROUP_SIZE;
		col = col / GROUP_SIZE;
		
		return getMap(row, col);
	}
	/**
	 * Missing Description
	 * @param row
	 * @param col
	 * @return
	 */
	public String index(int row, int col) {
		return row + "/" + col;
	}
	/**
	 * Missing Description.this function has already implemented in latest Java.
	 * @param row
	 * @param col
	 * @return
	 */
	public List<Place> getMap(int row, int col) {
		
		List<Place> p = placeMap.get(index(row, col));
		
		if(p == null) {
			p = new ArrayList<Place>();
		}
		return p;
	}
	/**
	 * Missing Description
	 * @param row
	 * @param col
	 * @return
	 */
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
