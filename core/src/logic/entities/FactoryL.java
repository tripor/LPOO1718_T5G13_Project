package logic.entities;

import java.util.ArrayList;

import logic.Map;
import logic.Place;
import logic.Recipe;
/**
 * Class that handle the logic of the Factory
 *
 */
public class FactoryL extends Place {
	/**
	 * Price of the factory
	 */
	public static int price=200;
	/**
	 * Width of the factory
	 */
	public static int width=40;
	/**
	 * Height of the factory
	 */
	public static int height=40;
	/**
	 * Recipies of this factories
	 */
	private Recipe receita;
	/**
	 * Constructor of the class Factory Logic with width 40 and height 40
	 * @param posX The X position in pixels
	 * @param posY The Y position in pixels
	 * @param doorAtBorder The place of the door.1-top,2-right,3-bottom,4-left
	 */
	public FactoryL(int posX,int posY,int doorAtBorder)
	{
		super(posX,posY,FactoryL.width,FactoryL.height,doorAtBorder);
		receita=new Recipe();
	}
	/**
	 * Empty constructor
	 */
	public FactoryL()
	{
		super();
	}
	/**
	 * Returns the index of the selected recipe
	 * @return
	 */
	public int getSelectedRecipe() {
		return this.receita.selectedRecipie();
	}
	/**
	 * Selectes a recipe
	 * @param index The index of the recipe
	 */
	public void selectRecipe(int index) {
		this.getInternalStorage().clear();
		this.receita.selectRecipie(index);
	}
	/**
	 * Transforms a material to a new one, according to the recipe 
	 * @return The material converted or null if not possible
	 */
	public MaterialL transform() {
		ArrayList<MaterialL> consumir= new ArrayList<MaterialL>();
		MaterialL devolver=null;
		for(String it:this.receita.getConsumir())
		{
			for(MaterialL mat:this.getInternalStorage()) {
				if(mat.getType().equals(it)) {
					consumir.add(mat);
					devolver=mat;
					this.getInternalStorage().remove(mat);
					break;
				}
			}
		}
		if(consumir.isEmpty() || consumir.size()<this.receita.getConsumir().size())
		{
			for(MaterialL it:consumir)
			{
				this.getInternalStorage().add(it);
			}
			return null;
		}
		devolver.setType(this.receita.getProduct());
		return devolver;
	}

	@Override
	public float handler() {
		time++;
		if (time >= this.receita.timeToBuild()) {
			time = 0;
			if (this.getInternalStorage().isEmpty())
				time = this.receita.timeToBuild();
			else {
				MaterialL adicionar = this.transform();
				if (adicionar != null)
					this.addToExternalStorage(adicionar);
			}
		}
		return 0;
	}

	@Override
	public int getPrice() {
		return FactoryL.price;
	}

	@Override
	public boolean receiveMaterial(MaterialL mat) {
		if (this.receita.canReceive(mat.getType())) {
			this.addToStorage(mat);
			Map.singleton.removeMap(mat);
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return This factory recipes
	 */
	public Recipe getReceita() {
		return receita;
	}
	
	
	

}
