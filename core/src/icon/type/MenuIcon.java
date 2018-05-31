package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class MenuIcon extends Icon{
	
	public MenuIcon(int posX,int posY,int width,int height) {
		super(posX,posY,width,height);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	GameStage.singleton.getMouse().remove=false;
            	GameStage.singleton.menuOpen=true;
            	GameStage.singleton.icons().setHolder(3);
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
	public void update(float delta) {
			
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[2];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("menu_icon.png");
		frames[1] =  GameStage.singleton.getGame().getAssetManager().get("menu_icon_mouse.png");
		return frames;
	}

}
