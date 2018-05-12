package icon.type;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import place.type.Factory;

public class FactoryIcon extends Icon {

	private void createFactoryIcon() {
		
		Texture texture = this.game.getGame().getAssetManager().get("factory_icon.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public FactoryIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.getMouse().createMouse("factory.png", Factory.width, Factory.height);
            }
        });
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(this.game.getCamera().position.x + this.pos_x - (GameStage.VIEWPORT_WIDTH/2), this.game.getCamera().position.y + this.pos_y -(GameStage.VIEWPORT_HEIGHT/2));
		sprite.draw(batch);
	}
	
	

}
