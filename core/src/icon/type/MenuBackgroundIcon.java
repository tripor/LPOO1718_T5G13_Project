package icon.type;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class MenuBackgroundIcon extends Icon  { 

	private void createFactoryIcon() {

		frames[0] = this.game.getGame().getAssetManager().get("menu_background.png");

		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public MenuBackgroundIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            }
        });
	}

	@Override
	public void update(float delta) {
	}
}
