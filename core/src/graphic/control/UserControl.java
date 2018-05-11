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
public class UserControl  {
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
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {	// camera goes down
            game.getCamera().translate(new Vector3(0,-5,0));
            this.checkMapPosition();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {	// camera goes right
            game.getCamera().translate(new Vector3(5,0,0));
            this.checkMapPosition();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {	// camera goes left
            game.getCamera().translate(new Vector3(-5,0,0));
            this.checkMapPosition();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        		PersonList.getInstance().popPaths();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
        	System.exit(0);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.Q))
        {
        	
        }
        int amount = 0;
        Gdx.input.getInputProcessor().scrolled(amount);
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
}
