package logic.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import graphic.Background;
import com.groundup.game.GameStage;
import place.Place;
import logic.console.Console;
import logic.storage.ConveyorList;
import logic.storage.PlaceList;

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
	
	private ArrayList<ArrayList<ArrayList<Object>>> map;
	
	private String testingTrack = "";

	/**
	 * Constructor for the class Map
	 */
	public Map(GameStage game) {
		this.game=game;
		
		this.mapHeight=1000;
		this.mapWidth=1000;
		map = new ArrayList<ArrayList<ArrayList<Object>>>();
		for(int i=0 ; i<this.mapWidth/Map.division;i++)
		{
			map.add(new ArrayList<ArrayList<Object>>());
			for(int j=0; j<this.mapHeight/Map.division;j++)
			{
				map.get(i).add(new ArrayList<Object>());
			}
		}
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
	 * Adds a object to the map
	 * @param obj the object i want to add
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @param width the width in pixels
	 * @param height the height in pixels
	 */
	public void addMap(Object obj, int pos_x, int pos_y, int width, int height) {
		int x = pos_x / Map.division;
		int y = pos_y / Map.division;

		int error_w = 0;
		int error_h = 0;
		if (width % Map.division > 0)
			error_w = 1;
		if (height % Map.division > 0)
			error_h = 1;

		int quantity_x = width / Map.division + error_w;
		int quantity_y = height / Map.division + error_h;

		for (int i = x; i <= x + quantity_x * Map.division; i ++) {
			for (int j = y; j <= y + quantity_y * Map.division; j ++) {
				
				this.map.get(i).get(j).add(obj);
				
			}
		}
	}
	/**
	 * Adds a object to the map
	 * @param obj the object i want to add
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @param width the width in pixels
	 * @param height the height in pixels
	 */
	public void removeMap(Object obj, int pos_x, int pos_y, int width, int height) 
	{
		int x = pos_x / Map.division;
		int y = pos_y / Map.division;

		int error_w = 0;
		int error_h = 0;
		if (width % Map.division > 0)
			error_w = 1;
		if (height % Map.division > 0)
			error_h = 1;

		int quantity_x = width / Map.division + error_w;
		int quantity_y = height / Map.division + error_h;

		for (int i = x; i <= x + quantity_x * Map.division; i ++) {
			for (int j = y; j <= y + quantity_y * Map.division; j ++) {
				
				this.map.get(i).get(j).remove(obj);
				
			}
		}
	}
	/**
	 * Gets what in a position block of the map
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @return Return an arraylist of the objects in that position
	 */
	public ArrayList<Object> getMap(int pos_x,int pos_y)
	{
		return this.map.get(pos_x/Map.division).get(pos_y/Map.division);
	}
	
	
	/**
	 * Missing description
	 * @return
	 */
	public int randRow() {
		return (new Random()).nextInt(this.getMapHeight());
	}
	/**
	 * Missing description
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
