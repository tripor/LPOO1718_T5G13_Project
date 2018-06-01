package graphic;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.entities.MaterialG;
import logic.entities.MaterialL;


public class MaterialList extends GroupExtension {

	/**
	 * Constructor for the class MaterialList
	 */
	public MaterialList() {
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
		if(GameStage.singleton.map.addMap(back.instance))
		{	
			this.addActor(back);
			return true;
		}
		return false;
	}
	/**
	 * Removes the material from the group
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
		ArrayList<MaterialL> elements= new ArrayList<MaterialL>();
		for(MaterialL it:GameStage.singleton.map.getList_material())
		{
			elements.add(it);
		}
		GameStage.singleton.map.getList_material().clear();
		for(MaterialL it:elements)
		{
			MaterialG novo= new MaterialG(it);
			this.addMaterialMap(novo);
		}
	}
	/**
	 * Removes the material from the map and the group
	 * @param it The material I want to remove
	 */
	public void removeMaterialAll(MaterialL p) {
		for(Actor it:this.getChildren())
		{
			if(((ActorExtension) it).getInstance()==p)
			{
				this.removeMaterial((MaterialG) it);
				GameStage.singleton.map.removeMap(p);
			}
		}
	}

}
