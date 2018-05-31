package icon.type;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import graphic.GameStage;
import graphic.GroupExtension;

public class FieldIcon extends GroupExtension{
	
	private TextField button3;
	protected final float pos_x;
	protected final float pos_y;
	
	public FieldIcon(int posX,int posY,int width,int height) {
		this.pos_x=posX;
		this.pos_y=posY;
		Skin uiSkin = new Skin(Gdx.files.internal("glassy-ui.json"));
		button3 = (new TextField("",uiSkin));
		button3.setWidth(width);
		button3.setHeight(height);
		button3.setPosition(posX, posY);
		this.addActor(button3);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		button3.setPosition(
				GameStage.singleton.getCamera().position.x + this.pos_x - (GameStage.singleton.VIEWPORT_WIDTH / 2),
				GameStage.singleton.getCamera().position.y + this.pos_y - (GameStage.singleton.VIEWPORT_HEIGHT / 2));
		button3.draw(batch, parentAlpha);
	}

	public String getText() {
		String devolver = this.button3.getText();
		this.button3.setText("");
		return devolver;
	}
	
	public void setText(String text) {
		this.button3.setText(text);
	}


	@Override
	public void loadFromMap() {
		// TODO Auto-generated method stub
		
	}
}
