package graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.groundup.game.GroundUpGame;

import icon.InicialHolder;

public class MenuStage extends Stage {
	
	/**
	 * The viewport width
	 */
    public int VIEWPORT_WIDTH = 1920;
    /**
     * The viewport height
     */
    public int VIEWPORT_HEIGHT=1080;
    /**
     * Conversion from pixel to meter
     */
    public static final float PIXEL_TO_METER = 1f / 500;
    /**
     * Instance of the ground up game
     */
    public GroundUpGame game;
    /**
     * Class with all the buttons in this game
     */
    protected IconList icon_list;
    /**
     * Game camera
     */
    private OrthographicCamera camera;
    
    public static MenuStage singleton;
    
	/**
	 * Constructor of the class GameStage
	 * It creates a viewport and loads all the texture of the game
	 * @param game 
	 */
	public MenuStage(GroundUpGame game) {
	    this.game=game;
		MenuStage.singleton=this;
		this.icon_list= new IconList();
		this.icon_list.setZ(5);
		this.addActor(icon_list);
		this.getActors().sort();
		
		
		// Set the viewport
		camera= new OrthographicCamera();
		setViewport(new FitViewport(this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT,camera));
	
	    // Load the textures
	    game.getAssetManager().load("factory_background.png", Texture.class);
	    game.getAssetManager().load("play.png", Texture.class);
	    game.getAssetManager().load("play_mouse.png", Texture.class);
	    game.getAssetManager().load("exit.png", Texture.class);
	    game.getAssetManager().load("exit_mouse.png", Texture.class);
	    
	    game.getAssetManager().finishLoading();
	    
	    initializeIcons();
	    
	}	
	/**
	 * Initializes all buttons of the game
	 */
	private void initializeIcons()
	{
		InicialHolder build_icon= new InicialHolder(this.VIEWPORT_WIDTH,this.VIEWPORT_HEIGHT);
		this.icon_list.addIcon(build_icon);
		
		this.icon_list.setHolder(1);
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
	 * @return Return the Class GroundUpGame of this game
	 */
	public GroundUpGame getGame() {
		return game;
	}
	
	
	
	
	
}
