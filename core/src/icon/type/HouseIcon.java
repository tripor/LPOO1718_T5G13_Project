package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class HouseIcon extends Icon {
	
	private void createFactoryIcon() {
		
		Texture novo = this.game.getGame().getAssetManager().get("house.png");
		
		sprite = new Sprite(novo);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public HouseIcon(final GameStage game,int posX,int posY,final int width,final int height, final LabelIcon label) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.getMouse().createMouse("house.png", 30, 30);
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
}
