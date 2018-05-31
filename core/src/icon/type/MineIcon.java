package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import logic.entities.MineL;

public class MineIcon extends Icon {

	public MineIcon(int posX,int posY,final int width,final int height, final LabelIcon label2) {
		super(posX,posY,width,height);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	GameStage.singleton.getMouse().createMouse("iron_mine.png",MineL.width, MineL.height);
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	label2.setVisible(true);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	label2.setVisible(false);
            }
        });
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("iron_mine.png");
		return frames;
	}
}
