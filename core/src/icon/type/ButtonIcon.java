package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class ButtonIcon extends Icon {
	
	public ButtonIcon(int posX,int posY,int width,int height, final LabelIcon label, final int number) {
		super(posX,posY,width,height);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) 
            {
            	GameStage.singleton.getMouse().selected.selectRecipe(number);
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				label.setVisible(true);
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				label.setVisible(false);
			}
        });
	}
	
	public void select()
	{
        sprite.setRegion(animation.getKeyFrame(1, false));
	}
	
	public void unselect()
	{
        sprite.setRegion(animation.getKeyFrame(0, false));
	}

	@Override
	public void update(float delta) {
			
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[2];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("button.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("button_pressed.png");
		return frames;
	}

}
