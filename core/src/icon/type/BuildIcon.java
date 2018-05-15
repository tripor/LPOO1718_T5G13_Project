package icon.type;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
/**
 * Class Build Icon
 *
 */
public class BuildIcon extends Icon{
	
	private boolean openned;
	
	private ArrayList<Icon> icon_build =  new ArrayList<Icon>();
	
	private void clickHandler()
	{
		if(this.openned == false)
		{
			for(int i=0; i<this.icon_build.size();i++)
			{
				this.icon_build.get(i).setVisible(true);
			}
			this.openned=true;
		}	
		else
		{
			for(int i=0; i<this.icon_build.size();i++)
			{
				this.icon_build.get(i).setVisible(false);
			}
			this.openned=false;
		}
	}

	private void createBuildIcon() {
		
		Texture texture = this.game.getGame().getAssetManager().get("build_icon.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		int pos_x=7+8;
		int pos_y=7;
		
		FactoryIcon button1= new FactoryIcon(this.game,pos_x,pos_y,7,7);
		button1.setVisible(false);
		this.game.getIcon_list().addIcon(button1);
		this.icon_build.add(button1);
		pos_x+=8;
	}
	/**
	 * Constructor for the class BuildIcon
	 * @param game the game it belongs to
	 * @param posX the posX in pixels
	 * @param posY the posY in pixels
	 * @param width the width of the image in pixels
	 * @param height the height of the image in pixels
	 */
	public BuildIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.openned=false;
		this.setWidth(width);
		this.setHeight(height);
		this.createBuildIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	clickHandler();
            }
        });
	}

}
