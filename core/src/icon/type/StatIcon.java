package icon.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import graphic.GroupExtension;

public class StatIcon extends GroupExtension  { 

	private Label label;
	
	public StatIcon(final int width,final int height) {
		
		label = new Label("0", new Label.LabelStyle(new BitmapFont(), null));
		label.setColor(Color.GOLD);
		label.setWidth(width-4);
		label.setHeight(height);
		label.setFontScale(0.35f);
		label.setWrap(true);
		label.setEllipsis(false);
		
		
		this.addActor(label);
	}
	
	public void setPosition(float posX,float posY) {
		this.label.setPosition(posX, posY);
	}
	
	public void setText(String text) {
		this.label.setText(text);
	}
	
	public String getText() {
		return this.label.getText().toString();
	}
	
	public Label getLabel()
	{
		return this.label;
	}
}
