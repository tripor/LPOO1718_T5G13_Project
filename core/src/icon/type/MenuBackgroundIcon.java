package icon.type;

import com.badlogic.gdx.graphics.Texture;

import graphic.GameStage;
import icon.Icon;

public class MenuBackgroundIcon extends Icon  { 
	
	public MenuBackgroundIcon(int posX,int posY,int width,int height) {
		super(posX,posY,width,height);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("menu_background.png");
		return frames;
	}
}
