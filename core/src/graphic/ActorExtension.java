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
public abstract class ActorExtension extends Actor implements Comparable<ActorExtension> {
	/**
	 * Sprite for the design of the actor
	 */
	protected Sprite sprite;
	/**
	 * The game that this actor is in
	 */
	protected GameStage game;
	/**
	 * Animation for the actor
	 */
	protected Animation<Texture> animation;
	/**
     * Time used to select the current animation frame.
     */
    protected float stateTime = 0;
    /**
     * For the drawing
     */
    protected Integer z=1;
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
	public int compareTo(ActorExtension a)
	{
		return this.z.compareTo(a.z) ;
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
