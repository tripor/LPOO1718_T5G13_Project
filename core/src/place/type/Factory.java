package place.type;

import static graphic.GameState.VIEWPORT_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import graphic.GroundUpGame;
import place.Place;

public class Factory extends Place {
	
	private Sprite sprite;

	private void createFactory(GroundUpGame game)
	{
		Texture texture=game.getAssetManager().get("factory.png");
		
		sprite = new Sprite(texture);
		
		setWidth(texture.getWidth());
        setHeight(texture.getHeight());
		
        setOrigin(getWidth() / 2, getHeight() / 2);
        sprite.setOrigin(getWidth() / 2, getHeight() / 2);
	}
	
	@Override
	public void setPosition(float x,float y)
	{
		super.setPosition(x - getWidth()/2, y - getHeight() /2);
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
        sprite.setColor(getColor());
        sprite.draw(batch);
    }
	public Factory(GroundUpGame game, int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
	}

}
