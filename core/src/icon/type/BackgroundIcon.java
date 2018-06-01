package icon.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import graphic.MenuStage;
import icon.Icon;

public class BackgroundIcon extends Icon{
	
	public BackgroundIcon(int posX,int posY,int width,int height) {
		super(posX,posY,width,height);
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames= new Texture[2];
		frames[0] = MenuStage.singleton.getGame().getAssetManager().get("factory_background.png");
		return frames;
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(
				MenuStage.singleton.getCamera().position.x + this.pos_x - (MenuStage.singleton.VIEWPORT_WIDTH / 2),
				MenuStage.singleton.getCamera().position.y + this.pos_y - (MenuStage.singleton.VIEWPORT_HEIGHT / 2));
		sprite.draw(batch);
		this.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
