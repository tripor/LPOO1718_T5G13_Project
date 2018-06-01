package graphic;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.FactoryG;
import graphic.entities.HouseG;
import graphic.entities.MineG;
import graphic.entities.SmelterG;
import logic.Entity;
import logic.Place;
import logic.entities.FactoryL;
import logic.entities.HouseL;
import logic.entities.MineL;
import logic.entities.SmelterL;



public class PlaceList extends GroupExtension{
	/**
	 * List containing all the place in the game
	 */
	public HashMap<String, ActorExtension> placeMap = new HashMap<String, ActorExtension>();
	
	/**
	 * Constructor for the Class Place List
	 */
	public PlaceList() {
	}
	/**
	 * Missing Description
	 * @param p
	 * @return
	 */
	public boolean addPlace(ActorExtension p) {
		if(GameStage.singleton.map.addMap(p.instance))
		{
			this.addActor(p);
			return true;
		}
		return false;
	}
	/**
	 * Removes the Place from the game and the group of actors
	 * @param p The place I want to remove
	 */
	public void removePlace(ActorExtension p) {
		this.removeActor(p);
		GameStage.singleton.map.removeMap(p.instance);
	}
	
	public void removePlace(Place p) {
		for(Actor it:this.getChildren())
		{
			if(((ActorExtension) it).getInstance()==p)
			{
				this.removePlace((ActorExtension) it);
			}
		}
	}
	/**
	 * Randomly return a building on the map
	 */
	/*public Place getNthBuilding(int n) {
		
		Iterator<Entry<String, Place>> i = placeMap.entrySet().iterator();

		while(n-- >= 1) {
			if(i.hasNext()) {
				i.next();
			}
		}
		
	    java.util.Map.Entry<String, Place> entry = (java.util.Map.Entry<String, Place>) i.next();
		// index = 0.
	    
		return (Place) entry.getValue();
		
	}
	
	public Place getRandomPlace() {
		
		int index = (new Random()).nextInt(placeMap.size());
		return getNthBuilding(index);
	}*/
	/**
	 * Randomly return a building, but avoid the building `s`.
	 */
	/*public int getRandomNumAvoid(int num, int max) {
		
		if(max <= 1) {
			return -1;
		}
		int result = ((new Random()).nextInt(max-1));
		
		if(result >= num) {
			result++;
		}
		return result;
	}
	public Place getRandomPlace(Place s) {

		int index = -1,
			places_size = placeMap.size();
		Place result;
		
		while(true) {
			
			if(index >= 0) {
				// that means, a repeat happened before.
				index = getRandomNumAvoid(index, places_size);
			}
			else {
				index = ((new Random()).nextInt(places_size));
			}
			
			result = getNthBuilding(index);
			
			if(!result.equals(s)) {
				break;
			}
		}
		
		return result;
		
	}*/
	/**
	 * Missing Description
	 * @param row
	 * @param col
	 * @return
	 */
	/*public Place checkIfPointInBuilding(int row, int col) {
		
		ArrayList<Actor> map = this.game.map().getMap(row, col);
		
		for(Object el : map) {
			
			if(Place.class.isAssignableFrom(el.getClass())) {
				
				if(((Place) el).including(row, col)) {
					return ((Place) el);
				}
			}
		}
		return null;
	}*/
	
	/**
	 * Loads all the Places to the screen
	 */
	
	@Override
	public void loadFromMap()
	{
		for(Entity it:GameStage.singleton.map.getList())
		{
			ActorExtension novo=null;
			if(FactoryL.class.isAssignableFrom(it.getClass()))
			{
				novo=new FactoryG(it);
			}
			else if(MineL.class.isAssignableFrom(it.getClass()))
			{
				novo=new MineG(it);
			}
			else if(HouseL.class.isAssignableFrom(it.getClass()))
			{
				novo=new HouseG(it);
			}
			else if(SmelterL.class.isAssignableFrom(it.getClass()))
			{
				novo=new SmelterG(it);
			}
			if(novo!=null)
				this.addPlace(novo);
		}
	}
	
	
	
}
