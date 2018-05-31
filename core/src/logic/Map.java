package logic;


import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.utils.Array;

import logic.entities.BackGroundL;
import logic.entities.MaterialL;
import logic.entities.PersonL;


/**
 * Class that handles the map logic
 *
 */
public class Map {
	
	/**
	 * Amount of pixel the map is divided. Squares 10 by 10
	 */
	public final static int division = 10;
	/**
	 * This map Width in pixels
	 */
	private int mapWidth;
	/**
	 * This map Height in pixels
	 */
	private int mapHeight;
	/**
	 * The blocks with all the entities
	 */
	private transient Array<Array<Array<Entity>>> map;
	/**
	 * Array with all the background of the game
	 */
	private Array<Array<BackGroundL>> lista_background;
	/**
	 * Array with all the static entities
	 */
	private Array<Entity> lista;
	/**
	 * Array with all the materials in the map
	 */
	private Array<MaterialL> lista_material;
	/**
	 * Array with all the Persons in the map
	 */
	private Array<PersonL> lista_person;
	/**
	 * Materials looking for an actor
	 */
	private transient Array<MaterialL> lista_material_toActor;
	/**
	 * Persons looking for an actor
	 */
	private transient Array<PersonL> lista_person_toActor;
	/**
	 * Money the player has
	 */
	private int money;
	/**
	 * Money the player has wasted all game
	 */
	private int money_wasted;
	/**
	 * Singleton of the map
	 */
	public static Map singleton;
	/**
	 * Gets a random number
	 * @param min The min number
	 * @param max The max number
	 * @return The random number created
	 */
	public int randomNumber(int min,int max)
	{
		Random rand = new Random();
		int  n = rand.nextInt(max) + min;
		return n;
	}
	
	/**
	 * Constructor of the map
	 * @param width The width and height of the map
	 */
	public Map(int width) {
		Map.singleton=this;
		this.mapWidth = width;
		this.mapHeight = width;
		map= new Array<Array<Array<Entity>>>();
		for(int i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			map.add(new Array<Array<Entity>>());
			for(int j=0; j<this.transformToBlock(mapHeight);j++)
			{
				map.get(i).add(new Array<Entity>());
			}
		}
		this.createBackground();
		this.lista_material=new Array<MaterialL>();
		this.lista=new Array<Entity>();
		this.lista_person=new Array<PersonL>();
		this.lista_material_toActor= new Array<MaterialL>();
		this.lista_person_toActor= new Array<PersonL>();
		this.money=5000;
		this.money_wasted=0;
	}
	/**
	 * Checks if the point is on the map
	 * @param posX Position X in pixels
	 * @param posY Position Y in pixels
	 * @return true if it is or false otherwise
	 */
	public boolean isInsideMap(int posX, int posY) {
		if (posX >= this.transformToBlock(this.mapWidth) || posY >= this.transformToBlock(this.mapHeight) || posX < 0
				|| posY < 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Creates recursivily the ores in the map
	 * @param quantidade Quantity to go out
	 * @param posX The posX to create
	 * @param posY The posY to create
	 */
	public void spawn(int quantidade,int posX,int posY,String type)
	{
		if (!this.isInsideMap(posX, posY) || quantidade < 1) {
			return;
		}
		else if (this.lista_background.get(posX).get(posY).getType().equals("land")) {
			BackGroundL novo = new BackGroundL(posX*Map.division,posY*Map.division,type, this.randomNumber(10000, 20000));
			this.lista_background.get(posX).set(posY, novo);
			
			boolean spam1 = true, spam2 = true, spam3 = true, spam4 = true;
			while (spam1 || spam2 || spam3 || spam4) {
				int numero = this.randomNumber(1, 4);
				if (spam1 && numero == 1) {
					this.spawn(quantidade - 1, posX + 1, posY, type);
					spam1 = false;
				} else if (spam2 && numero == 2) {
					this.spawn(quantidade - 1, posX, posY + 1, type);
					spam2 = false;
				} else if (spam3 && numero == 3) {
					this.spawn(quantidade - 1, posX - 1, posY, type);
					spam3 = false;
				} else if (spam4 && numero == 4) {
					this.spawn(quantidade - 1, posX, posY - 1, type);
					spam4 = false;
				}
			}
		}
		return;
	}
	/**
	 * Creates the background map with iron_ore , copper_ore and grass
	 */
	public void createBackground()
	{
		this.lista_background=new Array<Array<BackGroundL>>();
		for(int i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			this.lista_background.add(new Array<BackGroundL>());
			for(int j=0; j<this.transformToBlock(mapHeight);j++)
			{
				this.lista_background.get(i).add(new BackGroundL(i*Map.division,j*Map.division,"land",0));
			}
		}
		ArrayList<String> tipos=new ArrayList<String>();
		tipos.add("iron_ore");
		tipos.add("copper_ore");
		tipos.add("grass");
		for(String it:tipos)
		{
			for(int i=0;i<this.mapWidth/100-3*this.mapWidth/1000;i++)
			{
				int placeX=this.randomNumber(1, this.transformToBlock(this.mapWidth));
				int placeY=this.randomNumber(1, this.transformToBlock(this.mapHeight));
				int quantidade=this.randomNumber(5,10);
				this.spawn(quantidade, placeX, placeY, it);
			}
		}
		
	}
	/**
	 * Makes all the material and person look for actors
	 */
	public void recreateMap()
	{
		map= new Array<Array<Array<Entity>>>();
		for(int i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			map.add(new Array<Array<Entity>>());
			for(int j=0; j<this.transformToBlock(mapHeight);j++)
			{
				map.get(i).add(new Array<Entity>());
			}
		}
		this.lista_material_toActor= new Array<MaterialL>();
		this.lista_person_toActor=new Array<PersonL>();
		for(MaterialL it:this.lista_material)
		{
			this.lista_material_toActor.add(it);
		}
		for(PersonL it:this.lista_person)
		{
			this.lista_person_toActor.add(it);
		}
	}
	/**
	 * Empty constructor
	 */
	public Map()
	{
		Map.singleton=this;
	}
	/**
	 * Transform a number to this map corresponding block
	 * @param number The number I want to change, given in pixels
	 * @return The number of the corresponding block
	 */
	public int transformToBlock(int number)
	{
		return number/Map.division;
	}
	/**
	 * Gets what in a position block of the map
	 * @param posX the x position in pixels
	 * @param posY the y position in pixels
	 * @return Return an Array of entities in that position
	 */
	public Array<Entity> getMapPixel(int posX,int posY)
	{
		return this.map.get(this.transformToBlock(posX)).get(this.transformToBlock(posY));
	}
	/**
	 * Gets what in the pixel of the map
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @return Return an Array of entities in that position
	 */
	public Array<Entity> getMapPercisionPixel(int posX,int posY)
	{
		Array<Entity> elements = this.getMapPixel(posX, posY);
		Array<Entity> devolver = new Array<Entity>();
		for (Entity it : elements) {
			if (it.getPosX() <= posX && posX <= it.getPosX() + it.getWidth() && it.getPosY() <= posY
					&& posY <= it.getPosY() + it.getHeight()) {
				devolver.add(it);
			}
		}
		return devolver;
	}
	/**
	 * Gets what in a position block of the map
	 * @param posX the x position in block index
	 * @param posY the y position in block index
	 * @return Return an Array of entities in that position
	 */
	public Array<Entity> getMapBlock(int posX,int posY)
	{
		return this.map.get(posX).get(posY);
	}
	/**
	 * Adds an entity to the map. If it's a Material or person, it will be placed
	 * @param ent The entitie i want to add
	 * @return Return true if added of false otherwise
	 */
	public boolean addMap(Entity ent) {
		int x = this.transformToBlock(ent.getPosX());
		int y = this.transformToBlock(ent.getPosY());
		
		int error_x=0;
		if(ent.getWidth()%Map.division!=0) {
			error_x++;
		}
		int error_y=0;
		if(ent.getHeight()%Map.division!=0) {
			error_y++;
		}

		int quantity_x = this.transformToBlock(ent.getPosX()+ent.getWidth())-x + error_x;
		int quantity_y = this.transformToBlock(ent.getPosY()+ent.getHeight())-y +  error_y;
		if(!MaterialL.class.isAssignableFrom(ent.getClass()))
		{
			if(!this.checkPositions(ent.getPosX(), ent.getPosY(), ent.getWidth(), ent.getHeight()))
			{
				return false;
			}
		}
		if(!ent.addEntity())
		{
			return false;
		}
		
		for (int i = x; i < x + quantity_x ; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				this.map.get(i).get(j).add(ent);
			}
		}
		
		return true;
	}
	/**
	 * Remove an Entity from the map
	 * @param ent the entity i want to remove
	 */
	public void removeMap(Entity ent) 
	{
		int x = this.transformToBlock(ent.getPosX());
		int y = this.transformToBlock(ent.getPosY());
		
		int error_x=0;
		if(ent.getWidth()%Map.division!=0) {
			error_x++;
		}
		int error_y=0;
		if(ent.getHeight()%Map.division!=0) {
			error_y++;
		}

		int quantity_x = this.transformToBlock(ent.getPosX()+ent.getWidth())-x + error_x;
		int quantity_y = this.transformToBlock(ent.getPosY()+ent.getHeight())-y +  error_y;

		for (int i = x; i < x + quantity_x; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				this.map.get(i).get(j).removeValue(ent, false);
			}
		}
		ent.removeEntity();
	}

	/**
	 * Checks the between spaces to see if there is something there
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param width The width in pixels
	 * @param height The height in pixels
	 * @param ignoreMaterial True if I want to ignore the materials that are there of false otherwise
	 * @return false if there is something there or true if it's empty
	 */
	public boolean checkPositions(int posX,int posY,int width,int height)
	{
		if(posX>this.mapWidth || posX+width>this.mapWidth || posY>this.mapHeight || posY+height>this.mapHeight)
		{
			return false;
		}
		int x= this.transformToBlock(posX);
		int y= this.transformToBlock(posY);
		int error_x=0;
		if(width%Map.division!=0) {
			error_x++;
		}
		int error_y=0;
		if(height%Map.division!=0) {
			error_y++;
		}
		int quantity_x = this.transformToBlock(posX + width) - x + error_x;
		int quantity_y = this.transformToBlock(posY + height) - y + error_y;
		
		for (int i = x; i < x + quantity_x; i++) {
			for (int j = y; j < y + quantity_y; j++) {
				Array<Entity> elements = this.getMapBlock(i, j);
				for(Entity it:elements) {
					if(!MaterialL.class.isAssignableFrom(it.getClass()))
						return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return Returns the map width
	 */
	public int getMapWidth() {
		return mapWidth;
	}
	/**
	 * 
	 * @return Returns the map height
	 */
	public int getMapHeight() {
		return mapHeight;
	}

	/**
	 * Check if a point is in building.
	 * 
	 * Todo:
	 * this function should implement in placeList,
	 * but it's now using as graphic.
	 * 
	 * @param x
	 * @param y
	 * @return true|false
	 */
	public boolean pointIsOccupied(int x, int y) {
		
		return (this.getMapPercisionPixel(x, y).size > 0);
	}
	/**
	 * Gets the backgound in a point of the map
	 * @param x The X position in pixels
	 * @param y The Y position in pixels
	 * @return The background class
	 */
	public BackGroundL getBackGroundPoint(int x,int y) {
		return this.lista_background.get(this.transformToBlock(x)).get(this.transformToBlock(y));
	}
	/**
	 * 
	 * @return Retuns the map of the game
	 */
	public Array<Array<Array<Entity>>> getMap() {
		return map;
	}
	/**
	 * 
	 * @return Retuns the background list 
	 */
	public Array<Array<BackGroundL>> getLista_background() {
		return lista_background;
	}
	/**
	 * 
	 * @return Returns the list of entities
	 */
	public Array<Entity> getLista() {
		return lista;
	}
	/**
	 * 
	 * @return Returns the list of materials
	 */
	public Array<MaterialL> getLista_material() {
		return lista_material;
	}
	/**
	 * 
	 * @return Retuns the list of persons
	 */
	public Array<PersonL> getLista_person() {
		return lista_person;
	}
	/**
	 * 
	 * @return Retuns the list of materials looking for actor
	 */
	public Array<MaterialL> getLista_material_toActor() {
		return lista_material_toActor;
	}
	/**
	 * 
	 * @return Retuns the list of people looking for actor
	 */
	public Array<PersonL> getLista_person_toActor() {
		return lista_person_toActor;
	}
	/**
	 * 
	 * @return Returns the money the player has
	 */
	public int getMoney() {
		return money;
	}
	/**
	 * Sets the money the player has
	 * @param money
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	/**
	 * 
	 * @return Returns the money wasted by the player
	 */
	public int getMoney_wasted() {
		return money_wasted;
	}
	/**
	 * Sets the money wasted by the player
	 * @param money_wasted
	 */
	public void setMoney_wasted(int money_wasted) {
		this.money_wasted = money_wasted;
	}
	
	
}
