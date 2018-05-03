package userInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

import graphic.GameStage;
import graphic.GroundUpScreen;

public class UserInput {
	
	private GameStage game;
	
	public UserInput(GameStage game)
	{
		this.game=game;
	}
	
	public void InputHandler()
	{
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.getCamera().translate(new Vector3(0,5,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            game.getCamera().translate(new Vector3(0,-5,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            game.getCamera().translate(new Vector3(5,0,0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.getCamera().translate(new Vector3(-5,0,0));
        }
	}
}
