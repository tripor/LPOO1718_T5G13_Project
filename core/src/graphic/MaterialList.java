package graphic;


import graphic.enteties.MaterialG;


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
	/**
	 * Removes the material from the map and the group
	 * @param mat The material I want to remove
	 */
	public void removeMaterial(MaterialG mat) {
		this.removeActor(mat);
	}

}
