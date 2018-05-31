package icon;

import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.MenuBackgroundIcon;
import icon.type.MenuExitIcon;
import icon.type.MenuLoadIcon;
import icon.type.MenuResumeIcon;
import icon.type.MenuSaveIcon;

public class MenuHolder extends GroupExtension {
	
	public MenuHolder(int width,int height)
	{
		int width_of_icons=57*GameStage.singleton.VIEWPORT_WIDTH/200;
		int height_of_icons=10*GameStage.singleton.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/2.8);
		int pos_y=(int) (height/3.2);
		
		MenuBackgroundIcon base=new MenuBackgroundIcon(width/3,(int) (height/3.5),width/3,height/2);
		base.setZ(9);
		this.addActor(base);
		
		MenuExitIcon button= new MenuExitIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button.setZ(10);
		this.addActor(button);
		pos_y+=height_of_icons+2;
		
		MenuLoadIcon button2= new MenuLoadIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button2.setZ(10);
		this.addActor(button2);
		pos_y+=height_of_icons+2;
		
		MenuSaveIcon button3= new MenuSaveIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setZ(10);
		this.addActor(button3);
		pos_y+=height_of_icons+2;
		
		MenuResumeIcon button4= new MenuResumeIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button4.setZ(10);
		this.addActor(button4);
		pos_y+=height_of_icons+2;
		
		this.getChildren().sort();
	}

	@Override
	public void loadFromMap() {
		
	}

}
