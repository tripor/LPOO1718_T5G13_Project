package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import logic.Entity;


/**
 * Class that implements some overloaded funtion of actor
 *
 */
public abstract class ActorExtension extends Actor implements Comparable<Object> {
	/**
	 * Sprite for the design of the actor
	 */
	protected Sprite sprite;
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
     * The Entity logic this graphical object is associated
     */
    protected Entity instance;
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

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(!GameStage.singleton.menuOpen)
		{
			this.update(Gdx.graphics.getDeltaTime());
		}
		if (GameStage.singleton.pixelOnScreen(this.getX(),this.getY())
				|| GameStage.singleton.pixelOnScreen(this.getX()+this.getWidth(),this.getY())
				|| GameStage.singleton.pixelOnScreen(this.getX(),this.getY()+this.getHeight())
				|| GameStage.singleton.pixelOnScreen(this.getX()+this.getWidth(),this.getY()+this.getHeight())) {
	        sprite.setRegion(animation.getKeyFrame(GameStage.singleton.stateTime, true));
			sprite.draw(batch);

		}
	}
	/**
	 * Call this every frame 
	 * @param delta Time since the last frame
	 */
	public void update(float delta)
	{
		this.instance.handler();
	}
	/**
	 * 
	 * @return Returns the entity of this actor
	 */
	public Entity getInstance() {
		return instance;
	}
	
	protected abstract Texture[] createTexture();

	
	protected void create()
	{
		this.setWidth(this.instance.getWidth()*GameStage.singleton.scale());
		this.setHeight(this.instance.getHeight()*GameStage.singleton.scale());
		this.spriteCreator();
		this.setPosition(this.instance.getPosX()*GameStage.singleton.scale(), this.instance.getPosY()*GameStage.singleton.scale());
	}
	
	protected void spriteCreator()
	{
		this.animation= new Animation<Texture>(.25f,this.createTexture());
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		int direction=this.instance.getDirection();
		if(direction!=4)
			sprite.rotate(-90*direction);
	}
	
	
}
