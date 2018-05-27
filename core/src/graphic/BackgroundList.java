package graphic;

import graphic.entities.Background;

/**
 * Class that handles the ground background
 *
 */
public class BackgroundList extends GroupExtension{
	/**
	 * Constructor for the class BackGroundList
	 */
	public BackgroundList()
	{
		
	}
	/**
	 * Adds a actor to the background holder
	 * @param back The background I want to add
	 */
	public void addBackground(Background back)
	{
		this.addActor(back);
	}

}
