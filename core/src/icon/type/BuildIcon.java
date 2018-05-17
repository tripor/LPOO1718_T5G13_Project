package icon.type;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.groundup.game.GameStage;
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
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		
		int pos_x=7+8;
		int pos_y=7;
		
		FactoryIcon button1= new FactoryIcon(this.game,pos_x,pos_y,7,7);
		button1.setVisible(false);
		this.game.icons().addIcon(button1);
		this.icon_build.add(button1);
		pos_x+=8;
		
		MineIcon button2= new MineIcon(this.game,pos_x,pos_y,7,7);
		button2.setVisible(false);
		this.game.icons().addIcon(button2);
		this.icon_build.add(button2);
		pos_x+=8;
		
		ConveyorIcon button3= new ConveyorIcon(this.game,pos_x,pos_y,7,7);
		button3.setVisible(false);
		this.game.icons().addIcon(button3);
		this.icon_build.add(button3);
		pos_x+=8;
		
		InserterIcon button4= new InserterIcon(this.game,pos_x,pos_y,7,7);
		button4.setVisible(false);
		this.game.icons().addIcon(button4);
		this.icon_build.add(button4);
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

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
