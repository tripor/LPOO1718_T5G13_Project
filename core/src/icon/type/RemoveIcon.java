package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class RemoveIcon extends Icon {
	private boolean help=false;
	
	public RemoveIcon(int posX,int posY,int width,int height) {
		super(posX,posY,width,height);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	if(GameStage.singleton.getMouse().remove==false)
            	{
            		GameStage.singleton.getMouse().remove=true;
        	        sprite.setRegion(animation.getKeyFrame(2, false));
                    help=true;
            	}
            	else
            	{
            		GameStage.singleton.getMouse().remove=false;
        	        sprite.setRegion(animation.getKeyFrame(0, false));
            	}
            		
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	if(GameStage.singleton.getMouse().remove==false)
        	        sprite.setRegion(animation.getKeyFrame(1, false));
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	if(GameStage.singleton.getMouse().remove==false)
        	        sprite.setRegion(animation.getKeyFrame(0, false));
            }
        });
	}

	@Override
	public void update(float delta) {
			if(GameStage.singleton.getMouse().remove==false && this.help)
			{
    	        sprite.setRegion(animation.getKeyFrame(0, false));
				this.help=false;
			}
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[3];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("remove_icon.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("remove_icon_mouse.png");
		frames[2] = GameStage.singleton.getGame().getAssetManager().get("remove_icon_pressed.png");
		return frames;
	}
}
