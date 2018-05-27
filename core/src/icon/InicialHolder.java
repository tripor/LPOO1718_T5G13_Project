package icon;

import graphic.GroupExtension;
import graphic.MenuStage;
import icon.type.BackGroundIcon;
import icon.type.ExitIcon;
import icon.type.PlayIcon;

public class InicialHolder extends GroupExtension {
	
	public InicialHolder(MenuStage game,int width,int height)
	{
		int width_of_icons=57*game.VIEWPORT_WIDTH/200;
		int height_of_icons=10*game.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/2.8);
		int pos_y=(int) (height/3.2);
		
		BackGroundIcon base=new BackGroundIcon(game,0,0,width,height);
		this.addActor(base);
		
		ExitIcon button2= new ExitIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		this.addActor(button2);
		pos_y+=height_of_icons+2;

		PlayIcon button= new PlayIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		this.addActor(button);
		pos_y+=height_of_icons+2;
	}
}
