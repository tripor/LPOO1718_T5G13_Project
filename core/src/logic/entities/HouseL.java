package logic.entities;

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
	 * Constructor of the class House Logic with width 30 and height 30
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public HouseL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,HouseL.width,HouseL.height,doorAtBorder);
	}
	
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
	}

	@Override
	public float handler() {
		if(Map.singleton.getLista_person().size==0 && Map.singleton.getLista().size>=2)
		{
			int x=this.posX+HouseL.width/2,y=this.posY+HouseL.height/2;
			switch(this.direction)
			{
				case 1:
					y+=HouseL.height/1.2;
					break;
				case 2:
					x+=HouseL.width/1.2;
					break;
				case 3:
					y-=HouseL.height/1.2;
					break;
				case 4:
					x-=HouseL.width/1.2;
					break;
			}
			PersonL novo = new PersonL(x,y);
			novo.setTarget((Place) Map.singleton.getLista().get(1));
			novo.id = 0;
			Map.singleton.getLista_person().add(novo);
			Map.singleton.getLista_person_toActor().add(novo);
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

	

}
