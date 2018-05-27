package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;

public class RemoveIcon extends Icon {
	private Texture[] frame=new Texture[3];
	private boolean help=false;
	private void createFactoryIcon() {
		
		frame[0] = this.game.getGame().getAssetManager().get("remove_icon.png");
		frame[1] = this.game.getGame().getAssetManager().get("remove_icon_mouse.png");
		frame[2] = this.game.getGame().getAssetManager().get("remove_icon_pressed.png");
		
		sprite = new Sprite(frame[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public RemoveIcon(final GameStage game,int posX,int posY,int width,int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	if(game.getMouse().remove==false)
            	{
            		game.getMouse().remove=true;
                    sprite.setTexture(frame[2]);
                    help=true;
            	}
            	else
            	{
            		game.getMouse().remove=false;
                    sprite.setTexture(frame[0]);
            	}
            		
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	if(game.getMouse().remove==false)
            		sprite.setTexture(frame[1]);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	if(game.getMouse().remove==false)
            		sprite.setTexture(frame[0]);
            }
        });
	}

	@Override
	public void update(float delta) {
			if(this.game.getMouse().remove==false && this.help)
			{
                sprite.setTexture(frame[0]);
				this.help=false;
			}
	}
}
