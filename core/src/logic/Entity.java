package logic;

import logic.entities.MaterialL;
import logic.entities.PersonL;

/**
 * General class for entities of the game
 *
 */
public abstract class Entity {
	/**
	 * The X position in pixels
	 */
	protected int posX;
	/**
	 * The Y position in pixels
	 */
	protected int posY;
	/**
	 * The width in pixels
	 */
	protected int width;
	/**
	 * The height in pixels
	 */
	protected int height;
	/**
	 * Direction it is facing
	 */
	protected int direction=4;
	/**
	 * Constructor for the extended classes
	 * @param posX The x position in pixels
	 * @param posY The y position in pixels
	 * @param width The width in pixels
	 * @param height The height in pixels
	 */
	protected Entity(int posX,int posY,int width,int height)
	{
		if(Map.division-width>0)
			this.posX=posX+(Map.division-width)/2;
		else
			this.posX=posX;
		if(Map.division-height>0)
			this.posY=posY+(Map.division-height)/2;
		else
			this.posY=posY;
		this.width=width;
		this.height=height;
	}
	protected Entity()
	{
		
	}
	/**
	 * 
	 * @return Returns the X position in Pixels
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * 
	 * @return Returns the Y position in pixels
	 */
	public int getPosY() {
		return posY;
	}
	/**
	 * 
	 * @return Returns the right bound
	 */
	public int getRight() {
		return (posX + width);
	}
	/**
	 * 
	 * @return Returns the top bound
	 */
	public int getTop() {
		return (posY - height);
	}
	/**
	 * 
	 * @return Returns the width in pixels
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * 
	 * @return Returns the height in pixels
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * Sets the position of this entity
	 * @param posX The X position in pixels 
	 * @param posY The Y position in pixels
	 */
	public void setPosition(int posX,int posY)
	{
		this.posX=posX;
		this.posY=posY;
	}
	/**
	 * Should be called to move entities
	 * @return Float with information depending on the entity
	 */
	public abstract float handler();
	/**
	 * Entity tries to receive a material
	 * @param mat The material to receive
	 * @return True if it's able to receive a material or false otherwise
	 */
	public boolean receiveMaterial(MaterialL mat)
	{
		Map.singleton.addMap(mat);
		mat.setId(0);
		return true;
	}
	/**
	 * Tries to pick up from this entity
	 * @param type The type of material trying to pick up
	 * @return Null if can't of material the picked up material
	 */
	public MaterialL pickUp(String type) {
		return null;
	}
	/**
	 * Adds this entity to the list of the map
	 * @return True if it's possible or false otherwise
	 */
	public boolean addEntity() {
		if(Map.singleton.getMoney()>=this.getPrice())
		{
			int money=Map.singleton.getMoney(),money_wasted=Map.singleton.getMoney_wasted();
			Map.singleton.setMoney(money-this.getPrice());
			Map.singleton.setMoney_wasted(money_wasted+this.getPrice());
			Map.singleton.getList().add(this);
			return true;
		}
		return false;
	}
	/**
	 * Removes this entity from the list in the map
	 */
	public void removeEntity()
	{
		Map.singleton.getList().removeValue(this, true);
	}
	/**
	 * Gets the cost of the entity
	 * @return The cost in int
	 */
	public abstract int getPrice();
	/**
	 * 
	 * @return Returns this entity facing direction
	 */
	public int getDirection() {
		return this.direction;
	}
	/**
	 * Door X position
	 * @return The X position
	 */
	public int doorXposition() {
		if(this.direction==1 || this.direction==3) {
			return (int) (this.posX+this.width/2);
		}
		else if(this.direction==4){
			return this.posX-PersonL.width;
		}
		else {
			return this.posX+this.width;
		}
	}
	/**
	 * Door Y position
	 * @return The Y position
	 */
	public int doorYposition() {
		if(this.direction==2 || this.direction==4) {
			return (int) (this.posY+this.height/2);
		}
		else if(this.direction==3){
			return this.posY-PersonL.height;
		}
		else {
			return this.posY+this.height;
		}
	}
	/**
	 * Sets this entity X position
	 * @param posX The X position
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * Sets this entity Y position
	 * @param posY The Y position
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
