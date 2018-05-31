package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class LoadingIcon extends Icon{

	private float stateTime;
	/**
	 * Constructor for the class BuildIcon
	 * @param game the game it belongs to
	 * @param posX the posX in pixels
	 * @param posY the posY in pixels
	 * @param width the width of the image in pixels
	 * @param height the height of the image in pixels
	 */
	public LoadingIcon(int posX,int posY,final int width,final int height) {
		super(posX,posY,width,height);
		this.stateTime=0;
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            }
        });
	}

	@Override
	public void update(float delta) {
		this.stateTime+=delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
        GameStage.singleton.loadGame();
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[6];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("loading1.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("loading2.png");
		frames[2] = GameStage.singleton.getGame().getAssetManager().get("loading3.png");
		frames[3] = GameStage.singleton.getGame().getAssetManager().get("loading4.png");
		frames[4] = GameStage.singleton.getGame().getAssetManager().get("loading5.png");
		frames[5] = GameStage.singleton.getGame().getAssetManager().get("loading6.png");
		return frames;
	}
}
