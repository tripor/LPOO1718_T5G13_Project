package logic;

import java.util.ArrayList;

import logic.entities.MaterialL;
import logic.entities.PersonL;

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
	 * Storage that can be picked up
	 */
	private ArrayList<MaterialL> externalStorage = new ArrayList<MaterialL>();
	/**
	 * Person that workes in this place
	 */
	protected boolean has_worker=false;

	/**
	 * Time it takes to make a new material
	 */
	protected int work_time=10;
	/**
	 * Time
	 */
	protected int time=0;
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
		this.direction=doorAtBorder;
	}
	/**
	 * Empty constructor for place
	 */
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

		mat.setId(1);
		
		this.internalStorage.add(mat);
	}
	/**
	 * Adds a material to the external storage
	 * @param mat The material I want to add
	 */
	public void addToExternalStorage(MaterialL mat) {
		mat.setId(1);
		this.externalStorage.add(mat);
	}
	/**
	 * Moves the material from internal to external storage
	 * @param mat
	 */
	public void moveToExternal(MaterialL mat) {
		this.internalStorage.remove(mat);
		this.externalStorage.add(mat);
	}
	
	/**
	 * 
	 * @return The position of the door
	 */
	public int getDoorAtBorder() {
		return this.direction;
	}
	@Override
	public abstract boolean receiveMaterial(MaterialL mat);
	
	@Override
	public MaterialL pickUp(String type) {
		if(type.equals("any"))
		{
			if(this.externalStorage.isEmpty())
			{
				return null;
			}
			else
			{
				MaterialL devolver=this.externalStorage.get(0);
				Map.singleton.getList_material_toActor().add(devolver);
				devolver.setId(0);
				this.externalStorage.remove(0);
				return devolver;
			}
		}
		else
		{
			MaterialL devolver;
			for(int i=0; i < this.externalStorage.size();i++)
			{
				if(this.externalStorage.get(i).getType().equals(type))
				{
					devolver=this.externalStorage.get(i);
					Map.singleton.getList_material_toActor().add(devolver);
					devolver.setId(0);
					this.externalStorage.remove(i);
					return devolver;
				}
			}
		}
		return null;
	}
	/**
	 * 
	 * @return Array with all the internal storage
	 */
	public ArrayList<MaterialL> getInternalStorage() {
		return internalStorage;
	}
	/**
	 * 
	 * @return Array with all the external storage
	 */
	public ArrayList<MaterialL> getExternalStorage() {
		return externalStorage;
	}
	/**
	 * Puts the worker inside the place
	 * @param per The worker
	 */
	public void acceptWorker(PersonL per) {
		this.has_worker=true;
		Map.singleton.removeMap(per);
		per.setId(1);
	}
	/**
	 * Makes a place look for a actor
	 */
	public void lookForWorker() {
		Map.singleton.getLooking_for_worker().add(this);
	}
	/**
	 * 
	 * @return If this place has a worker
	 */
	public boolean isHas_worker() {
		return has_worker;
	}
	/**
	 * Sets if this place has a worker
	 * @param has_worker true|false
	 */
	public void setHas_worker(boolean has_worker) {
		this.has_worker = has_worker;
	}
	/**
	 * 
	 * @return The work time of the place
	 */
	public int getWork_time() {
		return work_time;
	}
	/**
	 * 
	 * @return The time passed
	 */
	public int getTime() {
		return time;
	}
	
	
	
	
	
}
