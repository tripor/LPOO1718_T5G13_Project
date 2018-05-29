package graphic;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.groundup.game.GroundUpGame;
import com.groundup.game.GroundUpScreen;

import graphic.control.Mouse;
import graphic.entities.BackgroundG;
import graphic.entities.MaterialG;
import icon.BuildHolder;
import icon.MenuHolder;
import icon.MenuLoadHolder;
import icon.MenuSaveHolder;
import icon.NormalHolder;
import icon.type.LoadingIcon;
import logic.Map;
import logic.SaveState;
import logic.entities.MaterialL;

public class GameStage extends Stage {
	
	/**
	 * The viewport width
	 */
    public int VIEWPORT_WIDTH = 1920;
    /**
     * The viewport height
     */
    public int VIEWPORT_HEIGHT=1080;
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
    protected BackgroundList background_list;
    /**
     * Class with all the buttons in this game
     */
    protected IconList icon_list;
    /**
     * Class with all the inserters in the game
     */
    protected InserterList inserter_list;
    /**
     * Class with all the materials of the game
     */
    protected MaterialList material_list;
    /**
     * Game mouse
     */
    protected Mouse mouse;
    /**
     * Game camera
     */
    private OrthographicCamera camera;
    /**
     * If the menu is openned
     */
    public boolean menuOpen=false;
    /**
     * Loading screen
     */
    private LoadingIcon build_icon;
    
    public ArrayList<MaterialG> unused_material;

	/**
     * For testing. For adding 100000 people into the map.
     */
//	private AsyncExecutor asyncExecutor = new AsyncExecutor(10);
    
	/**
	 * Constructor of the class GameStage
	 * It creates a viewport and loads all the texture of the game
	 * @param game 
	 * @param screen 
	 */
	public GameStage(GroundUpGame game, GroundUpScreen screen) {
	    this.game=game;
	    // I create a group of actor and I add it to the stage/GameStage
		this.background_list=new BackgroundList();
		this.background_list.setZ(0);
		this.addActor(this.background_list);
	    this.map= new Map(1000);
		this.place_list=new PlaceList(this);
		this.place_list.setZ(1);
		this.addActor(place_list);
		this.conveyor_list=new ConveyorList(this);
		this.conveyor_list.setZ(1);
		this.addActor(conveyor_list);
		this.material_list=new MaterialList(this);
		this.material_list.setZ(2);
		this.addActor(material_list);
		this.inserter_list= new InserterList(this);
		this.inserter_list.setZ(3);
		this.addActor(inserter_list);
		this.person_list= new PersonList(this);
		this.person_list.setZ(4);
		this.addActor(person_list);
		this.mouse= new Mouse(this);
		this.mouse.setZ(6);
		this.addActor(mouse);
		this.icon_list= new IconList();
		this.icon_list.setZ(5);
		this.addActor(icon_list);
		this.getActors().sort();
		this.unused_material= new ArrayList<MaterialG>();
			
		
		
		// Set the viewport
		camera= new OrthographicCamera();
		setViewport(new FitViewport(this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT,camera));
		

	    game.getAssetManager().load("loading1.png", Texture.class);
	    game.getAssetManager().load("loading2.png", Texture.class);
	    game.getAssetManager().load("loading3.png", Texture.class);
	    game.getAssetManager().load("loading4.png", Texture.class);
	    game.getAssetManager().load("loading5.png", Texture.class);
	    game.getAssetManager().load("loading6.png", Texture.class);
	    game.getAssetManager().finishLoading();
	    
	    game.getAssetManager().load("factory.png", Texture.class);
	    game.getAssetManager().load("house.png", Texture.class);
	    game.getAssetManager().load("menu_icon.png", Texture.class);
	    game.getAssetManager().load("conveyor_icon.png", Texture.class);
	    game.getAssetManager().load("inserter_icon.png", Texture.class);
	    game.getAssetManager().load("grass.png", Texture.class);
	    game.getAssetManager().load("land_iron.png", Texture.class);
	    game.getAssetManager().load("land_copper.png", Texture.class);
	    game.getAssetManager().load("land.png", Texture.class);
	    game.getAssetManager().load("iron_ore.png", Texture.class);
	    game.getAssetManager().load("copper_ore.png", Texture.class);
	    game.getAssetManager().load("iron_plate.png", Texture.class);
	    game.getAssetManager().load("copper_plate.png", Texture.class);
	    
	    
	    game.getAssetManager().load("build_icon.png", Texture.class);
	    game.getAssetManager().load("build_icon_mouse.png", Texture.class);
	    game.getAssetManager().load("remove_icon.png", Texture.class);
	    game.getAssetManager().load("remove_icon_mouse.png", Texture.class);
	    game.getAssetManager().load("remove_icon_pressed.png", Texture.class);
	    game.getAssetManager().load("menu_icon.png", Texture.class);
	    game.getAssetManager().load("menu_icon_mouse.png", Texture.class);
	    game.getAssetManager().load("cancel_icon.png", Texture.class);
	    game.getAssetManager().load("cancel_icon_mouse.png", Texture.class);
	    

	    game.getAssetManager().load("menu_resume.png", Texture.class);
	    game.getAssetManager().load("menu_resume_mouse.png", Texture.class);
	    game.getAssetManager().load("menu_save.png", Texture.class);
	    game.getAssetManager().load("menu_save_mouse.png", Texture.class);
	    game.getAssetManager().load("menu_exit.png", Texture.class);
	    game.getAssetManager().load("menu_exit_mouse.png", Texture.class);
	    game.getAssetManager().load("menu_load.png", Texture.class);
	    game.getAssetManager().load("menu_load_mouse.png", Texture.class);
	    game.getAssetManager().load("menu_background.png", Texture.class);
	    game.getAssetManager().load("menu_cancel.png", Texture.class);
	    game.getAssetManager().load("menu_cancel_mouse.png", Texture.class);
	    
	    game.getAssetManager().load("worker.png", Texture.class);
	    game.getAssetManager().load("grass01.png", Texture.class);
	    game.getAssetManager().load("nothing.png", Texture.class);
	    game.getAssetManager().load("conveyor1.png", Texture.class);
	    game.getAssetManager().load("conveyor2.png", Texture.class);
	    game.getAssetManager().load("conveyor3.png", Texture.class);
	    game.getAssetManager().load("conveyor4.png", Texture.class);
	    game.getAssetManager().load("conveyor5.png", Texture.class);
	    game.getAssetManager().load("conveyor6.png", Texture.class);
	    game.getAssetManager().load("conveyor7.png", Texture.class);
	    game.getAssetManager().load("conveyor8.png", Texture.class);
	    game.getAssetManager().load("conveyor9.png", Texture.class);
	    game.getAssetManager().load("conveyor10.png", Texture.class);
	    game.getAssetManager().load("conveyor11.png", Texture.class);
	    game.getAssetManager().load("conveyor12.png", Texture.class);
	    game.getAssetManager().load("iron_mine.png", Texture.class);
	    game.getAssetManager().load("inserter_base.png", Texture.class);
	    game.getAssetManager().load("inserter_hand.png", Texture.class);
	    game.getAssetManager().load("inserter_direction.png", Texture.class);
	    game.getAssetManager().load("barra1.png", Texture.class);
	    game.getAssetManager().load("barra2.png", Texture.class);
	    game.getAssetManager().load("barra3.png", Texture.class);
	    game.getAssetManager().load("barra4.png", Texture.class);
	    game.getAssetManager().load("barra5.png", Texture.class);
	    game.getAssetManager().load("barra6.png", Texture.class);
	    game.getAssetManager().load("barra7.png", Texture.class);
	    
	    build_icon= new LoadingIcon(this,0,0,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT);
		this.addActor(build_icon);
	
	    
	    
	}	
	/**
	 * Check if all the assets are loaded
	 */
	public void loadGame()
	{
	    if(game.getAssetManager().update())
	    {
		    build_icon.remove();
		    initializeIcons();
		    initializeMap();
	    }
	}
	/**
	 * Initializes all buttons of the game
	 */
	private void initializeIcons()
	{
		NormalHolder build_icon= new NormalHolder(this,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT/3);
		this.icon_list.addIcon(build_icon);
		
		BuildHolder icon2= new BuildHolder(this,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT/3);
		this.icon_list.addIcon(icon2);
		
		MenuHolder icon3= new MenuHolder(this,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT);
		this.icon_list.addIcon(icon3);
		
		MenuSaveHolder icon4= new MenuSaveHolder(this,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT);
		this.icon_list.addIcon(icon4);
		
		MenuLoadHolder icon5= new MenuLoadHolder(this,this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT);
		this.icon_list.addIcon(icon5);
		
		this.icon_list.setHolder(1);
	}
    
	/**
	 * Initializes the map as for the background
	 */
	private void initializeMap()
	{
		for(int i=0; i< this.map.getMapWidth()*this.VIEWPORT_WIDTH/256;i+=Map.division*this.VIEWPORT_WIDTH/256)
		{
			for(int j=0; j < this.map.getMapHeight()*this.VIEWPORT_WIDTH/256;j+=Map.division*this.VIEWPORT_WIDTH/256)
			{
				BackgroundG novo = new BackgroundG(this, i, j,
						this.map.lista_background.get(this.map.transformToBlock(i*256/this.VIEWPORT_WIDTH)).get(this.map.transformToBlock(j*256/this.VIEWPORT_WIDTH)));
				this.background_list.addBackground(novo);
			}
		}
		for(int i=0;i<400;i++)
		{
			MaterialG novo= new MaterialG(this);
			this.unused_material.add(novo);
		}
	}
	/**
	 * Checks if a given pixel is on the camera
	 * @param top The x/top position in pixels
	 * @param left The y/left Position in pixels
	 * @return True is it is or false otherwise
	 */
	public boolean pixelOnScreen(float top,float left)
	{
		Vector3 position=this.camera.position;
		int camera_width=this.VIEWPORT_WIDTH;
		int camera_height=this.VIEWPORT_HEIGHT;
		float x=position.x-camera_width/2;
		float y=position.y-camera_height/2;
		if(x<=top && top<=x+camera_width)
		{
			if(y<=left && left <=y+camera_height)
			{
				return true;
			}
		}
		return false;
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
	public Map map() {
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
	/**
	 * 
	 * @return Returns the Inserter list of the game
	 */
	public InserterList inserters()
	{
		return this.inserter_list;
	}
	/**
	 * 
	 * @return Returns the Materials visible of the game
	 */
	public MaterialList materials()
	{
		return this.material_list;
	}
	/**
	 * Updates sprites of all the materials in the map
	 */
	public void updateMaterials()
	{
		for(MaterialL it:this.map.lista_material_toActor)
		{
			if(this.unused_material.isEmpty())
			{
				MaterialG novo= new MaterialG(this,it);
				this.material_list.addMaterial(novo);
			}
			else
			{
				MaterialG adicionar=this.unused_material.get(0);
				this.unused_material.remove(0);
				adicionar.setInstance(it);
				this.material_list.addMaterial(adicionar);
			}
		}
		this.map.lista_material_toActor.clear();
	}
	/**
	 * Saves the game
	 */
	public void saveGame(String nome)
	{
		System.out.println(nome);
		SaveState instancia=new SaveState();
		instancia.saveGame(nome, map);
	}
	/**
	 * Load the game
	 */
	public void loadGame(String nome)
	{
		SaveState instancia=new SaveState();
		Map subs=instancia.loadGame(nome);
		if(subs!=null)
		{
			this.map=subs;
			this.map.recreateMap();
			this.map.money+=this.map.money_wasted;
			this.material_list.clear();
			this.inserter_list.clear();
			this.conveyor_list.clear();
			this.place_list.clear();
			this.person_list.clear();
			this.inserter_list.loadFromMap();
			this.conveyor_list.loadFromMap();
			this.place_list.loadFromMap();
			this.person_list.loadFromMap();
		}
	}
	
	
	
	
	
}
