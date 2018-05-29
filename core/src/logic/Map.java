package logic;


import java.util.Random;

import com.badlogic.gdx.utils.Array;

import logic.entities.BackGroundL;
import logic.entities.ConveyorL;
import logic.entities.FactoryL;
import logic.entities.HouseL;
import logic.entities.InserterL;
import logic.entities.MaterialL;
import logic.entities.MineL;
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
	 * Array with all the materials in the map
	 */
	public Array<MaterialL> lista_material;
	/**
	 * Array with all the inserter in the map
	 */
	public  Array<InserterL> lista_inserter;
	/**
	 * Array with all the Places in the map
	 */
	public  Array<Place> lista_place;
	/**
	 * Array with all the Conveyors in the map
	 */
	public  Array<ConveyorL> lista_conveyor;
	/**
	 * Array with all the Persons in the map
	 */
	public Array<PersonL> lista_person;
	/**
	 * Materials looking for an actor
	 */
	public transient Array<MaterialL> lista_material_toActor;
	/**
	 * Money the player has
	 */
	public int money;
	/**
	 * Money the player has wasted all game
	 */
	public int money_wasted;
	/**
	 * Array with all the background of the game
	 */
	public Array<Array<BackGroundL>> lista_background;
	/**
	 * Gets a random number
	 * @param min The min number
	 * @param max The max number
	 * @return The random number created
	 */
	private int randomNumber(int min,int max)
	{
		Random rand = new Random();
		int  n = rand.nextInt(max) + min;
		return n;
	}
	
	/**
	 * Constructor for the class logic Map
	 * 
	 * @param width
	 */
	public Map(int width) {
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
		this.lista_conveyor = new Array<ConveyorL>();
		this.lista_inserter= new Array<InserterL>();
		this.lista_material=new Array<MaterialL>();
		this.lista_place=new Array<Place>();
		this.lista_person=new Array<PersonL>();
		this.lista_material_toActor= new Array<MaterialL>();
		this.money=5000;
		this.money_wasted=0;
	}
	/**
	 * Creates recursivily the ores in the map
	 * @param quantidade Quantity to go out
	 * @param posX The posX to create
	 * @param posY The posY to create
	 */
	private void spawn(int quantidade,int posX,int posY,String type)
	{
		if (posX >= this.transformToBlock(this.mapWidth) || posY >= this.transformToBlock(this.mapHeight) || posX < 0
				|| posY < 0 || quantidade <= 0) {
			return;
		}
		else if (this.lista_background.get(posX).get(posY).getType().equals("land")) {
			BackGroundL novo = new BackGroundL(type, this.randomNumber(10000, 20000));
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
	private void createBackground()
	{
		this.lista_background=new Array<Array<BackGroundL>>();
		for(int i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			this.lista_background.add(new Array<BackGroundL>());
			for(int j=0; j<this.transformToBlock(mapHeight);j++)
			{
				this.lista_background.get(i).add(new BackGroundL("land",0));
			}
		}
		for(int i=0;i<this.mapWidth/100-3*this.mapWidth/1000;i++)
		{
			int placeX=this.randomNumber(1, this.transformToBlock(this.mapWidth));
			int placeY=this.randomNumber(1, this.transformToBlock(this.mapHeight));
			int quantidade=this.randomNumber(5,10);
			String type="iron_ore";
			this.spawn(quantidade, placeX, placeY, type);
		}
		for(int i=0;i<this.mapWidth/100-3*this.mapWidth/1000;i++)
		{
			int placeX=this.randomNumber(1, this.transformToBlock(this.mapWidth));
			int placeY=this.randomNumber(1, this.transformToBlock(this.mapHeight));
			int quantidade=this.randomNumber(5,10);
			String type="copper_ore";
			this.spawn(quantidade, placeX, placeY, type);
		}
		for(int i=0;i<this.mapWidth/100-5*this.mapWidth/1000;i++)
		{
			int placeX=this.randomNumber(1, this.transformToBlock(this.mapWidth));
			int placeY=this.randomNumber(1, this.transformToBlock(this.mapHeight));
			int quantidade=this.randomNumber(20,30);
			String type="grass";
			this.spawn(quantidade, placeX, placeY, type);
		}
		
	}
	/**
	 * Recreates the map
	 */
	public int recreateMap()
	{
		int i=0, j=0;
		map = new Array<Array<Array<Entity>>>();
		
		for(i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			map.add(new Array<Array<Entity>>());
			for(j=0; j<this.transformToBlock(mapHeight);j++)
			{
				map.get(i).add(new Array<Entity>());
			}
		}
		this.lista_material.clear();
		this.lista_material_toActor= new Array<MaterialL>();
		return (i * j);
	}
	
	public Map()
	{
		
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
		
		if(!(MaterialL.class.isAssignableFrom(ent.getClass()) || PersonL.class.isAssignableFrom(ent.getClass()))) {
			if(Place.class.isAssignableFrom(ent.getClass()) || ConveyorL.class.isAssignableFrom(ent.getClass()) || InserterL.class.isAssignableFrom(ent.getClass()))
			{
				if(this.checkPositions(ent.getPosX(), ent.getPosY(), ent.getWidth(), ent.getHeight(), true))
				{
					return false;
				}
			}
			else
			{
				if(this.checkPositions(ent.getPosX(), ent.getPosY(), ent.getWidth(), ent.getHeight(), false))
				{
					return false;
				}
			}
		}
		if(!this.addEntityLista(ent))
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
		this.removeEntityLista(ent);
	}

	/**
	 * Checks the between spaces to see if there is something there
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param width The width in pixels
	 * @param height The height in pixels
	 * @param ignoreMaterial True if I want to ignore the materials that are there of false otherwise
	 * @return True if there is something there of false if it's empty
	 */
	private boolean checkPositions(int posX,int posY,int width,int height,boolean ignoreMaterial)
	{
		if(posX>this.mapWidth || posX+width>this.mapWidth || posY>this.mapHeight || posY+height>this.mapHeight)
		{
			return true;
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
		int quantity_x = this.transformToBlock(posX+width)-x + error_x;
		int quantity_y = this.transformToBlock(posY+height)-y +  error_y;
		for (int i = x; i < x + quantity_x ; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				Array<Entity> elements= this.getMapBlock(i, j);
				if(ignoreMaterial)
				{
					for(Entity it:elements)
					{
						if(!MaterialL.class.isAssignableFrom(it.getClass()))
						{
							return true;
						}
					}
				}
				else
				{
					if(elements.size!=0) return true;
				}
			}
		}
		return false;
	}
	/**
	 * Adds the entity to the correct lista
	 * @param ent The entity I want to add
	 * @return true if possible to add or false otherwise
	 */
	public boolean addEntityLista(Entity ent)
	{
		if(ConveyorL.class.isAssignableFrom(ent.getClass()))
		{
			if(this.money>=ConveyorL.price)
			{
				this.money-=ConveyorL.price;
				this.money_wasted+=ConveyorL.price;
				this.lista_conveyor.add((ConveyorL) ent);
			}
			else
				return false;
			
		}
		else if(Place.class.isAssignableFrom(ent.getClass()))
		{
			if(FactoryL.class.isAssignableFrom(ent.getClass()) && this.money>=FactoryL.price)
			{
					this.money-=FactoryL.price;
					this.money_wasted+=FactoryL.price;
					this.lista_place.add((Place) ent);
			}
			else if(HouseL.class.isAssignableFrom(ent.getClass()) && this.money>=HouseL.price)
			{
				this.money-=HouseL.price;
				this.money_wasted+=HouseL.price;
				this.lista_place.add((Place) ent);
			}
			else if(MineL.class.isAssignableFrom(ent.getClass()) && this.money>=MineL.price)
			{
				this.money-=MineL.price;
				this.money_wasted+=MineL.price;
				this.lista_place.add((Place) ent);
			}
			else 
				return false;
		}
		else if(InserterL.class.isAssignableFrom(ent.getClass()))
		{
			if(this.money>=InserterL.price)
			{
				this.money-=InserterL.price;
				this.money_wasted+=InserterL.price;
				this.lista_inserter.add((InserterL) ent);
			}
			else
				return false;
		}
		else if(MaterialL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_material.add((MaterialL) ent);
		}
		else if(PersonL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_person.add((PersonL)ent);
		}
		return true;
	}
	
	/**
	 * Removes the entity to the correct lista
	 * @param ent The entity I want to remove
	 */
	private void removeEntityLista(Entity ent)
	{
		if(ConveyorL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_conveyor.removeValue((ConveyorL) ent,false);
		}
		else if(Place.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_place.removeValue((Place) ent,false);
		}
		else if(InserterL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_inserter.removeValue((InserterL) ent,false);
		}
		else if(MaterialL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_material.removeValue((MaterialL) ent,false);
		}
		else if(PersonL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_person.removeValue((PersonL)ent,false);
		}
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
}
