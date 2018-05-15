package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import graphic.GameStage;
import logic.storage.PersonList;
/**
 * Class that handles the user input
 *
 */
public class UserControl implements InputProcessor  {
	/**
	 * The game that the control belongs
	 */
	private GameStage game;
	/**
	 * Constructor of the class UserControl
	 * @param game The game that it belongs
	 */
	public UserControl(GameStage game) {
		this.game=game;
	}
	/**
	 * Takes care of the input of the user
	 */
	public void InputHandler() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {	// camera goes up.
            game.getCamera().translate(new Vector3(0,5,0));
            this.checkMapPosition();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {	// camera goes down
            game.getCamera().translate(new Vector3(0,-5,0));
            this.checkMapPosition();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {	// camera goes right
            game.getCamera().translate(new Vector3(5,0,0));
            this.checkMapPosition();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {	// camera goes left
            game.getCamera().translate(new Vector3(-5,0,0));
            this.checkMapPosition();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        	this.game.getPerson_list().popPaths();
        }
	}
	/**
	 * Corrects if the translation of the camera went out of the map
	 */
	public void checkMapPosition()
	{
		Vector3 vector=game.getCamera().position;
		float x=vector.x;
		float y=vector.y;
		if((GameStage.VIEWPORT_WIDTH/2)>x)
		{
			this.game.getCamera().position.set(new Vector3((GameStage.VIEWPORT_WIDTH/2),y,0));
			x=(GameStage.VIEWPORT_WIDTH/2);
		}
		if(this.game.getMap().getMapWidth()-(GameStage.VIEWPORT_WIDTH/2)<=x)
		{
			this.game.getCamera().position.set(new Vector3(this.game.getMap().getMapWidth()-(GameStage.VIEWPORT_WIDTH/2),y,0));
			x=this.game.getMap().getMapWidth()-(GameStage.VIEWPORT_WIDTH/2);
		}
		if((GameStage.VIEWPORT_HEIGHT/2)>=y)
		{
			this.game.getCamera().position.set(new Vector3(x,(GameStage.VIEWPORT_HEIGHT/2),0));
			y=(GameStage.VIEWPORT_HEIGHT/2);
		}
		if(this.game.getMap().getMapHeight()-(GameStage.VIEWPORT_HEIGHT/2)<=y)
		{
			this.game.getCamera().position.set(new Vector3(x,this.game.getMap().getMapHeight()-(GameStage.VIEWPORT_HEIGHT/2),0));
			y=this.game.getMap().getMapHeight()-(GameStage.VIEWPORT_HEIGHT/2);
		}
	}
	@Override
	public boolean keyDown(int keycode) {
        if(keycode==Input.Keys.ESCAPE)
        {
        	System.exit(0);
        }
        if(keycode==Input.Keys.Q)
        {
        	this.game.generatePerson(1);
        }
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		
		
		this.game.getMouse().addObject();
		
		
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
