package graphic;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.ConveyorG;
import graphic.entities.InserterG;
import logic.entities.ConveyorL;
import logic.entities.InserterL;

/**
 * Saves the conveyor in map
 *
 */
public class ConveyorList extends GroupExtension{
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	/**
	 * Constructor for the group of conveyors
	 * @param game
	 */
	public ConveyorList(GameStage game) {
		this.game=game;
	}
	/**
	 * Adds a conveyor to the group of actors and to the map
	 * @param c The conveyor I want to add
	 * @return True if it added of false otherwise
	 */
	public boolean addConveyor(ConveyorG c) {

		if(this.game.map.addMap(c.instance))
		{	
			this.addActor(c);
			return true;
		}
		return false;
	}
	/**
	 * Removes the conveyor from the group and the map
	 * @param c The conveyor I want to remove
	 */
	public void removeConveyor(ConveyorG c) {
		this.removeActor(c);
		this.game.map().removeMap(c.instance);
	}
	public void removeConveyor(ConveyorL p) {
		for(Actor it:this.getChildren())
		{
			if(((ActorExtension) it).getInstance()==p)
			{
				this.removeConveyor((ConveyorG) it);
			}
		}
	}
	/**
	 * Loads all the conveyors to the screen
	 */
	public void loadFromMap()
	{
		for(ConveyorL it:this.game.map.lista_conveyor)
		{
			ConveyorG novo=new ConveyorG(this.game,it);
			this.addConveyor(novo);
		}
	}
	
}
