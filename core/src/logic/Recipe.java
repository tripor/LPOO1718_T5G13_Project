package logic;

import java.util.ArrayList;

/**
 * Class that has all the recipies on the game
 *
 */
public class Recipe {
	/**
	 * Array with all the recipes
	 */
	private transient ArrayList<ArrayList<String>> recipe;
	/**
	 * The selected Recipe
	 */
	private int selectedRecipie;
	/**
	 * Constructor for the class Recipe
	 */
	public Recipe()
	{
		recipe= new ArrayList<ArrayList<String>>();
		recipe.add(new ArrayList<String>());
		recipe.get(0).add("copper_plate");
		recipe.get(0).add("copper_cable");
		recipe.add(new ArrayList<String>());
		recipe.get(1).add("iron_plate");
		recipe.get(1).add("copper_cable");
		recipe.get(1).add("copper_cable");
		recipe.get(1).add("electronic_circuit");
		
	}
	/**
	 * 
	 * @return Return a string with all the elements of the selected recipe
	 */
	public ArrayList<String> getSelectedRecipe(){
		return this.recipe.get(selectedRecipie);
	}
	/**
	 * Selects a recepie
	 * @param index index of the recipe
	 */
	public void selectRecipie(int index) {
		this.selectedRecipie=index;
	}
	/**
	 * 
	 * @return Retuns the index of the selected recipe
	 */
	public int selectedRecipie() {
		return this.selectedRecipie;
	}
	/**
	 * 
	 * @return Returns the total number of recipes
	 */
	public int totalNumber() {
		return this.recipe.size();
	}
	/**
	 * Formats a String to be user friendly
	 * @param index The index of the recipe I want the string
	 * @return The string formated
	 */
	public String getRecipeString(int index) {
		String devolver = "";
		for (int i = 0; i < this.recipe.get(index).size() - 2; i++) {
			devolver = devolver + this.recipe.get(index).get(i) + " + ";
		}
		devolver = devolver + this.recipe.get(index).get(this.recipe.get(index).size() - 2) + " = "
				+ this.recipe.get(index).get(this.recipe.get(index).size() - 1);
		return devolver;
	}
	/**
	 * Checks it can receive this type of material acording to the recipe
	 * @param type String with the type of the material
	 * @return True if possible of false otherwise
	 */
	public boolean canReceive(String type) {
		for(int i=0;i<this.recipe.get(selectedRecipie).size()-1;i++)
		{
			if(this.recipe.get(selectedRecipie).get(i).equals(type))
				return true;
		}
		return false;
	}
	/**
	 * Retuns a array of string with all the type of materials to consume
	 * @return The array
	 */
	public ArrayList<String> getConsumir() {
		ArrayList<String> devolver=new ArrayList<String>();
		for(int i=0;i< this.recipe.get(selectedRecipie).size()-1;i++) {
			devolver.add(this.recipe.get(selectedRecipie).get(i));
		}
		return devolver;
	}
	/**
	 * String with the type of product from the selected recipe
	 * @return String with the name
	 */
	public String getProduct() {
		return this.recipe.get(selectedRecipie).get(this.recipe.get(selectedRecipie).size()-1);
	}

}
