package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
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
	private static final int VIEWPORT_WIDTH = 60;
	/**
     * 1 meter is 100 pixels
     */
    private static final float PIXEL_TO_METER = 1f / 100;
    /**
     * The camera.
     */
    private final OrthographicCamera camera;
    /**
     * The physical world.
     */
    private final World world;
    /**
     * The ball physical body
     */
    private final Body workerBody;
    /**
     * Physics debug camera
     */
    private final Box2DDebugRenderer debugRenderer;
    

    /**
     * Physics debug matrix transformation (meters to pixels)
     */
    private Matrix4 debugCamera;
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
		
		// Create debug camera
        debugRenderer = new Box2DDebugRenderer();
        debugCamera = camera.combined.cpy();
        debugCamera.scl(1 / PIXEL_TO_METER);
        
		//Create the phisical world
		world = new World( new Vector2(0,-10),true);
		
		workerBody=this.createWorkerBody();
	}
	
	private Body createWorkerBody()
	{
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Create the ball body
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        Body body = world.createBody(bodyDef);
        body.setTransform(VIEWPORT_WIDTH / 2, (VIEWPORT_WIDTH * ratio) / 2, 0); // Middle of the viewport, no rotation

        // Create circle shape
        CircleShape circle = new CircleShape();
        circle.setRadius(1f);

        // Create ball fixture
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = .5f;      // how heavy is the ball
        fixtureDef.friction =  .5f;    // how slippery is the ball
        fixtureDef.restitution =  .5f; // how bouncy is the ball

        // Attach ficture to body
        body.createFixture(fixtureDef);

        // Dispose of circle shape
        circle.dispose();

        return body;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

		// Update the camera
		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		// Clear screen
		Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		// Draw
		game.getBatch().begin();
		game.getBatch().draw(worker, 100, 100);
		game.getBatch().end();

		// Render debug camera
		debugCamera = camera.combined.cpy();
		debugCamera.scl(1 / PIXEL_TO_METER);
		debugRenderer.render(world, debugCamera);
	}
	
	/**
	 * gets the screen camera
	 * @return OrthographicCamera camera of the screen
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}

}
