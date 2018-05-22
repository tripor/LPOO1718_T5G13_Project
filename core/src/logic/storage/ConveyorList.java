package logic.storage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import logic.map.Map;

public class ConveyorList extends Group{
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	public ConveyorList(GameStage game) {
		this.game=game;
	}
	
	public boolean addConveyor(Conveyor c) {

		boolean success = this.game.map().tryAdd(c, this.game);
		
		if(!success) {
			return false;
		}
		
		this.addActor(c);
		this.game.map().addMap(c,(int) c.getX(), (int)c.getY(), (int)c.getWidth(),(int) c.getHeight());
		return true;
	}
}
