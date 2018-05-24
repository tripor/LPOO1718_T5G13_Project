package logic;

import java.util.ArrayList;

import logic.enteties.ConveyorL;
import logic.enteties.InserterL;
import logic.enteties.MaterialL;


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
	 * The blocks with all the enteties
	 */
	private ArrayList<ArrayList<ArrayList<Entetie>>> map;
	/**
	 * ArrayList with all the materials in the map
	 */
	public ArrayList<MaterialL> lista_material;
	/**
	 * ArrayList with all the inserter in the map
	 */
	public  ArrayList<InserterL> lista_inserter;
	/**
	 * ArrayList with all the Places in the map
	 */
	public  ArrayList<Place> lista_place;
	/**
	 * ArrayList with all the Conveyors in the map
	 */
	public  ArrayList<ConveyorL> lista_conveyor;
	
	/**
	 * Constructor for the class logic Map
	 * 
	 * @param width
	 * @param height
	 */
	public Map(int width, int height) {
		this.mapWidth = width;
		this.mapHeight = height;
		map= new ArrayList<ArrayList<ArrayList<Entetie>>>();
		for(int i=0 ; i<this.transformToBlock(mapWidth) ;i++)
		{
			map.add(new ArrayList<ArrayList<Entetie>>());
			for(int j=0; j<this.transformToBlock(mapHeight);j++)
			{
				map.get(i).add(new ArrayList<Entetie>());
			}
		}
		this.lista_conveyor = new ArrayList<ConveyorL>();
		this.lista_inserter= new ArrayList<InserterL>();
		this.lista_material=new ArrayList<MaterialL>();
		this.lista_place=new ArrayList<Place>();
	}
	/**
	 * Transform a number to this map curresponding block
	 * @param number The number I want to change, given in pixels
	 * @return The number of the curresponding block
	 */
	public int transformToBlock(int number)
	{
		return number/Map.division;
	}
	/**
	 * Gets what in a position block of the map
	 * @param posX the x position in pixels
	 * @param posY the y position in pixels
	 * @return Return an arraylist of entities in that position
	 */
	public ArrayList<Entetie> getMapPixel(int posX,int posY)
	{
		return this.map.get(this.transformToBlock(posX)).get(this.transformToBlock(posY));
	}
	/**
	 * Gets what in the pixel of the map
	 * @param pos_x the x position in pixels
	 * @param pos_y the y position in pixels
	 * @return Return an arraylist of entities in that position
	 */
	public ArrayList<Entetie> getMapPercisionPixel(int posX,int posY)
	{
		ArrayList<Entetie> elements = this.getMapPixel(posX, posY);
		ArrayList<Entetie> devolver = new ArrayList<Entetie>();
		for (Entetie it : elements) {
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
	 * @return Return an arraylist of entities in that position
	 */
	public ArrayList<Entetie> getMapBlock(int posX,int posY)
	{
		return this.map.get(posX).get(posY);
	}
	/**
	 * Adds an entetie to the map. If it's a Material, the material will be placed
	 * @param ent The entitie i want to add
	 * @return Return true if added of false otherwise
	 */
	public boolean addMap(Entetie ent) {
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
		
		if(!MaterialL.class.isAssignableFrom(ent.getClass())) {
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
		
		for (int i = x; i < x + quantity_x ; i ++) {
			for (int j = y; j < y + quantity_y; j ++) {
				this.map.get(i).get(j).add(ent);
			}
		}
		
		this.addEntetieLista(ent);
		return true;
	}
	/**
	 * Remove an Entetie from the map
	 * @param ent the entetie i want to remove
	 */
	public void removeMap(Entetie ent) 
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
				this.map.get(i).get(j).remove(ent);
			}
		}
		this.removeEntetieLista(ent);
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
				ArrayList<Entetie> elements= this.getMapBlock(i, j);
				if(ignoreMaterial)
				{
					for(Entetie it:elements)
					{
						if(!MaterialL.class.isAssignableFrom(it.getClass()))
						{
							return true;
						}
					}
				}
				else
				{
					if(elements.size()!=0) return true;
				}
			}
		}
		return false;
	}
	/**
	 * Adds the entetie to the correct lista
	 * @param ent The entetie I want to add
	 */
	private void addEntetieLista(Entetie ent)
	{
		if(ConveyorL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_conveyor.add((ConveyorL) ent);
		}
		else if(Place.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_place.add((Place) ent);
		}
		else if(InserterL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_inserter.add((InserterL) ent);
		}
		else if(MaterialL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_material.add((MaterialL) ent);
		}
	}
	
	/**
	 * Removes the entetie to the correct lista
	 * @param ent The entetie I want to remove
	 */
	private void removeEntetieLista(Entetie ent)
	{
		if(ConveyorL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_conveyor.remove((ConveyorL) ent);
		}
		else if(Place.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_place.remove((Place) ent);
		}
		else if(InserterL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_inserter.remove((InserterL) ent);
		}
		else if(MaterialL.class.isAssignableFrom(ent.getClass()))
		{
			this.lista_material.remove((MaterialL) ent);
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
	
}
