package icon;

import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.BarraIcon;
import icon.type.CancelIcon;
import icon.type.ConveyorIcon;
import icon.type.FactoryIcon;
import icon.type.HouseIcon;
import icon.type.InserterIcon;
import icon.type.MineIcon;

public class BuildHolder extends GroupExtension {
	
	public BuildHolder(GameStage game,int width,int height)
	{
		int width_of_icons=36*game.VIEWPORT_WIDTH/200;
		int height_of_icons=15*game.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/50);
		int pos_y=(int) (height/15);
		
		BarraIcon base=new BarraIcon(game,0,0,width,height);
		base.setZ(9);
		this.addActor(base);
		
		CancelIcon button1= new CancelIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button1.setZ(10);
		this.addActor(button1);
		pos_y+=height_of_icons+1;

		pos_x=(int) (width/4.5);
		pos_y=(int) (height/15);
		width_of_icons=20*game.VIEWPORT_WIDTH/200;
		height_of_icons=15*game.VIEWPORT_HEIGHT/100;
		
		FactoryIcon button3= new FactoryIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setZ(10);
		this.addActor(button3);
		pos_x+=width_of_icons+1;
		
		MineIcon button4= new MineIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button4.setZ(10);
		this.addActor(button4);
		pos_x+=width_of_icons+1;
		
		HouseIcon button5= new HouseIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button5.setZ(10);
		this.addActor(button5);
		pos_x+=width_of_icons+1;
		
		InserterIcon button6= new InserterIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button6.setZ(10);
		this.addActor(button6);
		pos_x+=width_of_icons+1;
		
		ConveyorIcon button7= new ConveyorIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button7.setZ(10);
		this.addActor(button7);
		pos_x+=width_of_icons+1;
		
		this.getChildren().sort();
	}
}
