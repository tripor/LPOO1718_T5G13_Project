package icon;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import graphic.GameStage;
import graphic.GroupExtension;
import icon.type.BarraIcon;
import icon.type.ButtonIcon;
import icon.type.CancelIcon;
import icon.type.LabelIcon;
import logic.Recipe;

public class SelectedHolder extends GroupExtension {
	
	private ArrayList<ButtonIcon> botoes;
	
	public SelectedHolder(int width,int height)
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
		width_of_icons=3*GameStage.singleton.VIEWPORT_WIDTH/200;
		height_of_icons=3*GameStage.singleton.VIEWPORT_HEIGHT/100;
		
		botoes= new ArrayList<ButtonIcon>();
		
		Recipe receitas= new Recipe();
		for(int i=0;i<receitas.totalNumber();i++) {
			LabelIcon label= new LabelIcon(40*GameStage.singleton.VIEWPORT_WIDTH/200,25*GameStage.singleton.VIEWPORT_HEIGHT/100);
			label.setZ(11);
			label.setVisible(false);
			label.setText(receitas.getRecipeString(i));
			this.addActor(label);
			
			ButtonIcon button= new ButtonIcon(pos_x,pos_y,width_of_icons,height_of_icons,label,i);
			button.setZ(10);
			this.addActor(button);
			botoes.add(button);
			pos_x+=width_of_icons+1;
		}

		this.getChildren().sort();
		
	}
	
	public void select(int index) {
		for(ButtonIcon it:this.botoes)
		{
			it.unselect();
		}
		this.botoes.get(index).select();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.select(GameStage.singleton.getMouse().selected.getSelectedRecipe());
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public void loadFromMap() {
		
	}

}
