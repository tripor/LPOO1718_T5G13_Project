package graphic;

import graphic.entities.BackgroundG;

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
	public void addBackground(BackgroundG back)
	{
		this.addActor(back);
		
	}
	@Override
	public void loadFromMap() {
		// TODO Auto-generated method stub
		
	}

}
