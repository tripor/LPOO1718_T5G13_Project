package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.MenuStage;

public class ExitIcon extends Actor{
	/**
	 * Sprite for the design of the actor
	 */
	protected Sprite sprite;
	/**
	 * The game that this actor is in
	 */
	protected MenuStage game;
	protected Texture[] frames= new Texture[2];

	protected final float pos_x;
	protected final float pos_y;
	
	private void createFactoryIcon() {
		
		frames[0] = this.game.getGame().getAssetManager().get("exit.png");
		frames[1] = this.game.getGame().getAssetManager().get("exit_mouse.png");
		
		sprite = new Sprite(frames[0]);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public ExitIcon(final MenuStage game,int posX,int posY,int width,int height) {
		this.game=game;
		this.pos_x=posX;
		this.pos_y=posY;
		this.setWidth(width);
		this.setHeight(height);
		this.createFactoryIcon();
		this.setPosition(posX, posY);
		
		this.addCaptureListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	System.exit(0);
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
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(this.game.getCamera().position.x + this.pos_x - (this.game.VIEWPORT_WIDTH/2), this.game.getCamera().position.y + this.pos_y -(this.game.VIEWPORT_HEIGHT/2));
		sprite.draw(batch);
	}
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
	}

	@Override
	protected void positionChanged() {
		super.positionChanged();
		sprite.setPosition(this.getX(), this.getY());
	}

	@Override
	protected void rotationChanged() {
		super.rotationChanged();
		sprite.setRotation(getRotation());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
