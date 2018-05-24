package graphic;

import icon.Icon;
/**
 * Class that saves all the buttons of the game
 *
 */
public class IconList extends GroupExtension{
	/**
	 * Constructor for the class IconList
	 */
	public IconList()
	{
		
	}
	/**
	 * Add a button to the game
	 * @param icon Actor icon
	 */
	public void addIcon(Icon icon)
	{
		this.addActor(icon);
		this.getChildren().sort();
	}
}
