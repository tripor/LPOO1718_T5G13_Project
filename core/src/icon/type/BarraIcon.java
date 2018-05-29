package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import icon.Icon;
/**
 * Class Build Icon
 *
 */
public class BarraIcon extends Icon{

	private void createBuildIcon() {
		
		Texture[] frames=new Texture[7];
		
		frames[0] = this.game.getGame().getAssetManager().get("barra1.png");
		frames[1] = this.game.getGame().getAssetManager().get("barra2.png");
		frames[2] = this.game.getGame().getAssetManager().get("barra3.png");
		frames[3] = this.game.getGame().getAssetManager().get("barra4.png");
		frames[4] = this.game.getGame().getAssetManager().get("barra5.png");
		frames[5] = this.game.getGame().getAssetManager().get("barra6.png");
		frames[6] = this.game.getGame().getAssetManager().get("barra7.png");
		
		this.animation= new Animation<Texture>(.25f,frames);
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	/**
	 * Constructor for the class BuildIcon
	 * @param game the game it belongs to
	 * @param posX the posX in pixels
	 * @param posY the posY in pixels
	 * @param width the width of the image in pixels
	 * @param height the height of the image in pixels
	 */
	public BarraIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createBuildIcon();
		this.setPosition(posX, posY);
	}

	@Override
	public void update(float delta) {
		this.stateTime+=delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
		
		
	}

}
