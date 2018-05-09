package logic.storage;

import java.util.HashMap;
import java.util.Map.Entry;

import person.Person;
/**
 * Class that saves and handles all the persons in the game
 *
 */
public class PersonList {
	
	public HashMap<String, Person> personMap = new HashMap<String, Person>();
	// Usage: conveyorMap.get(index(ROW, COL));
	
	private static PersonList instance = new PersonList();
	
	public PersonList() {
		// TODO Auto-generated constructor stub
	}
	
	public static PersonList getInstance() {
		return instance;
	}
	
	public boolean addPerson(Person p) {
		
		int row = p.getRow();
		int col = p.getCol();
		
		if(getPerson(row, col) == null) {
			personMap.put(index(row, col), p);
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
