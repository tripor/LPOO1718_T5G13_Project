package graphic;

import java.util.ArrayList;

/**
 * Class that saves all the buttons of the game
 *
 */
public class IconList extends GroupExtension{
	
	private ArrayList<GroupExtension> holders;
	/**
	 * Constructor for the class IconList
	 */
	public IconList()
	{
		holders=new ArrayList<GroupExtension>();
	}
	/**
	 * Add a button to the game
	 * @param icon Actor icon
	 */
	public void addIcon(GroupExtension icon)
	{
		this.addActor(icon);
		icon.setVisible(false);
		holders.add(icon);
	}
	/**
	 * Set the icons on the screen
	 * @param number The choise for what I want to change to
	 */
	public void setHolder(int number)
	{
		this.holders.get(number-1).setVisible(true);
		for(int i=0;i<this.holders.size();i++)
		{
			if(i!=number-1)
			{
				this.holders.get(i).setVisible(false);
			}
		}
	}
	/**
	 * 
	 * @return Returns the active menu number
	 */
	public int getHolder()
	{
		for(int i=0;i<this.holders.size();i++)
		{
			if(this.holders.get(i).isVisible())
			{
				return i+1;
			}
		}
		return 0;
	}
	/**
	 * 
	 * @return Returns the active menu number
	 */
	public GroupExtension getCurrentHolder()
	{
		for(GroupExtension it:this.holders)
		{
			if(it.isVisible())
			{
				return it;
			}
		}
		return null;
	}
}
