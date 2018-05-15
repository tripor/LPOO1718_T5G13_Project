package logic.storage;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Group;

import person.Person;
/**
 * Class that saves and handles all the persons in the game
 *
 */
public class PersonList extends Group{
	
	public HashMap<String, Person> personMap = new HashMap<String, Person>();
	// Usage: conveyorMap.get(index(ROW, COL));
	
	public PersonList() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addPerson(Person p) {
		
		
		int row = p.getRow();
		int col = p.getCol();
		
		if(getPerson(row, col) == null) {
			personMap.put(index(row, col), p);
			this.addActor(p);
			return true;
		}
		return false;
	}
	
	public Person getPerson(int row, int col) {
		return personMap.get(index(row, col));
	}
	
	public String index(int row, int col) {
		return row + "/" + col;
	}
	
	public void popPaths() {
		for (Entry<String, Person> p : personMap.entrySet()) {
			p.getValue().popPath();
		}
	}
}
