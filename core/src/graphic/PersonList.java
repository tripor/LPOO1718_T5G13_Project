package graphic;

import java.util.HashMap;

/**
 * Class that saves and handles all the persons in the game
 *
 */
public class PersonList extends GroupExtension{
	
	
	//public HashMap<String, Person> personMap = new HashMap<String, Person>();
	// public ArrayList<Person> persons = new ArrayList<Person>();
	
	private GameStage game;

	public PersonList(GameStage game) {
		this.game = game;
	}
	
	/*public boolean addPerson(Person p) {
		
		if(personMap.get(p.getId()) != null) {
			// unique Id repeated.
			
			return false;
			// don't let it add.
		}
		
		if(this.game.map().getPixelMap(p.getCol(), p.getRow()).size() > 0) {
			// [the position where the person is going to stand] is already occupied.
			return false;
		}

		this.game.map().addMap(
				p,
				p.getCol(),
				p.getRow(),
				((int) p.getWidth()),
				((int) p.getHeight())
			);
		
		personMap.put(p.getId(), p);

		// persons.add(p);
		this.addActor(p);
		this.lista.add(p);
		
		return true;
	}
	
	public boolean movePersonTo(int t_row, int t_col, Person p) {
		
		ArrayList<Actor> list = this.game.map().getPixelMap(t_col,t_row);
		int list_size = list.size();
		
		for(Actor a : list) {
			if(Person.class.isAssignableFrom(a.getClass())) {
				if(((Person) a).getId().equals(p.getId())) {
					// if the block is himself, don't consider it as a block.
					list_size--;
				}
			}
		}
		
		boolean target_point_occupied = (list_size > 0);
										
		Console.log("To: " + t_row + "," + t_col);
		
		for(Actor a : this.game.map().getPixelMap(t_col,t_row)) {
			Console.log(a.getClass().toString());
		}
		
		if(!target_point_occupied) {
			
			this.game.map().removeMap(
					p,
					p.getCol(), p.getRow(),
					(int) p.getWidth(), (int) p.getHeight()
				);
			
			p.setCurrentPos(t_row, t_col);
			
			this.game.map().addMap(
					p,
					t_col, t_row,
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
		
		for(Person p : lista) {
			p.popPath();
		}
	}*/
}