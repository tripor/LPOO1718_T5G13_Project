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
	public static int division = 10;
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
	/**�
	 * @return the maximun size of a building in pixels
	 */
	public int getbuildingMaxSize() {
		return buildingMaxSize;
	}
	/**
	
	
	
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
