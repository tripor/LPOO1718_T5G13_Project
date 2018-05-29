package graphic.control;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import graphic.GameStage;
import logic.Entity;
import logic.Place;
import logic.entities.ConveyorL;
import logic.entities.InserterL;
import logic.entities.MaterialL;
import logic.entities.PersonL;
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
		if (!this.game.menuOpen) {
			if (Gdx.input.isKeyPressed(Input.Keys.W)) { // camera goes up.
				game.getCamera().translate(new Vector3(0, 5*this.game.scale(), 0));
				this.checkMapPosition();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S)) { // camera goes down
				game.getCamera().translate(new Vector3(0, -5*this.game.scale(), 0));
				this.checkMapPosition();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D)) { // camera goes right
				game.getCamera().translate(new Vector3(5*this.game.scale(), 0, 0));
				this.checkMapPosition();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A)) { // camera goes left
				game.getCamera().translate(new Vector3(-5*this.game.scale(), 0, 0));
				this.checkMapPosition();
			}
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				//this.game.people().popPaths();
			}
		}
	}
	/**
	 * Corrects if the translation of the camera went out of the map
	 */
	public void checkMapPosition()
	{
		int width= (int) (this.game.map().getMapWidth()*this.game.scale());
		int camera_width=this.game.VIEWPORT_WIDTH/2;
		int camera_height=this.game.VIEWPORT_HEIGHT/2;
		Vector3 vector=game.getCamera().position;
		float x=vector.x;
		float y=vector.y;
		if(x<camera_width)
		{
			this.game.getCamera().position.set(new Vector3(camera_width,y,0));
			x=camera_width;
		}
		
		if(y<camera_height)
		{
			this.game.getCamera().position.set(new Vector3(x,camera_height,0));
			y=camera_height;
		}
		if(x>width-camera_width)
		{
			this.game.getCamera().position.set(new Vector3(width-camera_width,y,0));
			x=width-camera_width;
		}
		if(y>width-camera_height)
		{
			this.game.getCamera().position.set(new Vector3(x,width-camera_height,0));
			y=width-camera_height;
		}
	}
	/**
	 * Remove from the map the places/inserters/conveyor in the mouse position
	 */
	private void deleteFromMap()
	{
		Vector3 mouse_pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		this.game.getViewport().unproject(mouse_pos);
		Array<Entity> elements=this.game.map().getMapPixel((int)(mouse_pos.x*this.game.reverseScale()),(int)(mouse_pos.y*this.game.reverseScale()));
		ArrayList<Entity> remover=new ArrayList<Entity>();
		for (Entity it : elements) {
			if (PersonL.class.isAssignableFrom(it.getClass())) {
			} else {
				remover.add(it);
			}
		}
		for(Entity it:remover)
		{
			if(Place.class.isAssignableFrom(it.getClass()))
			{
				this.game.places().removePlace((Place)it);
			}
			else if(ConveyorL.class.isAssignableFrom(it.getClass()))
			{
				this.game.conveyors().removeConveyor((ConveyorL)it);
			}
			else if(InserterL.class.isAssignableFrom(it.getClass()))
			{
				this.game.inserters().removeInserter((InserterL)it);
			}
			else if(MaterialL.class.isAssignableFrom(it.getClass()))
			{
				this.game.materials().removeMaterialAll((MaterialL)it);
			}
		}
	}
	@Override
	public boolean keyDown(int keycode) {
		if (!this.game.menuOpen) {
			if (keycode == Input.Keys.ESCAPE) {
				this.game.getMouse().isSelected = false;
				this.game.getMouse().remove = false;
			}
			if (keycode == Input.Keys.Q) {
				// this.game.generatePerson();
			}
			if (keycode == Input.Keys.R) {
				this.game.getMouse().rotateMouse();
			}
			if (keycode == Input.Keys.P) {
				//this.game.saveGame();
			}
			if (keycode == Input.Keys.O) {
				//this.game.loadGame();
			}
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
		if(this.game.getMouse().isSelected )
			this.game.getMouse().addObject();
		else if(this.game.getMouse().remove)
		{
			this.deleteFromMap();
		}
		
		
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
