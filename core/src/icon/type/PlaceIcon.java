package icon.type;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class PlaceIcon extends Icon {
	
	private Texture[] frames=new Texture[3];
	private boolean mouse_over=false;
	
	private boolean openned=false;

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
	
	private void createFactoryIcon() {
		
		frames[0] = this.game.getGame().getAssetManager().get("place_button_unpressed.png");
		frames[1] = this.game.getGame().getAssetManager().get("place_button_pressed.png");
		frames[2] = this.game.getGame().getAssetManager().get("place_button_mouse_over.png");
		
		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		
		
		int width_of_icons=20*this.game.VIEWPORT_WIDTH/200;
		int height_of_icons=16*this.game.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (this.game.VIEWPORT_WIDTH/4.5);
		int pos_y=(int) (this.game.VIEWPORT_HEIGHT/60);
		
		FactoryIcon button1= new FactoryIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button1.setVisible(false);
		button1.setZ(10);
		this.game.icons().addIcon(button1);
		this.icon_build.add(button1);
		pos_x+=width_of_icons+1;
		//pos_y-=height_of_icons+1;
		
		MineIcon button2= new MineIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button2.setVisible(false);
		button2.setZ(10);
		this.game.icons().addIcon(button2);
		this.icon_build.add(button2);
		pos_x+=width_of_icons+1;
		//pos_y-=height_of_icons+1;
		
		HouseIcon button3= new HouseIcon(this.game,pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setVisible(false);
		button3.setZ(10);
		this.game.icons().addIcon(button3);
		this.icon_build.add(button3);
		pos_x+=width_of_icons+1;
		//pos_y-=height_of_icons+1;
	}
	
	private void newSprite(int number)
	{
		sprite.setTexture(frames[number]);
	}
	
	public PlaceIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	clickHandler();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	mouse_over=true;
            	if(!openned)
            		newSprite(2);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	mouse_over=false;
            	if(!openned)
            		newSprite(0);
            }
        });
	}

	@Override
	public void update(float delta) {
		if(this.openned)
    		newSprite(1);
		else if(!this.mouse_over)
    		newSprite(0);
			
	}
	
	

}