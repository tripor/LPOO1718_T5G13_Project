package graphic;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.ConveyorG;
import logic.Entity;
import logic.entities.ConveyorL;

/**
 * Saves the conveyor in map
 *
 */
public class ConveyorList extends GroupExtension{
	/**
	 * Constructor for the group of conveyors
	 * @param game
	 */
	public ConveyorList() {
	}
	/**
	 * Adds a conveyor to the group of actors and to the map
	 * @param c The conveyor I want to add
	 * @return True if it added of false otherwise
	 */
	public boolean addConveyor(ConveyorG c) {

		if(GameStage.singleton.map.addMap(c.instance))
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
		GameStage.singleton.map().removeMap(c.instance);
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
		for(Entity it:GameStage.singleton.map.lista)
		{
			if (ConveyorL.class.isAssignableFrom(it.getClass())) {
				ConveyorG novo = new ConveyorG(it);
				this.addConveyor(novo);
			}
		}
	}
	
}
