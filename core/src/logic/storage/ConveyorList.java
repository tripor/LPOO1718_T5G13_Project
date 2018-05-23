package logic.storage;

import java.util.ArrayList;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import graphic.GroupExtension;
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
	 * Saves all the conveyor in the map
	 */
	private ArrayList<Conveyor> lista;
	/**
	 * Constructor for the group of conveyors
	 * @param game
	 */
	public ConveyorList(GameStage game) {
		this.game=game;
		this.lista= new ArrayList<Conveyor>();
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
		this.lista.add(c);
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
	/**
	 * 
	 * @return Return a list with all the conveyor in the game
	 */
	public ArrayList<Conveyor> getLista() {
		return lista;
	}
	/**
	 * Readds all the conveyor to the group of conveyors
	 */
	public void reAdd()
	{
		for(Conveyor it:this.lista)
		{
			this.addActor(it);
		}
	}
	
}
