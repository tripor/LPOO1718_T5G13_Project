package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

import graphic.GameStage;

public class UserControl {
	
	private GameStage game = GameStage.getInstance();
	
	public UserControl() {
		
	}
	
	public void InputHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {	// camera goes up.
            game.getCamera().translate(new Vector3(0,5,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {	// camera goes down
            game.getCamera().translate(new Vector3(0,-5,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {	// camera goes right
            game.getCamera().translate(new Vector3(5,0,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {	// camera goes left
            game.getCamera().translate(new Vector3(-5,0,0));
        }
        int amount = 0;
        Gdx.input.getInputProcessor().scrolled(amount);
	}
}
