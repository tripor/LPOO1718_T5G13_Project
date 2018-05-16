package icon.type;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.groundup.game.GameStage;
import icon.Icon;
import place.type.Factory;

public class FactoryIcon extends Icon {

	private void createFactoryIcon() {
		
		Texture texture = this.game.getGame().getAssetManager().get("factory_icon.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
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
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
	

}
