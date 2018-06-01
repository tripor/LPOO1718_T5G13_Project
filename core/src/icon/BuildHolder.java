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
import icon.type.SmelterIcon;
import icon.type.StatIcon;
import logic.entities.ConveyorL;
import logic.entities.FactoryL;
import logic.entities.HouseL;
import logic.entities.InserterL;
import logic.entities.MineL;
import logic.entities.SmelterL;

public class BuildHolder extends GroupExtension {
	
	private StatIcon money;
	
	public BuildHolder(int width,int height)
	{
		int width_of_icons=36*GameStage.singleton.VIEWPORT_WIDTH/200;
		int height_of_icons=15*GameStage.singleton.VIEWPORT_HEIGHT/100;
		
		int pos_x=(int) (width/50);
		int pos_y=(int) (height/15);
		
		BarraIcon base=new BarraIcon(0,0,width,height);
		base.setZ(9);
		this.addActor(base);
		
		CancelIcon button1= new CancelIcon(pos_x,pos_y,width_of_icons,height_of_icons);
		button1.setZ(10);
		this.addActor(button1);
		pos_y+=height_of_icons+1;

		pos_x=(int) (width/4.5);
		pos_y=(int) (height/15);
		width_of_icons=20*GameStage.singleton.VIEWPORT_WIDTH/200;
		height_of_icons=15*GameStage.singleton.VIEWPORT_HEIGHT/100;
	
		
		
		LabelIcon label2= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,25*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label2.setZ(11);
		label2.setVisible(false);
		label2.setText("Mine\nA mine produces materials periodicaly and are saved in a external storage. Materials can be removed with inserters.Costs:"+MineL.price);
		this.addActor(label2);
		
		MineIcon button4= new MineIcon(pos_x,pos_y,width_of_icons,height_of_icons,label2);
		button4.setZ(10);
		this.addActor(button4);
		pos_x+=width_of_icons+1;
		
		LabelIcon label6= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,25*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label6.setZ(11);
		label6.setVisible(false);
		label6.setText("Smelter\nA Smelter transforms ores materials into plates materials. Costs:"+SmelterL.price);
		this.addActor(label6);
		
		SmelterIcon button8= new SmelterIcon(pos_x,pos_y,width_of_icons,height_of_icons,label6);
		button8.setZ(10);
		this.addActor(button8);
		pos_x+=width_of_icons+1;
		
		LabelIcon label= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,30*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label.setZ(11);
		label.setVisible(false);
		label.setText("Factory\nA Factory accepts items from a inserter and produces new products to be delivered to the houses. Select a Factory to select a recipe.Costs:"+FactoryL.price);
		this.addActor(label);
		
		FactoryIcon button3= new FactoryIcon(pos_x,pos_y,width_of_icons,height_of_icons,label);
		button3.setZ(10);
		this.addActor(button3);
		pos_x+=width_of_icons+1;
		
		LabelIcon label3= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,30*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label3.setZ(11);
		label3.setVisible(false);
		label3.setText("House\nA house is where people live and where you ca sell items. People work in factories and places and make them work. People are automatically assigned.Cost:"+HouseL.price);
		this.addActor(label3);
		
		HouseIcon button5= new HouseIcon(pos_x,pos_y,width_of_icons,height_of_icons,label3);
		button5.setZ(10);
		this.addActor(button5);
		pos_x+=width_of_icons+1;
		
		LabelIcon label4= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,30*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label4.setZ(11);
		label4.setVisible(false);
		label4.setText("Inserter\nA inserter can pick up items from the ground or from buildings and can deliver them 180 degrees from the place they picked up the material.Cost:"+InserterL.price);
		this.addActor(label4);
		
		InserterIcon button6= new InserterIcon(pos_x,pos_y,width_of_icons,height_of_icons,label4);
		button6.setZ(10);
		this.addActor(button6);
		pos_x+=width_of_icons+1;
		
		LabelIcon label5= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,25*GameStage.singleton.VIEWPORT_HEIGHT/100);
		label5.setZ(11);
		label5.setVisible(false);
		label5.setText("Conveyor\nA conveyor transports all materials on top to the next block. Rotate the conveyor by pressing R.Cost:"+ConveyorL.price);
		this.addActor(label5);
		
		ConveyorIcon button7= new ConveyorIcon(pos_x,pos_y,width_of_icons,height_of_icons,label5);
		button7.setZ(10);
		this.addActor(button7);
		pos_x+=width_of_icons+1;
		
		money= new StatIcon(100,GameStage.singleton.VIEWPORT_HEIGHT-200,200*GameStage.singleton.VIEWPORT_WIDTH/200,10*GameStage.singleton.VIEWPORT_HEIGHT/100);
		money.setZ(11);
		money.setVisible(true);
		money.setText("Money: 0");
		this.addActor(money);
		
		this.getChildren().sort();
	}

	@Override
	public void loadFromMap() {
		// TODO Auto-generated method stub
		
	}
}
