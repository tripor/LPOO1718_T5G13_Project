package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

import graphic.GameStage;
import logic.storage.PersonList;
/**
 * Class that handles the user input
 *
 */
public class UserControl {
	
	private GameStage game;
	
	public UserControl(GameStage game) {
		this.game=game;
	}
	
	public void InputHandler() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {	// camera goes up.
            game.getCamera().translate(new Vector3(0,5,0));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {	// camera goes down
            game.getCamera().translate(new Vector3(0,-5,0));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {	// camera goes right
            game.getCamera().translate(new Vector3(5,0,0));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {	// camera goes left
            game.getCamera().translate(new Vector3(-5,0,0));
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        		PersonList.getInstance().popPaths();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
        	System.exit(0);
        }
        int amount = 0;
        Gdx.input.getInputProcessor().scrolled(amount);
	}
}
