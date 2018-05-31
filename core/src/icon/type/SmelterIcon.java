package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import logic.entities.SmelterL;

public class SmelterIcon extends Icon {
	
	public SmelterIcon(int posX,int posY,int width,int height, final LabelIcon label) {
		super(posX,posY,width,height);
		
		this.addCaptureListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				GameStage.singleton.getMouse().createMouse("smelter.png",SmelterL.width, SmelterL.height);
			}

			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				label.setVisible(true);
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				label.setVisible(false);
			}
        });
	}

	@Override
	public void update(float delta) {
			
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("smelter.png");
		return frames;
	}
	
	
}
