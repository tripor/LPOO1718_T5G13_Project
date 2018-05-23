package logic.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;

import graphic.ActorExtension;
import logic.console.Console;
import material.Material;
import person.Person;
import place.Place;

public class Map {

	/**
	 * Amount of pixel the map is divided. Squares 10 by 10
	 */
	public final static int division = 10;
	/**
	 * Missing description
	 */
	private int mapWidth = 0;
	/**
	 * Missing description
	 */
	private int mapHeight = 0;
	/**
	 * Missing description
	 */
	private final int buildingMinSize = 20;
	/**
	 * Missing description
	 */
	private final int buildingMaxSize = 100;
	/**
	 * Maximum number of object that can be in one block at a time
	 */
	private final int maxOjectoInBlock =5;
	/**
	 * The Game this Map is in
	 */
	private GameStage game;
	
	private ArrayList<ArrayList<ArrayList<Actor>>> map;
	
	private String testingTrack = "";

	/**
	 * Constructor for the class Map
	 */
	public Map(GameStage game) {
		this.game=game;
		
		this.mapHeight=1000;
		this.mapWidth=1000;
		map = new ArrayList<ArrayList<ArrayList<Actor>>>();
		for(int i=0 ; i<Map.getBlockIndex(this.mapWidth);i++)
		{
			map.add(new ArrayList<ArrayList<Actor>>());
			for(int j=0; j<Map.getBlockIndex(this.mapHeight);j++)
			{
				map.get(i).add(new ArrayList<Actor>());
			}
		}
	}

	public static int getBlockIndex(int num) {
		return num / Map.division;
	}
	public static int getBlockIndex(float num) {
		return Map.getBlockIndex((int) num);
	}
	
	

	private static boolean overlapWith(Actor obj, Actor castedEl) {

		boolean colOverlap = obj.getX() <= castedEl.getRight()
						&& obj.getRight() >= castedEl.getX();
		
		boolean rowOverlap = obj.getTop() <= castedEl.getY()
						&& obj.getY() >= castedEl.getTop();

		return (colOverlap && rowOverlap);
	}
	
	/**
	 * Check Overlap
	 * @param obj The object to be added.
	 * @param game
	 * @return
	 */
	public boolean tryAdd(Actor obj, GameStage game) {
		
		Console.log("tryAdd();");

		int left   = Map.getBlockIndex(obj.getX()),
			right  = Map.getBlockIndex(obj.getRight()),
			top    = Map.getBlockIndex(obj.getY()),
			bottom = Map.getBlockIndex(obj.getTop());

		Console.log(
				"-> L" + obj.getX() + "=K" + left +
				" / R" + obj.getRight() + "=K" + right +
				" / T" + obj.getTop() + "=K" + top +
				" / B" + obj.getY() + "=K" + bottom
			);
		
		int col, row;
		
		for(col = left; col <= right; col++) {
			for(row = top; row <= bottom; row++) {

				// get map blocks.
				ArrayList<Actor> element_list = game.map().getMap(row, col);
				
				System.out.print(" :: R" + row + " C" + col + " ListSize=" + element_list.size());

				// loop through the block.
				for(Object el : element_list) {
					
					if(Place.class.isAssignableFrom(el.getClass())) {
						// check place.

						// if found, return false (= addPlace not successful)
						if(((Place) el).overlapWith(obj.getTop(), obj.getRight(), obj.getY(), obj.getX())) {
							return false;
						}
					}
					else if(Person.class.isAssignableFrom(el.getClass())
							|| Material.class.isAssignableFrom(el.getClass())) {
						// skip person, material.
					}
					else { 
						// check other (i.e., inserter, conveyor)
						
						Actor castedEl = ((Actor) el);
						
						if(overlapWith(obj, castedEl)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * @return The map Width in pixels
	 */
	public int getMapWidth() {
		return mapWidth;
	}
	/**
	 * @return the map Height in pixels
	 */
	public int getMapHeight() {
		return mapHeight;
	}
	/**
	 * @return the minum size of a building in pixels
	 */
	public int getbuildingMinSize() {
		return buildingMinSize;
	}
	/**
	 * 
	 * @return the maximun size of a building in pixels
	 */
	public int getbuildingMaxSize() {
		return buildingMaxSize;
	}
	
	/**
	 * Adds an object to the map
	 * @param obj the object i want to add
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @param width the width in pixels
	 * @param height the height in pixels
	 */
	public void addMap(Actor obj, int pos_x, int pos_y, int width, int height) {
		int x = Map.getBlockIndex(pos_x);
		int y = Map.getBlockIndex(pos_y);
		
		int error_x=0;
		if(width%Map.division!=0) {
			error_x++;
		}
		int error_y=0;
		if(height%Map.division!=0) {
			error_y++;
		}

		int quantity_x = Map.getBlockIndex(pos_x+width)-x + error_x;
		int quantity_y = Map.getBlockIndex(pos_y+height)-y +  error_y;

		for (int i = x; i < x + quantity_x ; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				
				Console.log(" -> appending to " + i + "," + j);
				
				this.map.get(i).get(j).add(obj);
			}
		}
	}
	public void addMap(Actor obj, float x, float y, float w, float h) {
		addMap(obj, ((int) x), ((int) y), ((int) w), ((int) h));
	}
	/**
	 * Remove an object from the map
	 * @param obj the object i want to remove
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @param width the width in pixels
	 * @param height the height in pixels
	 */
	public void removeMap(Actor obj, int pos_x, int pos_y, int width, int height) 
	{
		int x = Map.getBlockIndex(pos_x);
		int y = Map.getBlockIndex(pos_y);
		
		int error_x=0;
		if(width%Map.division!=0) {
			error_x++;
		}
		int error_y=0;
		if(height%Map.division!=0) {
			error_y++;
		}

		int quantity_x = Map.getBlockIndex(pos_x+width)-x + error_x;
		int quantity_y = Map.getBlockIndex(pos_y+height)-y +  error_y;

		for (int i = x; i < x + quantity_x; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				this.map.get(i).get(j).remove(obj);
			}
		}
	}
	public void removeMap(Actor obj, float x, float y, float w, float h) {
		removeMap(obj, ((int) x), ((int) y), ((int) w), ((int) h));
	}
	/**
	 * Gets what in a position block of the map
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @return Return an arraylist of the objects in that position
	 */
	public ArrayList<Actor> getMap(int pos_x,int pos_y)
	{
		System.out.print(" -> get " + pos_x + "," + pos_y);
		return this.map.get(Map.getBlockIndex(pos_x)).get(Map.getBlockIndex(pos_y));
	}
	public ArrayList<Actor> getMap(float pos_x,float pos_y)
	{
		return getMap((int) pos_x, (int) pos_y);
	}
	/**
	 * Gets pixel in the position of the map
	 * @param pos_x the x/top position in pixels
	 * @param pos_y the y/left position in pixels
	 * @return Returns a list with all the actor on that pixel
	 */
	public ArrayList<Actor> getPixelMap(int pos_x,int pos_y)
	{
		ArrayList<Actor> elements = this.getMap(pos_x, pos_y);
		ArrayList<Actor> devolver= new ArrayList<Actor>();
		for (Actor it : elements) {
			if (it.getX() <= pos_x && pos_x <= it.getX() + it.getWidth() && it.getY() <= pos_y
					&& pos_y <= it.getY() + it.getHeight()) {
				devolver.add(it);
			}
		}
		return devolver;
	}
	public ArrayList<Actor> getPixelMap(float pos_x,float pos_y)
	{
		return getPixelMap((int) pos_x, (int) pos_y);
	}
	
	/**
	 * Generate a row-position
	 * @return
	 */
	public int randRow() {
		return (new Random()).nextInt(this.getMapHeight());
	}
	/**
	 * Generate a column-position
	 * @return
	 */
	public int randCol() {
		return (new Random()).nextInt(this.getMapWidth());
	}

	// for testing.
	public String getTestingTrack() {
		return testingTrack;
	}
	public void setTestingTrack(String testingTrack) {
		if(this.testingTrack.equals("")) {
			this.testingTrack = testingTrack;
		}
	}

}
