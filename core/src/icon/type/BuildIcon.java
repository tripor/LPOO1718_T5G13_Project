package icon.type;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class BuildIcon extends Icon {
	
	private void createIcon() {
		
		frames[0] = this.game.getGame().getAssetManager().get("build_icon.png");
		frames[1] = this.game.getGame().getAssetManager().get("build_icon_mouse.png");
		
		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public BuildIcon(final GameStage game,int posX,int posY,int width,int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createIcon();
		this.setPosition(posX, posY);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) 
            {
            	game.icons().setHolder(2);
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
                sprite.setTexture(frames[1]);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
                sprite.setTexture(frames[0]);
            }
        });
	}

	@Override
	public void update(float delta) {
			
	}

}
