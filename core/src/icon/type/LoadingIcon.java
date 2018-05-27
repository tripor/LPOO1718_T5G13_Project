package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.groundup.game.GroundUpScreen;

import graphic.GameStage;
import icon.Icon;

public class LoadingIcon extends Icon{

	private void createBuildIcon() {
		
		Texture[] frames=new Texture[6];
		
		frames[0] = this.game.getGame().getAssetManager().get("loading1.png");
		frames[1] = this.game.getGame().getAssetManager().get("loading2.png");
		frames[2] = this.game.getGame().getAssetManager().get("loading3.png");
		frames[3] = this.game.getGame().getAssetManager().get("loading4.png");
		frames[4] = this.game.getGame().getAssetManager().get("loading5.png");
		frames[5] = this.game.getGame().getAssetManager().get("loading6.png");
		
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
	public LoadingIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createBuildIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            }
        });
	}

	@Override
	public void update(float delta) {
		this.stateTime+=delta;
        sprite.setRegion(animation.getKeyFrame(stateTime, true));
        this.game.loadGame();
		
		
	}
}
