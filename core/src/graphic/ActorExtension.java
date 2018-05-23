package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;


/**
 * Class that implements some overloaded funtion of actor
 *
 */
public abstract class ActorExtension extends Actor implements Comparable<Object> {
	/**
	 * Sprite for the design of the actor
	 */
	protected transient Sprite sprite;
	/**
	 * The game that this actor is in
	 */
	protected transient GameStage game;
	/**
	 * Animation for the actor
	 */
	protected transient Animation<Texture> animation;
	/**
     * Time used to select the current animation frame.
     */
    protected transient float stateTime = 0;
    /**
     * For the drawing
     */
    protected transient Integer z=1;
	/**
	 * 
	 * @return Returns the Z value
	 */
	public Integer getZ() {
		return z;
	}
	/**
	 * Sets the Z value
	 * @param z The z value I want to change to
	 */
	public void setZ(Integer z) {
		this.z = z;
	}

	/**
	 * Transperancy of the sprite
	 * @param alpha the amount i want
	 */
	protected void setTransparency(float alpha)
	{
		this.sprite.setAlpha(alpha);
	}
	
	@Override
	public int compareTo(Object arg0) {
		if(GroupExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((GroupExtension)arg0).z);
		}
		else if(ActorExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((ActorExtension)arg0).z);
		}
		return 0;
	}
	
	@Override
	public void setPosition(float x, float y) {
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
		this.update(Gdx.graphics.getDeltaTime());
	}
	/**
	 * Call this every frame 
	 * @param delta Time since the last frame
	 */
	public abstract void update(float delta);
}
