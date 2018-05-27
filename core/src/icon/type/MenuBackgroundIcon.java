package icon.type;

import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import icon.Icon;

public class MenuBackgroundIcon extends Icon  { 

	private void createFactoryIcon() {

		frames[0] = this.game.getGame().getAssetManager().get("menu_background.png");

		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public MenuBackgroundIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
	}

	@Override
	public void update(float delta) {
	}
}
