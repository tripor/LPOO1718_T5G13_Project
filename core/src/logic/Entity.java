package logic;
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
	
	
}
