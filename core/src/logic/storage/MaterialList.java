package logic.storage;

import java.util.ArrayList;

import com.groundup.game.GameStage;

import graphic.GroupExtension;
import material.Material;

public class MaterialList extends GroupExtension {
	/**
	 * The game that this group is in
	 */
	protected GameStage game;
	
	private ArrayList<Material> lista;

	/**
	 * Constructor for the class MaterialList
	 */
	public MaterialList(GameStage game) {
		this.game = game;
		this.lista= new ArrayList<Material>();
	}

	public void addMaterial(Material back) {
		this.addActor(back);
		this.lista.add(back);
	}

	public void addMaterialToMap(Material back) {
		this.game.map().addMap(back, (int) back.getX(), (int) back.getY(), (int) back.getWidth(),
				(int) back.getHeight());
	}

	public void removeMaterialFromMap(Material mat) {
		this.game.map().removeMap(mat, (int) mat.getX(), (int) mat.getY(), (int) mat.getWidth(), (int) mat.getHeight());

	}

	public void removeMaterial(Material mat) {
		this.removeActor(mat);
		this.lista.remove(mat);

	}

	public ArrayList<Material> getLista() {
		return lista;
	}
	
	public void reAdd()
	{
		for(Material it:this.lista)
		{
			this.addActor(it);
		}
	}

}
