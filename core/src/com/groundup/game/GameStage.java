package com.groundup.game;

import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import graphic.Background;
import graphic.GroundUpGame;
import graphic.control.Mouse;
import icon.Icon;
import icon.type.BuildIcon;
import icon.type.FactoryIcon;
import logic.console.Console;
import logic.map.Map;
import logic.storage.BackGroundList;
import logic.storage.ConveyorList;
import logic.storage.IconList;
import logic.storage.PersonList;
import logic.storage.PlaceList;
import person.type.Worker;
import place.Place;
import place.type.Factory;

public class GameStage extends Stage {
	
	/**
	 * The viewport width
	 */
    public static final int VIEWPORT_WIDTH = 200;
    /**
     * The viewport height
     */
    public static final int VIEWPORT_HEIGHT=100;
    /**
     * Conversion from pixel to meter
     */
    public static final float PIXEL_TO_METER = 1f / 500;
    /**
     * Instance of the ground up game
     */
    public GroundUpGame game;
    /**
     * Instance of the game map
     */
    protected Map map;
    /**
     * Class with all the places in the game
     */
    protected PlaceList place_list;
    /**
     * Class with all the conveyors in the game
     */
    protected ConveyorList conveyor_list;
    /**
     * Class with all the persons in this game
     */
    protected PersonList person_list;
    /**
     * Class with all the background in this game
     */
    protected BackGroundList background_list;
    /**
     * Class with all the buttons in this game
     */
    protected IconList icon_list;
    /**
     * Game mouse
     */
    protected Mouse mouse;
    /**
     * Game camera
     */
    private OrthographicCamera camera;

	/**
     * For testing. For adding 100000 people into the map.
     */
//	private AsyncExecutor asyncExecutor = new AsyncExecutor(10);
    
	/**
	 * Constructor of the class GameStage
	 * It creates a viewport and loads all the texture of the game
	 * @param game 
	 */
	public GameStage(GroundUpGame game) {
	    this.game=game;
	    // I create a group of actor and I add it to the stage/GameStage
		this.background_list=new BackGroundList();
		this.addActor(this.background_list);
	    this.map= new Map(this);
		this.place_list=new PlaceList();
		this.addActor(place_list);
		this.conveyor_list=new ConveyorList();
		this.addActor(conveyor_list);
		this.person_list= new PersonList();
		this.addActor(person_list);
		this.mouse= new Mouse(this);
		this.addActor(mouse);
		this.icon_list= new IconList();
		this.addActor(icon_list);
		
		// Set the viewport
		camera= new OrthographicCamera();
		setViewport(new FitViewport(GameStage.VIEWPORT_WIDTH,GameStage.VIEWPORT_HEIGHT,camera));
	
	    // Load the textures
	    game.getAssetManager().load("factory.png", Texture.class);
	    game.getAssetManager().load("factory_icon.png", Texture.class);
	    game.getAssetManager().load("menu_icon.png", Texture.class);
	    game.getAssetManager().load("build_icon.png", Texture.class);
	    game.getAssetManager().load("mine_icon.png", Texture.class);
	    game.getAssetManager().load("conveyor_icon.png", Texture.class);
	    game.getAssetManager().load("worker.png", Texture.class);
	    game.getAssetManager().load("grass01.png", Texture.class);
	    game.getAssetManager().load("nothing.png", Texture.class);
	    game.getAssetManager().load("conveyor1.png", Texture.class);
	    
	    game.getAssetManager().finishLoading(); // should be replaced by something more efficiente
	    
	    initializeIcons();
	    initializeMap();
	    
	    //Initialize the game
	    //initGame();

	}
	/**
	 * Initializes all buttons of the game
	 */
	private void initializeIcons()
	{
		Icon build_icon= new BuildIcon(this,7,7,7,7);
		this.icon_list.addIcon(build_icon);
	}
    
	/**
	 * Initializes the map as for the background
	 */
	private void initializeMap()
	{
		for(int i=0; i< this.map.getMapWidth();i+=Map.division)
		{
			for(int j=0; j < this.map.getMapHeight();j+=Map.division)
			{
				Background adicionar= new Background(this,i,j,Map.division,Map.division);
				this.background_list.addBackground(adicionar);
			}
		}
	}
	

	/**
	 * Initializes the games creating all the places and people
	 */
    private void initGame() {

			int i = 0;
			while (i < 1500) {
				generateFactory();	 // generateFactory will return i++.
				i++;
			}
			
			for (i=0; i<1000; i++) {
				generatePerson(i);
			}

			//	asyncExecutor.submit(new AsyncTask<Void>() {
			//        public Void call() {
			//        		int	i = 0;
			//        		
			//        		for(i = 0; i < 1500; i++) {
			//        			generateFactory();
			//        		}
			//        		for(i = 0; i < 1000; i++) {
			//        			generatePerson(i);
			//        		}
			//            return null;
			//        } 
			//    });
    }
    
	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}

	private void generateFactory() {
		
		//	Factory p = null;
		//
		//	int min_size = map.getbuildingMinSize();
		//	int max_size = map.getbuildingMaxSize();
		//
		//	// TODO: Move camera instead of moving buildings to negative quadrant.
		//	int row = (map.randRow() - this.map.getMapWidth()  / 2),
		//	    col = (map.randCol() - this.map.getMapHeight() / 2),
		//	    
		//	    w = Random(min_size, max_size),
		//	    h = Random(min_size, max_size),
		//	    side = Random(1,4),	// 1=Top, 2=Right, 3=Bottom, 4=Left
		//	    door_px = 0;
		//	
		//	if(side == 1 || side == 3) {
		//		door_px = Random(0, w);
		//	}
		//	else {
		//		door_px = Random(0, h);
		//	}
		
		// if(row+h < map.getMapHeight() && col+w < map.getMapWidth()) {
			
			Factory p = new Factory(this, this.Random(1, 1000), this.Random(1, 1000), this.Random(5, 30), this.Random(5, 30), Random(1,4), 2);
			boolean success = this.place_list.addPlace(p);
			
			if(success) {
				// this.addLabel(p.getUniqueId(), col, row);

				// Console.log(p.toString());
				// return p;
			}
		// }
		//return null;
	}
	
	



//	private boolean generateFactory(int i) {
//		
//		Place p = generateFactory();
//		
//		if(p == null) {
//			return false;
//		}
//		return true;
//	}
	
	// for testing
	//private void addLabel(String str, int col, int row) {
	//    Label scoreLabel = new Label(str, new Label.LabelStyle(new BitmapFont(), null));
	//    scoreLabel.setColor(Color.CYAN);
	//    scoreLabel.setPosition(col, row);
	//    this.addActor(scoreLabel);
	//}
	
	public void generatePerson(int i) {

		int s_row = map.randRow(),
			s_col = map.randCol(),
			t_row = map.randRow(),
			t_col = map.randCol();
		
		// Console.log("\nPerson " + i + " (" + s_row + "," + s_col + ") T (" + t_row + "," + t_col + ")");

		/**
		 *  TODO:
		 *  Can be replaced by "this.place_list.checkIfPointInBuilding(s_row, s_col)"
		 */
		// For Starting Point
		for(Place pl : this.place_list.getPlaceList(s_row, s_col)) {
			if(pl.including(s_row, s_col)) {
				// Move the person to the door.
				s_row = pl.getDoorRow();
				s_col = pl.getDoorCol();
				break;
			}
		}
		
		Worker p = new Worker(this, s_row, s_col);
		p.setId("" + i);
		
		if(this.person_list.addPerson(p)) {
			// Console.log(p.toString());
			//	Console.log(p.getId());
			
			// List<Node> path = 
			p.getPath(t_row, t_col);
		}
	}
	
	/**
	 * 
	 * @return Returns a class containing all the places in the game
	 */
    public PlaceList places() {
		return place_list;
	}
    /**
     * Sets the class containing all the places in the game
     * @param place_list The class PlaceList I want to change to
     */
	public void setPlaceList(PlaceList place_list) {
		this.place_list = place_list;
	}
	/**
	 * 
	 * @return Return the Map of this game
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * 
	 * @return Return the Class GroundUpGame of this game
	 */
	public GroundUpGame getGame() {
		return game;
	}
	/**
	 * 
	 * @return Returns the Conveyor List of this game
	 */
	public ConveyorList conveyors() {
		return conveyor_list;
	}
	/**
	 * 
	 * @return Returns the mouse of the game
	 */
	public Mouse getMouse() {
		return mouse;
	}
	/**
	 * 
	 * @return Returns the Icon list of the game
	 */
	public IconList icons() {
		return icon_list;
	}
	/**
	 * 
	 * @return Returns the Person list of the game
	 */
	public PersonList people() {
		return person_list;
	}
	
	
	
}
