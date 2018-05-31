package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import logic.entities.ConveyorL;

public class ConveyorIcon extends Icon {

	public ConveyorIcon(int posX,int posY,final int width,final int height, final LabelIcon label) {
		super(posX,posY,width,height);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	GameStage.singleton.getMouse().createMouse("conveyor1.png",ConveyorL.width,ConveyorL.height);
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	label.setVisible(true);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	label.setVisible(false);
            }
        });
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[2];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("conveyor1.png");
		return frames;
	}
}
