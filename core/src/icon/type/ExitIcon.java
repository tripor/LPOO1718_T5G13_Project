package icon.type;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.MenuStage;
import icon.Icon;

public class ExitIcon extends Icon{
	
	public ExitIcon(int posX,int posY,int width,int height) {
		super(posX,posY,width,height);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	Gdx.app.exit();
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
    	        sprite.setRegion(animation.getKeyFrame(1, false));
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
    	        sprite.setRegion(animation.getKeyFrame(0, false));
            }
        });
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(
				MenuStage.singleton.getCamera().position.x + this.pos_x - (MenuStage.singleton.VIEWPORT_WIDTH / 2),
				MenuStage.singleton.getCamera().position.y + this.pos_y - (MenuStage.singleton.VIEWPORT_HEIGHT / 2));
		sprite.draw(batch);
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[2];
		frames[0] = MenuStage.singleton.getGame().getAssetManager().get("exit.png");
		frames[1] = MenuStage.singleton.getGame().getAssetManager().get("exit_mouse.png");
		return frames;
	}

	@Override
	protected void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
