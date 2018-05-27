package icon;

import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.BarraIcon;
import icon.type.CancelIcon;
import icon.type.ConveyorIcon;
import icon.type.FactoryIcon;
import icon.type.HouseIcon;
import icon.type.InserterIcon;
import icon.type.LabelIcon;
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
	
		LabelIcon label= new LabelIcon(game,60,50);
		label.setZ(11);
		label.setVisible(false);
		label.setText("Factory\nA Factory accepts items from a inserter and produces new products to be delivered to the houses.");
		this.addActor(label);
		
		FactoryIcon button3= new FactoryIcon(game,pos_x,pos_y,width_of_icons,height_of_icons,label);
		button3.setZ(10);
		this.addActor(button3);
		pos_x+=width_of_icons+1;
		
		LabelIcon label2= new LabelIcon(game,60,50);
		label2.setZ(11);
		label2.setVisible(false);
		label2.setText("Mine\nA mine produces materials periodicaly and are saved in a internal storage. Materials can be removed with inserters.");
		this.addActor(label2);
		
		MineIcon button4= new MineIcon(game,pos_x,pos_y,width_of_icons,height_of_icons,label2);
		button4.setZ(10);
		this.addActor(button4);
		pos_x+=width_of_icons+1;
		
		LabelIcon label3= new LabelIcon(game,60,50);
		label3.setZ(11);
		label3.setVisible(false);
		label3.setText("House\nA house is here people live. People work in factories and places and increase productivity. The more house the more people.");
		this.addActor(label3);
		
		HouseIcon button5= new HouseIcon(game,pos_x,pos_y,width_of_icons,height_of_icons,label3);
		button5.setZ(10);
		this.addActor(button5);
		pos_x+=width_of_icons+1;
		
		LabelIcon label4= new LabelIcon(game,70,50);
		label4.setZ(11);
		label4.setVisible(false);
		label4.setText("Inserter\nA inserter can pick up items from the ground or from buildings and can deliver them 180 degrees from the place they picked up the material.");
		this.addActor(label4);
		
		InserterIcon button6= new InserterIcon(game,pos_x,pos_y,width_of_icons,height_of_icons,label4);
		button6.setZ(10);
		this.addActor(button6);
		pos_x+=width_of_icons+1;
		
		LabelIcon label5= new LabelIcon(game,60,50);
		label5.setZ(11);
		label5.setVisible(false);
		label5.setText("Conveyor\nA conveyor transports all materials on top to the next block. Rotate the conveyor by pressing R.");
		this.addActor(label5);
		
		ConveyorIcon button7= new ConveyorIcon(game,pos_x,pos_y,width_of_icons,height_of_icons,label5);
		button7.setZ(10);
		this.addActor(button7);
		pos_x+=width_of_icons+1;
		
		this.getChildren().sort();
	}
}
