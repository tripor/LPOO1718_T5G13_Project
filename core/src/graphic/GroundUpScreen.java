package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
/**
 * 
 * Class that deals with the Screen for the game
 *
 */
public class GroundUpScreen extends ScreenAdapter {
	/**
	 * The game it self
	 */
	private final GroundUpGame game;
	/**
	 * Texture for the worker
	 */
	private final Texture worker;
	/**
     * Viewport width in meters. Height depends on screen ratio
     */
	private static final int VIEWPORT_WIDTH = 6;
	/**
     * 1 meter is 100 pixels
     */
    private static final float PIXEL_TO_METER = 1f / 100;
    /**
     * The camera.
     */
    private final OrthographicCamera camera;
	
	/**
	 * Constructor for the GroundUpScreen class
	 * @param game
	 */
	public GroundUpScreen(GroundUpGame game) {
		this.game=game;
		game.getAssetManager().load("worker.png",Texture.class);
		// to be replaced with update();
		game.getAssetManager().finishLoading();
		
		worker= game.getAssetManager().get("worker.png");
		
		//create camera
		float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
		camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio);
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		// Update the camera
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
		
		//Clear screen
		 Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
	     Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
	     
	     //Draw
	     game.getBatch().begin();
	     game.getBatch().draw(worker, 100, 100);
	     game.getBatch().end();
	}
	/**
	 * gets the screen camera
	 * @return OrthographicCamera camera of the screen
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

}
