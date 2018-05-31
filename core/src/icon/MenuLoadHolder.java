package icon;

import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.FieldIcon;
import icon.type.MenuBackgroundIcon;
import icon.type.MenuCancelIcon;
import icon.type.MenuLoadIcon;

public class MenuLoadHolder extends GroupExtension {
	
	private FieldIcon button4;
	
	public MenuLoadHolder(int width,int height)
	{
		int width_of_icons=57*GameStage.singleton.VIEWPORT_WIDTH/200;
		int height_of_icons=10*GameStage.singleton.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/2.8);
		int pos_y=(int) (height/3.2);
		
		MenuBackgroundIcon base=new MenuBackgroundIcon(width/3,(int) (height/3.5),width/3,height/2);
		base.setZ(9);
		this.addActor(base);
		
		MenuCancelIcon button2= new MenuCancelIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button2.setZ(10);
		this.addActor(button2);
		pos_y+=height_of_icons+1;
		
		MenuLoadIcon button3= new MenuLoadIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setZ(10);
		this.addActor(button3);
		pos_y+=height_of_icons+2;
		
		this.button4= new FieldIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		this.addActor(button4);
		pos_y+=height_of_icons+1;
		
		
		//this.getChildren().sort();
	}
	
	public String getText()
	{
		return this.button4.getText();
	}
	
	public void setText(String text)
	{
		this.button4.setText(text);
	}

	@Override
	public void loadFromMap() {
		// TODO Auto-generated method stub
		
	}
}
