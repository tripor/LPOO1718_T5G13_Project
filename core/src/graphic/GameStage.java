package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.groundup.game.GroundUpGame;

import graphic.control.Mouse;
import graphic.entities.Background;
import graphic.entities.MaterialG;
import icon.BuildHolder;
import icon.Icon;
import icon.MenuHolder;
import icon.MenuLoadHolder;
import icon.MenuSaveHolder;
import icon.NormalHolder;
import icon.type.BarraIcon;
import logic.Map;
import logic.SaveState;
import logic.entities.MaterialL;

public class GameStage extends Stage {
	
	/**
	 * The viewport width
	 */
    public int VIEWPORT_WIDTH = 256;
    /**
     * The viewport height
     */
    public int VIEWPORT_HEIGHT=192;
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
		this.background_list=new BackgroundList();
		this.background_list.setZ(0);
		this.addActor(this.background_list);
	    this.map= new Map(1000,1000);
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
			
		
		
		// Set the viewport
		camera= new OrthographicCamera();
		setViewport(new FitViewport(this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT,camera));
	
	    // Load the textures
	    game.getAssetManager().load("factory.png", Texture.class);
	    game.getAssetManager().load("menu_icon.png", Texture.class);
	    game.getAssetManager().load("conveyor_icon.png", Texture.class);
	    game.getAssetManager().load("inserter_icon.png", Texture.class);
	    
	    
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
	    game.getAssetManager().load("iron_plate.png", Texture.class);
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
	    
	    game.getAssetManager().finishLoading(); // should be replaced by something more efficiente
	    
	    initializeIcons();
	    initializeMap();
	    
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
	 * Checks if a given pixel is on the camera
	 * @param top The x/top position in pixels
	 * @param left The y/left Position in pixels
	 * @return True is it is or false otherwise
	 */
	public boolean pixelOnScreen(int top,int left)
	{
		Vector3 position=this.camera.position;
		float x=position.x;
		float y=position.y;
		if(x<=top && top<=x+this.camera.viewportWidth)
		{
			if(y<=left && left <=y+this.camera.viewportHeight)
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
		for(MaterialL it:this.map.lista_material)
		{
			if(it.id==0)
			{
				it.id=1;
				MaterialG novo=new MaterialG(this,it);
				this.material_list.addMaterial(novo);
			}
		}
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
		this.map=instancia.loadGame(nome);
		this.map.recreateMap();
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
