package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import place.type.Factory;

public class GameStage extends Stage {
    public static final int VIEWPORT_WIDTH = 4;

    static final float PIXEL_TO_METER = 1f / 500;

	
	public Factory fab;
    
    public GameStage(GroundUpGame game)
    {
    	// Set the viewport
        @SuppressWarnings("unused")
		float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new ScreenViewport());

        // Load the textures
        game.getAssetManager().load("factory.png", Texture.class);
        game.getAssetManager().load("worker.png", Texture.class);
        game.getAssetManager().finishLoading();
        
        
        
        this.fab=new Factory(game,100,100,100,100,100,100);
        this.addActor(fab);
    }
}
