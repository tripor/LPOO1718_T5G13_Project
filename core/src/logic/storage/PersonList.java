package logic.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.async.AsyncExecutor;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.groundup.game.GameStage;

import logic.map.Map;
import person.Person;
/**
 * Class that saves and handles all the persons in the game
 *
 */
public class PersonList extends Group{
	
	/**
     * For testing. For adding 100000 people into the map.
     */
	// private AsyncExecutor asyncExecutor = new AsyncExecutor(10);
	
	public ArrayList<Person> persons = new ArrayList<Person>();
	
	private GameStage game;

	public PersonList(GameStage game) {
		this.game = game;
	}
	
	public boolean addPerson(Person p) {
		
		int left = p.getCol()/Map.division,
			top  = p.getRow()/Map.division;

		ArrayList<Actor> element_list = this.game.map().getMap(top, left);

		// todo: check point, but not check block.
		if(element_list.size() > 0) {
			return false;
		}

		p.setId("P-R" + p.getRow() + "C" + p.getCol());
		this.game.map().addMap(
				p,
				p.getRow(),
				p.getCol(),
				((int) p.getWidth()),
				((int) p.getHeight())
			);
		
		persons.add(p);
		this.addActor(p);
		
		return true;
	}
	
	public boolean movePerson(int s_row, int s_col, int t_row, int t_col) {
		
		// todo: check point, but not check block.
		boolean target_point_occupied = this.game.map().getMap(
				((int) t_row/Map.division),
				((int) t_col/Map.division)
			).size() > 0;
		
		if(!target_point_occupied) {
			
			// todo: finish it.
			return true;
		}
		return false;
	}
	
	public void popPaths() {
		// for (Entry<String, Person> p : personMap.entrySet()) {
			// asyncExecutor.submit(new AsyncTask<Void>() {
		    //    public Void call() {
					// p.getValue().popPath();
			//    		return null;
		    //    } 
		    // });
		// }
	}
}
