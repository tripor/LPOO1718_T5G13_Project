package graphic;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import logic.console.Console;
import logic.map.Map;
import place.type.Factory;

public class GameStage extends Stage {
	
    public static final int VIEWPORT_WIDTH = 40;
    static final float PIXEL_TO_METER = 1f / 500;

    public GroundUpGame game = GroundUpGame.getInstance();

	Map map = Map.getInstance();
    
    public GameStage() {
    	// Set the viewport
		// float ratio = ((float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth());
        setViewport(new ScreenViewport());

        // Load the textures
        game.getAssetManager().load("factory.png", Texture.class);
        game.getAssetManager().load("worker.png", Texture.class);
        game.getAssetManager().finishLoading();
        
        initGame();
        
        Console.log("Left=" + this.getCamera().position.x
        		+ " Right=" + this.getWidth() + "VS" + this.getCamera().viewportWidth
        		+ " Top=" + this.getCamera().position.y
        		+ " Bottom=" + this.getHeight() + "VS" + this.getCamera().viewportHeight);
    }
    
    private void initGame() {
		map.setMapWidth(500000);
		map.setMapHeight(500000);
		
		int i = 0;
		while (i < 1000) {
			i = generateFactory(i);	// generateFactory will return i++.
		}
    }
    
	private int Random(int min, int max) {
		return (new Random()).nextInt(max) + min;
	}
	
	private int generateFactory(int i) {
		
		int min_size = 20;
		int max_size = 100;

		int row = map.randRow(),
		    col = map.randCol(),
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
		
		if(row+h < map.getMapHeight() || col+w < map.getMapWidth()) {
			
			Factory p = new Factory(row, col, w, h, Random(1,4), door_px);
			
			map.addPlace(p);
			Console.log(p.toString());
			
			this.addActor(p);
			
			i++;
		}
		return i;
	}
}
