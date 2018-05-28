package icon;


import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.BarraIcon;
import icon.type.BuildIcon;
import icon.type.MenuIcon;
import icon.type.RemoveIcon;
import icon.type.StatIcon;

public class NormalHolder extends GroupExtension {
	
	private StatIcon money;
	
	public NormalHolder(GameStage game,int width,int height)
	{
		int width_of_icons=36*game.VIEWPORT_WIDTH/200;
		int height_of_icons=5*game.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/50);
		int pos_y=(int) (height/15);
		
		BarraIcon base=new BarraIcon(game,0,0,width,height);
		base.setZ(9);
		this.addActor(base);
		
		MenuIcon button3= new MenuIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button3.setZ(10);
		this.addActor(button3);
		pos_y+=height_of_icons+1;
		
		RemoveIcon button2= new RemoveIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button2.setZ(10);
		this.addActor(button2);
		pos_y+=height_of_icons+1;
		
		BuildIcon button1= new BuildIcon(game,pos_x,pos_y,width_of_icons,height_of_icons);
		button1.setZ(10);
		this.addActor(button1);
		pos_y+=height_of_icons+1;
		
		pos_x=(int) (width/4.5);
		pos_y=(int) (height/3);
		
		money= new StatIcon(game,pos_x,pos_y,200*game.VIEWPORT_WIDTH/200,10*game.VIEWPORT_HEIGHT/100);
		money.setZ(11);
		money.setVisible(true);
		money.setText("Money: 0");
		this.addActor(money);
		
		this.getChildren().sort();
	}

}
