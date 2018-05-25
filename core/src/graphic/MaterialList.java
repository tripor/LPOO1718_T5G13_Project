package graphic;

import java.util.ArrayList;

import graphic.enteties.MaterialG;
import logic.Entetie;
import logic.enteties.ConveyorL;
import logic.enteties.MaterialL;


public class MaterialList extends GroupExtension {
	/**
	 * The game that this group is in
	 */
	protected GameStage game;

	/**
	 * Constructor for the class MaterialList
	 */
	public MaterialList(GameStage game) {
		this.game = game;
	}
	/**
	 * Adds the material to the group and to the map
	 * @param back The material I wan to add
	 * @return True if it add, false otherwise
	 */
	public boolean addMaterial(MaterialG back) {
		this.addActor(back);
		return true;
	}
	public boolean addMaterialMap(MaterialG back) {
		if(this.game.map.addMap(back.instance))
		{	
			this.addActor(back);
			return true;
		}
		return false;
	}
	/**
	 * Removes the material from the map and the group
	 * @param mat The material I want to remove
	 */
	public void removeMaterial(MaterialG mat) {
		this.removeActor(mat);
	}
	/**
	 * Loads all the Materials to the screen
	 */
	public void loadFromMap()
	{
		ArrayList<MaterialG> add= new ArrayList<MaterialG>();
		for(MaterialL it:this.game.map.lista_material)
		{
			for(Entetie et:this.game.map.getMapPixel(it.getPosX(), it.getPosY()))
			{
				if(ConveyorL.class.isAssignableFrom(et.getClass()))
				{					
					MaterialG novo=new MaterialG(this.game,it);
					add.add(novo);
				}
			}
		}
		for(MaterialG it:add)
		{
			this.addMaterialMap(it);
		}
	}

}
