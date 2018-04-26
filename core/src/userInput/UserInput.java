package userInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

import graphic.GroundUpScreen;

public class UserInput {
	
	private GroundUpScreen screen;
	
	public UserInput(GroundUpScreen screen)
	{
		this.screen=screen;
	}
	
	public void InputHandler()
	{
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            screen.getCamera().translate(new Vector3(0,-1,0));
        }
	}
}
