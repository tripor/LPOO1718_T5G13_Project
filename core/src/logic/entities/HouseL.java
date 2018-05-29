package logic.entities;

import logic.Map;
import logic.Place;

public class HouseL extends Place {
	/**
	 * Price of the house
	 */
	public static int price=100;
	/**
	 * Constructor of the class House Logic with width 30 and height 30
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public HouseL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,30,30,doorAtBorder);
	}
	
	public HouseL()
	{
		super();
	}
	
	public void handler(Map map)
	{
		if(map.lista_person.size==0 && map.lista_place.size>=2)
		{
			int x=this.posX+this.width/2,y=this.posY+this.height/2;
			switch(this.doorAtBorder)
			{
				case 1:
					y+=this.height/1.2;
					break;
				case 2:
					x+=this.width/1.2;
					break;
				case 3:
					y-=this.height/1.2;
					break;
				case 4:
					x-=this.width/1.2;
					break;
			}
			PersonL novo= new PersonL(x,y,map);
			novo.id=0;
			map.lista_person.add(novo);
			map.lista_person_toActor.add(novo);
		}
	}

}
