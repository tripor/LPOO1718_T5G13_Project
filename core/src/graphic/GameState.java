package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameState extends Stage {
    public static final int VIEWPORT_WIDTH = 4;

    static final float PIXEL_TO_METER = 0.22f / 200;
    
    
    public GameState(GroundUpGame game)
    {
    	// Set the viewport
        float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new FitViewport(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ratio));

        // Load the textures
        game.getAssetManager().load("factory.png", Texture.class);
        game.getAssetManager().load("worker.png", Texture.class);
        game.getAssetManager().finishLoading();
    }
}
