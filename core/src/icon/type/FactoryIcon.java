package icon.type;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import icon.Icon;

public class FactoryIcon extends Icon {

	private void createFactoryIcon() {
		
		Texture texture = this.game.getGame().getAssetManager().get("factory_icon.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public FactoryIcon(GameStage game,int posX,int posY,int width,int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(this.game.getCamera().position.x + this.pos_x - (GameStage.VIEWPORT_WIDTH/2), this.game.getCamera().position.y + this.pos_y -(GameStage.VIEWPORT_HEIGHT/2));
		sprite.draw(batch);
	}
	
	

}
