package logic.storage;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Group;

import graphic.Background;

/**
 * Class that handles the ground background
 *
 */
public class BackGroundList extends Group{
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
