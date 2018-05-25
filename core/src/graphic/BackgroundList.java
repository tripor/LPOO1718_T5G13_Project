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
	
	public void addBackground(Background back)
	{
		this.addActor(back);
	}

}
