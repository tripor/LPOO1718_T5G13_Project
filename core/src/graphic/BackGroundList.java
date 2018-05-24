package graphic;

import graphic.enteties.Background;

/**
 * Class that handles the ground background
 *
 */
public class BackGroundList extends GroupExtension{
	/**
	 * Constructor for the class BackGroundList
	 */
	public BackGroundList()
	{
		
	}
	
	public void addBackground(Background back)
	{
		this.addActor(back);
	}

}
