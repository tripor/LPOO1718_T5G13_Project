package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import icon.Icon;
import place.type.IronMine;

public class ConveyorIcon extends Icon {

	private void createConveyorIcon() {

		Texture texture = this.game.getGame().getAssetManager().get("conveyor_icon.png");

		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
	}

	public ConveyorIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createConveyorIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.getMouse().createMouse("conveyor1.png", Conveyor.width, Conveyor.height);
            }
        });
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
