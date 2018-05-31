package icon;

import graphic.GroupExtension;
import graphic.MenuStage;
import icon.type.BackGroundIcon;
import icon.type.ExitIcon;
import icon.type.PlayIcon;

public class InicialHolder extends GroupExtension {
	
	public InicialHolder(int width,int height)
	{
		int width_of_icons=57*MenuStage.singleton.VIEWPORT_WIDTH/200;
		int height_of_icons=10*MenuStage.singleton.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/2.8);
		int pos_y=(int) (height/3.2);
		
		BackGroundIcon base=new BackGroundIcon(0,0,width,height);
		this.addActor(base);
		
		ExitIcon button2= new ExitIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		this.addActor(button2);
		pos_y+=height_of_icons+2;

		PlayIcon button= new PlayIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		this.addActor(button);
		pos_y+=height_of_icons+2;
	}

	@Override
	public void loadFromMap() {
		// TODO Auto-generated method stub
		
	}
}
