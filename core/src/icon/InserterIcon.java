package icon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;

public class InserterIcon  extends Icon  { 
	
	private Texture[] frames=new Texture[3];
	private boolean mouse_over=false;
	private boolean selected=false;

	private void createFactoryIcon() {
		
		frames[0] = this.game.getGame().getAssetManager().get("inserter_button_unpressed.png");
		frames[1] = this.game.getGame().getAssetManager().get("inserter_button_pressed.png");
		frames[2] = this.game.getGame().getAssetManager().get("inserter_button_mouse_over.png");

		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	private void newSprite(int number)
	{
		sprite.setTexture(frames[number]);
	}
	
	public InserterIcon(final GameStage game,int posX,int posY,final int width,final int height) {
		super(game,posX,posY);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            		game.getMouse().createMouse("inserter_direction.png", 10, 10);
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
            	mouse_over=true;
            	if(!selected)
            		newSprite(2);
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
            	mouse_over=false;
            	if(!selected)
            		newSprite(0);
            }
        });
	}

	@Override
	public void update(float delta) {
		this.selected=this.game.getMouse().isSelected;
		if(this.selected)
    		newSprite(1);
		else if(!this.mouse_over)
    		newSprite(0);
	}
	
}
