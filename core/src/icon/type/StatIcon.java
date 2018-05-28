package icon.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import graphic.GameStage;
import graphic.GroupExtension;

public class StatIcon extends GroupExtension  { 

	private Label label;
	private GameStage game;
	private int posX;
	private int posY;
	
	public StatIcon(GameStage game,int pos_x,int pos_y, final int width,final int height) {
		this.game=game;
		label = new Label("0", new Label.LabelStyle(new BitmapFont(), null));
		label.setColor(Color.GOLD);
		label.setWidth(width-4);
		label.setHeight(height);
		label.setFontScale((1f*this.game.VIEWPORT_WIDTH)/960);
		label.setWrap(true);
		label.setEllipsis(false);
		this.setPosition(pos_x, pos_y);
		this.posX=pos_x;
		this.posY=pos_y;
		
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
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setText("Money: " + this.game.map().money);
		this.setPosition(this.game.getCamera().position.x + this.posX - (this.game.VIEWPORT_WIDTH / 2),
				this.game.getCamera().position.y + this.posY - (this.game.VIEWPORT_HEIGHT / 2));
		super.draw(batch, parentAlpha);
	}
}
