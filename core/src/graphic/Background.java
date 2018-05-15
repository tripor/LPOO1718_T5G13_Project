package graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Class that handles the creation of the BackGround
 *
 */
public class Background extends ActorExtension {
	
	/**
	 * Create a Graphic desing of this Actor
	 */
	private void createBackground() {

		Texture texture = this.game.getGame().getAssetManager().get("grass01.png");

		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
	}
	/**
	 * Constructor for the Class Background
	 * @param top
	 * @param left
	 * @param width
	 * @param height
	 */
	public Background(GameStage game,int top, int left, int width, int height) {
		this.game=game;
		this.setWidth(width);
		this.setHeight(height);
		this.createBackground();
		this.setPosition(left, top);
	}
	
	@Override
	public String toString()
	{
		return "BackGround";
	}

}
