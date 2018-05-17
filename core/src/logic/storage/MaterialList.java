package logic.storage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.groundup.game.GameStage;

import material.Material;

public class MaterialList extends Group {
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

	public void addMaterial(Material back) {
		this.addActor(back);
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

	}

}
