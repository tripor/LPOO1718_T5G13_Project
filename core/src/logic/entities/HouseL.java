package logic.entities;

import logic.Map;
import logic.Place;
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
	
	public void handler(Map map)
	{
		
	}

	@Override
	public float handler() {
		if(Map.singleton.lista_person.size==0 && Map.singleton.lista.size>=2)
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
			novo.setTarget((Place) Map.singleton.lista.get(1));
			novo.id = 0;
			Map.singleton.lista_person.add(novo);
			Map.singleton.lista_person_toActor.add(novo);
		}
		return 0;
	}

	@Override
	public int getPrice() {
		return HouseL.price;
	}

	

}
