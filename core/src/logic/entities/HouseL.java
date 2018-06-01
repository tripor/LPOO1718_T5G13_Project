package logic.entities;

import java.util.ArrayList;

import logic.Map;
import logic.Place;
import logic.Recipe;
/**
 * Handles the logic of the house
 *
 */
public class HouseL extends Place {
	/**
	 * Price of the house
	 */
	public static int price=100;
	/**
	 * Width of the house
	 */
	public static int width=30;
	/**
	 * Height of the house
	 */
	public static int height=30;
	/**
	 * People inside the building
	 */
	private ArrayList<PersonL> inside;
	/**
	 * Max number of people inside
	 */
	private int maxNumber=10;
	/**
	 * Constructor of the class House Logic with width 30 and height 30
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public HouseL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,HouseL.width,HouseL.height,doorAtBorder);
		inside=new ArrayList<PersonL>();
		for(int i=0;i<this.maxNumber;i++)
		{
			this.inside.add(new PersonL(this.doorXposition(),this.doorYposition()));
		}
	}
	/**
	 * Empty constructor
	 */
	public HouseL()
	{
		super();
	}
	/**
	 * Sells all items in storage
	 */
	public void sell() {
		for(MaterialL it:this.getInternalStorage())
		{
			if(it.getType().equals("iron_ore"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.iron_ore_price);
			if(it.getType().equals("copper_ore"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.copper_ore_price);
			if(it.getType().equals("iron_plate"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.iron_plate_price);
			if(it.getType().equals("copper_plate"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.copper_plate_price);
			if(it.getType().equals("copper_cable"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.copper_cable_price);
			if(it.getType().equals("electronic_circuit"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.electronic_circuit_price);
			if(it.getType().equals("gear"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.gear_price);
			if(it.getType().equals("advanced_circuit"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.advanced_circuit_price);
			if(it.getType().equals("processing_unit"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.processing_unit_price);
			if(it.getType().equals("pipe"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.pipe_price);
			if(it.getType().equals("engine_unit"))
				Map.singleton.setMoney(Map.singleton.getMoney()+Recipe.engine_unit_price);
			
		}
		this.getInternalStorage().clear();
	}

	@Override
	public float handler() {
		if(!this.inside.isEmpty()) {
			ArrayList<Place> remove= new ArrayList<Place>();
			for(Place it:Map.singleton.getLooking_for_worker())
			{
				remove.add(it);
				PersonL ir=this.inside.remove(0);
				//ir.setTarget(it);
				//ir.setId(0);
				//Map.singleton.getList_person_toActor().add(ir);
			}
			for(Place it:remove)
			{
				Map.singleton.getLooking_for_worker().removeValue(it, true);
			}
		}
		if(!this.getInternalStorage().isEmpty())
		{
			this.sell();
		}
		return 0;
	}

	@Override
	public int getPrice() {
		return HouseL.price;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		this.addToStorage(mat);
		Map.singleton.removeMap(mat);
		return true;
	}
	/**
	 * 
	 * @return The maximum number of people in the building
	 */
	public int getMaxNumber() {
		return maxNumber;
	}
	/**
	 * 
	 * @return Array with the people inside
	 */
	public ArrayList<PersonL> getInside() {
		return inside;
	}
	
	
	

}
