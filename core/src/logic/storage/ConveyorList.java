package logic.storage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import logic.map.Map;
/**
 * Saves the conveyor in map
 *
 */
public class ConveyorList extends Group{
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	public ConveyorList(GameStage game) {
		this.game=game;
	}
	/**
	 * Adds a conveyor to the group of actors and to the map
	 * @param c
	 * @return
	 */
	public boolean addConveyor(Conveyor c) {

		boolean success = this.game.map().tryAdd(c, this.game);
		
		if(!success) {
			return false;
		}
		
		this.addActor(c);
		this.game.map().addMap(c,(int) c.getX(), (int)c.getY(), (int)c.getWidth(),(int) c.getHeight());
		return true;
	}
	/**
	 * Removes the conveyor from the group and the map
	 * @param c The conveyor I want to remove
	 */
	public void removeConveyor(Conveyor c) {
		this.removeActor(c);
		this.game.map().removeMap(c,(int) c.getX(), (int)c.getY(), (int)c.getWidth(),(int) c.getHeight());
	}
}
