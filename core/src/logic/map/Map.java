package logic.map;

import java.util.List;
import java.util.Random;

import graphic.GameStage;
import place.Place;
import logic.console.Console;
import logic.storage.ConveyorList;
import logic.storage.PlaceList;

public class Map {

	/**
	 * Amount of pixel the map is divided. Squares 10 by 10
	 */
	public static int division = 10;
	/**
	 * Missing description
	 */
	public int mapWidth = 0;
	/**
	 * Missing description
	 */
	public int mapHeight = 0;
	/**
	 * Missing description
	 */
	private int buildingMinSize = 20;
	/**
	 * Missing description
	 */
	private int buildingMaxSize = 100;
	/**
	 * The Game this Map is in
	 */
	private GameStage game;
	
	private String testingTrack = "";

	/**
	 * Constructor for the class Map
	 */
	public Map(GameStage game) {
		this.game=game;
		
		this.mapHeight=1000;
		this.mapWidth=1000;
	}
	/**
	 * Missing description
	 * @param mapWidth
	 */
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}
	/**
	 * Missing description
	 * @param mapHeight
	 */
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}
	/**
	 * Missing description
	 * @return
	 */
	public int getMapWidth() {
		return mapWidth;
	}
	/**
	 * Missing description
	 * @return
	 */
	public int getMapHeight() {
		return mapHeight;
	}
	/**
	 * Missing description
	 * @return
	 */
	public int getbuildingMinSize() {
		return buildingMinSize;
	}
	/**
	 * Missing description
	 * @return
	 */
	public int getbuildingMaxSize() {
		return buildingMaxSize;
	}
	/**
	 * Missing description
	 * @param size
	 */
	public void setbuildingMinSize(int size) {
		buildingMinSize = size;
	}
	/**
	 * Missing description
	 * @param size
	 */
	public void setbuildingMaxSize(int size) {
		buildingMaxSize = size;
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
