package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
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
	private static final int VIEWPORT_WIDTH = 4;
	/**
     * 1 meter is 100 pixels
     */
    private static final float PIXEL_TO_METER = 0.22f / 200;
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
		//load texture
		game.getAssetManager().load("worker.png",Texture.class);
		game.getAssetManager().finishLoading();// to be replaced with update();
		
		//get worker texture
		worker= game.getAssetManager().get("worker.png");
		
		//create camera
		float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
		camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio);
		camera.position.set(new Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0));
	}
	
	
	@Override
	public void render(float delta) {
		super.render(delta);

		this.game.getInput().InputHandler();
		
		// Update the world
        //world.step(delta, 6, 2);
		
		
		// Update the camera
		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		// Clear screen
		Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		// Draw
		game.getBatch().begin();
		game.getBatch().draw(worker,10,10);
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
