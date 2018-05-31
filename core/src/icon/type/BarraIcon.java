package icon.type;

import com.badlogic.gdx.graphics.Texture;

import graphic.GameStage;
import icon.Icon;
/**
 * Class Build Icon
 *
 */
public class BarraIcon extends Icon{
	
	private float stateTime=0;
	/**
	 * Constructor for the class BuildIcon
	 * @param game the game it belongs to
	 * @param posX the posX in pixels
	 * @param posY the posY in pixels
	 * @param width the width of the image in pixels
	 * @param height the height of the image in pixels
	 */
	public BarraIcon(int posX,int posY,final int width,final int height) {
		super(posX,posY,width,height);
	}

	@Override
	public void update(float delta) {
		this.stateTime+=delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
		
		
	}
	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[7];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("barra1.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("barra2.png");
		frames[2] = GameStage.singleton.getGame().getAssetManager().get("barra3.png");
		frames[3] = GameStage.singleton.getGame().getAssetManager().get("barra4.png");
		frames[4] = GameStage.singleton.getGame().getAssetManager().get("barra5.png");
		frames[5] = GameStage.singleton.getGame().getAssetManager().get("barra6.png");
		frames[6] = GameStage.singleton.getGame().getAssetManager().get("barra7.png");
		return frames;
	}

}
