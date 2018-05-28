package graphic;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.MaterialG;
import logic.Entity;
import logic.entities.ConveyorL;
import logic.entities.MaterialL;


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
		//this.game.map().removeMap(mat.instance);
	}
	/**
	 * Removes a inserter from the map and game
	 * @param p the inserter I want to remove
	 */
	public void removeMaterial(MaterialL p) {
		for(Actor it:this.getChildren())
		{
			if(((ActorExtension) it).getInstance()==p)
			{
				this.removeMaterial((MaterialG) it);
			}
		}
	}
	/**
	 * Loads all the Materials to the screen
	 */
	public void loadFromMap()
	{
		ArrayList<MaterialG> add= new ArrayList<MaterialG>();
		for(MaterialL it:this.game.map.lista_material)
		{
			for(Entity et:this.game.map.getMapPixel(it.getPosX(), it.getPosY()))
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
