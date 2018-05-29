package logic;

import java.util.ArrayList;

import logic.entities.MaterialL;

/**
 * Class that represents the Places of the game
 *
 */
public abstract class Place extends Entity {
	/**
	 * The storage inside the Place
	 */
	private ArrayList<MaterialL> internalStorage = new ArrayList<MaterialL>();
	/**
	 * Door at what border. 1-top,2-right,3-bottom,4-left
	 */
	protected int doorAtBorder;
	/**
	 * Constructor for the extend class
	 * @param posX The X position in Pixels
	 * @param posY The Y position in Pixels
	 * @param width The width of the place in Pixels
	 * @param height The height of the place in Pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	protected Place(int posX,int posY,int width,int height,int doorAtBorder)
	{
		super(posX,posY,width,height);
		this.doorAtBorder=doorAtBorder;
	}
	
	protected Place()
	{
		super();
	}
	/**
	 * Adds a material to the place storage
	 * @param mat The material I want to add
	 */
	public void addToStorage(MaterialL mat)
	{

		mat.id=1;
		
		this.internalStorage.add(mat);
	}
	/**
	 * Removes a material form the place
	 * @param type String name of material or "any" for the any material
	 * @return the material or null if non
	 */
	public MaterialL removeMaterial(String type)
	{
		// Console.log(type);
		
		if(type.equals("any"))
		{
			if(this.internalStorage.isEmpty())
			{
				return null;
			}
			else
			{
				MaterialL devolver=this.internalStorage.get(0);
				this.internalStorage.remove(0);
				return devolver;
			}
		}
		else
		{
			MaterialL devolver;
			for(int i=0; i < this.internalStorage.size();i++)
			{
				if(this.internalStorage.get(i).getType().equals(type))
				{
					devolver=this.internalStorage.get(i);
					this.internalStorage.remove(i);
					return devolver;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @return The position of the door
	 */
	public int getDoorAtBorder() {
		return doorAtBorder;
	}
	
}
