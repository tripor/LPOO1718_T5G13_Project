package logic.storage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import icon.Icon;
/**
 * Class that saves all the buttons of the game
 *
 */
public class IconList extends Group{
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
		icon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("clicked");
            }
        });
		this.addActor(icon);
	}
}
