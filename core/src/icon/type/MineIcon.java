package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class MineIcon extends Icon {
	
	private void createFactoryIcon() {

		Texture texture = this.game.getGame().getAssetManager().get("iron_mine.png");

		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
	}

	public MineIcon(final GameStage game,int posX,int posY,final int width,final int height, final LabelIcon label2) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.getMouse().createMouse("iron_mine.png",20, 20);
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
}
