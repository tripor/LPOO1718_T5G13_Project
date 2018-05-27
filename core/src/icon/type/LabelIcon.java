package icon.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import graphic.GameStage;
import graphic.GroupExtension;

public class LabelIcon extends GroupExtension  { 

	private Label label;
	private MenuBackgroundIcon back;
	private GameStage game;
	
	public LabelIcon(final GameStage game,final int width,final int height) {
		this.game=game;

		back= new MenuBackgroundIcon(game,0,0,width,height);
		this.addActor(back);
		
		label = new Label("teessssssssssstar", new Label.LabelStyle(new BitmapFont(), null));
		label.setColor(Color.BLACK);
		label.setWidth(width-4);
		label.setHeight(height);
		label.setFontScale(0.35f);
		label.setWrap(true);
		label.setEllipsis(false);
		
		
		this.addActor(label);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Vector3 mouse_pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		this.game.getViewport().unproject(mouse_pos);
		label.setPosition(mouse_pos.x+6, mouse_pos.y+3);
		back.setIconPosition(mouse_pos.x+3, mouse_pos.y+3);
		super.draw(batch,parentAlpha);
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
