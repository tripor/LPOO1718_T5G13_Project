package icon.type;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.groundup.game.GameStage;
import icon.Icon;
/**
 * Class Build Icon
 *
 */
public class BuildIcon extends Icon{
	
	private ArrayList<Icon> icon_build =  new ArrayList<Icon>();
	/**
	 * Closes/opens the options on the left
	 * @param choise true for close, false otherwise
	 */
	public void closeOpen(boolean choise)
	{
		if(choise == false)
		{
			for(int i=0; i<this.icon_build.size();i++)
			{
				this.icon_build.get(i).setVisible(true);
			}
		}	
		else
		{
			for(int i=0; i<this.icon_build.size();i++)
			{
				this.icon_build.get(i).setVisible(false);
			}
		}
	}

	private void createBuildIcon() {
		
		Texture[] frames=new Texture[7];
		
		frames[0] = this.game.getGame().getAssetManager().get("barra1.png");
		frames[1] = this.game.getGame().getAssetManager().get("barra2.png");
		frames[2] = this.game.getGame().getAssetManager().get("barra3.png");
		frames[3] = this.game.getGame().getAssetManager().get("barra4.png");
		frames[4] = this.game.getGame().getAssetManager().get("barra5.png");
		frames[5] = this.game.getGame().getAssetManager().get("barra6.png");
		frames[6] = this.game.getGame().getAssetManager().get("barra7.png");
		
		this.animation= new Animation<Texture>(.25f,frames);
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		
		int width_of_icons=20*this.game.VIEWPORT_WIDTH/200;
		int height_of_icons=3*this.game.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (this.getWidth()/40);
		int pos_y=(int) (this.getHeight()/2.3);
		
		PlaceIcon button1= new PlaceIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button1.setZ(10);
		this.game.icons().addIcon(button1);
		this.icon_build.add(button1);
		//pos_x+=width_of_icons+1;
		pos_y-=height_of_icons+1;
		
		ConveyorIcon button3= new ConveyorIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setZ(10);
		this.game.icons().addIcon(button3);
		this.icon_build.add(button3);
		//pos_x+=width_of_icons+1;
		pos_y-=height_of_icons+1;
		
		InserterIcon button4= new InserterIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button4.setZ(10);
		this.game.icons().addIcon(button4);
		this.icon_build.add(button4);
		//pos_x+=width_of_icons+1;
		pos_y-=height_of_icons+1;
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
		this.setWidth(width);
		this.setHeight(height);
		this.createBuildIcon();
		this.setPosition(posX, posY);
		
		/*this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	clickHandler();
            }
        });*/
	}

	@Override
	public void update(float delta) {
		//System.out.println(delta);
		this.stateTime+=delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
		
		
	}

}
