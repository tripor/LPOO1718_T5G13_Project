package graphic;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncResult;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import logic.console.Console;
import logic.map.Map;
import logic.storage.PersonList;
import person.Person;
import place.Place;
import place.type.Factory;

public class GameStage extends Stage {
	
    public static final int VIEWPORT_WIDTH = 40;
    public static final float PIXEL_TO_METER = 1f / 500;
	
    public static GameStage instance = new GameStage();

    public GroundUpGame game = GroundUpGame.getInstance();
    public          Map map  = Map.getInstance();

	private AsyncExecutor asyncExecutor = new AsyncExecutor(10);
	
	private GameStage() {
		
		// float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
	    
		// Set the viewport
		setViewport(new ScreenViewport());
	
	    // Load the textures
	    game.getAssetManager().load("factory.png", Texture.class);
	    game.getAssetManager().load("worker.png", Texture.class);
	    game.getAssetManager().finishLoading();
	
	    // this.setActionsRequestRendering(false);
	    initGame();
	}
	
	public static GameStage getInstance() {
		return instance;
	}
    
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

		asyncExecutor.submit(new AsyncTask<Void>() {
	        public Void call() {
	        		int	i = 0;
	        		
//	        		for(i = 0; i < 30; i++) {
//	        			generateFactory(i);
//	        		}
	        		for(i = 0; i < 30; i++) {
	        			generatePerson(i);
	        		}
	            return null;
	        } 
	    });
    }
    
	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}

	private Place generateFactory() {
		Factory p = null;

		int min_size = map.getbuildingMinSize();
		int max_size = map.getbuildingMaxSize();

		// TODO: Move camera instead of modifying buildings' position.
		int row = (map.randRow() - Map.getInstance().getMapWidth()  / 2),
		    col = (map.randCol() - Map.getInstance().getMapHeight() / 2),
		    
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
			
			p = new Factory(row, col, w, h, Random(1,4), door_px);
			boolean success = map.addPlace(p);
			
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
		for(Place pl : map.getPlaceList(s_row, s_col)) {
			if(pl.including(s_row, s_col)) {
				// Move the person to the door.
				s_row = pl.getDoorRow();
				s_col = pl.getDoorCol();
				break;
			}
		}
		
		// For Destination
		for(Place pl : map.getPlaceList(t_row, t_col)) {
			if(pl.including(t_row, t_col)) {
				// Move the person to the door.
				t_row = pl.getDoorRow();
				t_col = pl.getDoorCol();
				break;
			}
		}
		
		Person p = new Person("" + i);
		p.setCurrentRow(s_row);
		p.setCurrentCol(s_col);
		
		if(PersonList.getInstance().addPerson(p)) {

			this.addActor(p);
			Console.log(p.toString());
			//	Console.log(p.getId());
			
			// List<Node> path = 
			p.getPath(t_row, t_col);
		}
	}
}
