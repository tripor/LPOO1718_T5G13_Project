package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GroundUpGame;
import place.Place;

public class Factory extends Place {
	
	private Sprite sprite;

	private void createFactory(GroundUpGame game)
	{
		Texture texture=game.getAssetManager().get("factory.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	@Override
	public void setPosition(float x,float y)
	{
		super.setPosition(x, y);
	}
	
	@Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
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
	
	@Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
	public Factory(GroundUpGame game, int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactory(game);
		this.setPosition(top, left);
	}

}
