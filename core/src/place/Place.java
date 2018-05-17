package place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import graphic.ActorExtension;
import com.groundup.game.GameStage;
import graphic.GroundUpGame;
import logic.map.Map;
import material.Material;
import person.Person;
/**
 * Abstract Class for all the places of the game
 *
 */
public abstract class Place extends ActorExtension {
	
	private HashMap<String,Integer> storage = new HashMap<String,Integer>();
	
	public List<Person> _people_here = new ArrayList<Person>();
	public int	bound_top,
				bound_bottom,
				bound_left,
				bound_right,
				door_col,
				door_row,
				
				// for testing.
				door_atBorder,
				door_atPx;
	
	public String unique_id = "undefined";
	
	
	
	/**
	 * Constructor of the class Place
	 * @param game 
	 * @param doorAtBorder 1 = TOP 2 = RIGHT 3 = BOTTOM 4 = LEFT
	 * @param doorAtPx Clockwise - for easier object rotation. i.e., for [TOP, 2] = 3rd pixel from left. (0, 1, {2}<=Third) [RIGHT, ... [LEFT, 5] = 6th pixel from bottom. [BOTTOM, ...
	 */
	public Place(GameStage game, int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		this.game=game;
		
		// for rectangle place
		this.bound_top    = top;
		this.bound_right  = left + width;
		this.bound_bottom = top + height;
		this.bound_left   = left;
		
		// for testing.
		this.door_atBorder = doorAtBorder;
		this.door_atPx = doorAtPx;
		
		if(doorAtBorder == 1) {
			// at top, count from left.
			this.door_col = this.bound_left + doorAtPx;
			this.door_row = this.bound_top - 1;	// make it outside of the building
		}
		else if(doorAtBorder == 2) {
			// at right, count from top
			this.door_col = this.bound_right + 1;
			this.door_row = this.bound_top + doorAtPx;
		}
		else if(doorAtBorder == 3) {
			// at bottom, count from right
			this.door_col = this.bound_right - doorAtPx;
			this.door_row = this.bound_bottom + 1;
		}
		else if(doorAtBorder == 4) {
			// at left, count from bottom
			this.door_col = this.bound_left - 1;
			this.door_row = this.bound_bottom - doorAtPx;
		}
	}

	public int getBoundTop() {
		return bound_top;
	}

	public int getBoundBottom() {
		return bound_bottom;
	}

	public int getBoundLeft() {
		return bound_left;
	}

	public int getBoundRight() {
		return bound_right;
	}

	public int getDoorRow() {
		return door_row;
	}

	public int getDoorCol() {
		return door_col;
	}
	/**
	 * Adds a material to the place storage
	 * @param mat The material I want to add
	 */
	public void addToStorage(Material mat)
	{
		if(this.storage.containsKey(mat.type))
			this.storage.put(mat.type, this.storage.get(mat.type).intValue()+1);
		else 
			this.storage.put(mat.type, 1);
		this.game.addUnusedMaterial(mat);
	}
	/**
	 * Removes a material form the place
	 * @param type String name of material or "any" for the any material
	 * @return the material or null if non
	 */
	public Material removeMaterial(String type)
	{
		if(type.equals("any"))
		{
			if(this.storage.isEmpty())
				return null;
			Entry<String, Integer> entry = this.storage.entrySet().iterator().next();
			int updated_value=this.storage.get(entry.getKey()).intValue() -1;
			this.storage.put(entry.getKey(),updated_value);
			if(updated_value==0)
			{
				this.storage.remove(entry.getKey());
			}
			return this.game.removeUnusedMaterial();
		}
		else
		{
			if(!this.storage.containsKey(type))
				return null;
			int updated_value=this.storage.get(type).intValue() -1;
			this.storage.put(type,updated_value);
			if(updated_value==0)
			{
				this.storage.remove(type);
			}
			return this.game.removeUnusedMaterial();
		}
	}

    @Override
    public boolean equals(Object arg0) {
        Place other = (Place) arg0;
        return other.getBoundLeft() == this.getBoundLeft() && other.getBoundRight() == this.getBoundRight() && other.getBoundTop() == this.getBoundTop() && other.getBoundBottom() == this.getBoundBottom() && other.getDoorRow() == this.getDoorRow() && other.getDoorCol() == this.getDoorCol();
    }
	
	public boolean overlapWith(Place p) {
		
		if(p.including(this.getDoorRow(), this.getDoorCol())) {
			return true;
			// We should not allow the door of a factory inside another building.
		}
		
		boolean colOverlap = p.getBoundLeft() <= this.getBoundRight()
						&& p.getBoundRight() >= this.getBoundLeft();
		
		boolean rowOverlap = p.getBoundTop() <= this.getBoundBottom()
						&& p.getBoundBottom() >= this.getBoundTop();

		return (colOverlap && rowOverlap);
	}
	
	public boolean including(int row, int col) {
		return this.col_overlap_with(col) && this.row_overlap_with(row);
	}

	
	public String toString() {
		return "[" + this.getType() + " " + unique_id + "] "
			+ "From"
				+ " Row " + this.getBoundTop()
				+ " Col " + this.getBoundLeft()
			+ " | To"
				+ " Row " + this.getBoundBottom()
				+ " Col " + this.getBoundRight()
			+ " | Door at Row " + this.getDoorRow() + " Col " + this.getDoorCol()
			+ " = new Place(" + this.bound_top + "," + this.bound_left + "," + (this.bound_right - this.bound_left) + "," + (this.bound_bottom - this.bound_top) + "," + this.door_atBorder + "," + this.door_atPx + ")";
	}
	
	private boolean row_overlap_with(int row) {
		return in_between(row, this.getBoundTop(), this.getBoundBottom());
	}
	
	private boolean col_overlap_with(int col) {
		return in_between(col, this.getBoundLeft(), this.getBoundRight());
	}
	
	private boolean in_between(int num, int bound1, int bound2) {
		
		// assume `bound1` is smaller.
		int min = bound1, max = bound2;
		
		// if `bound2` is smaller, just reverse it :)
		if(min > max) {
			min = bound2; max = bound1;
		}
		return (num <= max && num >= min);
	}
	
	public void setUniqueId(String id) {
		this.unique_id = id;
		
		// for testing.
		this.game.map().setTestingTrack(id);
	}
	
	public String getUniqueId() {
		return this.unique_id;
	}
	
	public String getType() {
		return "Place";
	}
	
//	public void sizePlace(float amountX, float amountY) {
//		this.setWidth(amountX);
//		this.setHeight(amountY);
//		sprite.setSize(this.getWidth(), this.getHeight());
//	}
}
