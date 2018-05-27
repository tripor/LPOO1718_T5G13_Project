package icon.type;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import icon.MenuSaveHolder;

public class MenuSaveIcon extends Icon  { 

	private void createFactoryIcon() {

		frames[0] = this.game.getGame().getAssetManager().get("menu_save.png");
		frames[1] = this.game.getGame().getAssetManager().get("menu_save_mouse.png");

		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public MenuSaveIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	if(game.icons().getHolder()!=4)
            	{
            		game.icons().setHolder(4);
            	}
            	else
            	{
            		game.saveGame(((MenuSaveHolder)game.icons().getCurrentHolder()).getText());
            		game.icons().setHolder(3);
            	}
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
