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
	
	public HashMap<String, Person> personMap = new HashMap<String, Person>();
	// public ArrayList<Person> persons = new ArrayList<Person>();
	
	private GameStage game;

	public PersonList(GameStage game) {
		this.game = game;
	}
	
	public boolean addPerson(Person p) {
		
		if(personMap.get(p.getId()) != null) {
			// unique Id repeated.
			
			return false;
			// don't let it add.
		}
		
		if(this.game.map().getPixelMap(p.getRow(), p.getCol()).size() > 0) {
			// [the position where the person is going to stand] is already occupied.
			return false;
		}

		this.game.map().addMap(
				p,
				p.getRow(),
				p.getCol(),
				((int) p.getWidth()),
				((int) p.getHeight())
			);
		
		personMap.put(p.getId(), p);

		// persons.add(p);
		this.addActor(p);
		
		return true;
	}
	
	public boolean movePersonTo(int t_row, int t_col, Person p) {
		
		boolean target_point_occupied = this.game
										.map().getPixelMap(t_row,t_col)
										.size() > 0;
		
		if(!target_point_occupied) {
			
			this.game.map().removeMap(
					p,
					p.getRow(), p.getCol(),
					(int) p.getWidth(), (int) p.getHeight()
				);
			
			p.setCurrentPos(t_row, t_col);
			
			this.game.map().addMap(
					p,
					t_row, t_col,
					(int) p.getWidth(), (int) p.getHeight()
				);

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
