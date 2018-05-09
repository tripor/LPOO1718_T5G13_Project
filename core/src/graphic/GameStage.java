package graphic;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import logic.console.Console;
import logic.map.Map;
import logic.storage.ConveyorList;
import logic.storage.PersonList;
import logic.storage.PlaceList;
import person.type.Worker;
import place.Place;
import place.type.Factory;

public class GameStage extends Stage {
	
	/**
	 * The viewport width
	 */
    public static final int VIEWPORT_WIDTH = 40;
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
     * Missing Description
     */
	private AsyncExecutor asyncExecutor = new AsyncExecutor(10);
	/**
	 * Constructor of the class GameStage
	 * It creates a viewport and loads all the texture of the game
	 * @param game 
	 */
	public GameStage(GroundUpGame game) {
	    this.game=game;
	    this.map= new Map(this);
		this.place_list=new PlaceList();
		this.conveyor_list=new ConveyorList();
		this.person_list= new PersonList();
		
		// Set the viewport
		setViewport(new ScreenViewport());
	
	    // Load the textures
	    game.getAssetManager().load("factory.png", Texture.class);
	    game.getAssetManager().load("worker.png", Texture.class);
	    game.getAssetManager().finishLoading(); // should be replaced by something more efficiente
	    
	    
	    
	    //Initialize the game
	    initGame();
	}
    
	

	/**
	 * Initializes the games creating all the places and people
	 */
    private void initGame() {
		map.setMapWidth(300);
		map.setMapHeight(300);

		//	Factory p2 = new Factory(170,484,69,75,3,43);
		//	Console.log("ADDING: " + p2.toString());
		//	if(map.addPlace(p2)) {
		//		this.addActor(p2);
		//		this.addLabel("1", p2.getBoundLeft(), p2.getBoundTop());
		//	}
		//	Factory p = new Factory(204,477,86,39,1,57);
		//	Console.log("ADDING: " + p.toString());
		//	if(map.addPlace(p)) {
		//		this.addActor(p);
		//		this.addLabel("2", p.getBoundLeft(), p.getBoundTop());
		//	}
		//	Console.log("Size=" + PlaceList.getInstance().getPlaceList().size());

		//	Factory p = new Factory(0,0,640,480,1,50);
		//	map.addPlace(p);
		//	this.addActor(p);

		//	int i = 0;
		//	while (i < 150) {
		//		i = generateFactory(i);	// generateFactory will return i++.
		//	}

		/*asyncExecutor.submit(new AsyncTask<Void>() {
	        public Void call() {
	        		int	i = 0;
	        		
	        		for(i = 0; i < 30; i++) {
	        			generateFactory(i);
	        		}
	        		for(i = 0; i < 30; i++) {
	        			generatePerson(i);
	        		}
	            return null;
	        } 
	    });*/
    }
    
	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}

	private Place generateFactory() {
		Factory p = null;

		int min_size = map.getbuildingMinSize();
		int max_size = map.getbuildingMaxSize();

		// TODO: Move camera instead of modifying buildings' position.
		int row = (map.randRow() - this.map.getMapWidth()  / 2),
		    col = (map.randCol() - this.map.getMapHeight() / 2),
		    
		    w = Random(min_size, max_size),
		    h = Random(min_size, max_size),
		    side = Random(1,4),	// 1=Top, 2=Right, 3=Bottom, 4=Left
		    door_px = 0;
		
		if(side == 1 || side == 3) {
			door_px = Random(0, w);
		}
		else {
			door_px = Random(0, h);
		}
		
		// if(row+h < map.getMapHeight() && col+w < map.getMapWidth()) {
			
			p = new Factory(this,row, col, w, h, Random(1,4), door_px);
			boolean success = this.place_list.addPlace(p);
			
			if(success) {
				this.addActor(p);
				// this.addLabel(p.getUniqueId(), col, row);

				Console.log(p.toString());
				return p;
			}
		// }
		return null;
	}
	
	



	private boolean generateFactory(int i) {
		
		Place p = generateFactory();
		
		if(p == null) {
			return false;
		}
		return true;
	}
	
	// for testing
	//private void addLabel(String str, int col, int row) {
	//    Label scoreLabel = new Label(str, new Label.LabelStyle(new BitmapFont(), null));
	//    scoreLabel.setColor(Color.CYAN);
	//    scoreLabel.setPosition(col, row);
	//    this.addActor(scoreLabel);
	//}
	
	private void generatePerson(int i) {

		int s_row = map.randRow(),
			s_col = map.randCol(),
			t_row = map.randRow(),
			t_col = map.randCol();

		// For Starting Point
		for(Place pl : this.place_list.getPlaceList(s_row, s_col)) {
			if(pl.including(s_row, s_col)) {
				// Move the person to the door.
				s_row = pl.getDoorRow();
				s_col = pl.getDoorCol();
				break;
			}
		}
		
		// For Destination
		for(Place pl : this.place_list.getPlaceList(t_row, t_col)) {
			if(pl.including(t_row, t_col)) {
				// Move the person to the door.
				t_row = pl.getDoorRow();
				t_col = pl.getDoorCol();
				break;
			}
		}
		
		Worker p = new Worker(this,s_row, s_col);
		p.setId("" + i);
		
		if(PersonList.getInstance().addPerson(p)) {

			this.addActor(p);
			Console.log(p.toString());
			//	Console.log(p.getId());
			
			// List<Node> path = 
			p.getPath(t_row, t_col);
		}
	}
	
	/**
	 * 
	 * @return Returns a class containing all the places in the game
	 */
    public PlaceList getPlace_list() {
		return place_list;
	}
    /**
     * Sets the class containing all the places in the game
     * @param place_list The class PlaceList I want to change to
     */
	public void setPlace_list(PlaceList place_list) {
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
	public ConveyorList getConveyor_list() {
		return conveyor_list;
	}
	
}
