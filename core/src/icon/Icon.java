package icon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import graphic.ActorExtension;
import graphic.GameStage;
import graphic.GroupExtension;

public abstract class Icon extends Actor implements Comparable<Object> {
	/**
	 * Sprite for the design of the actor
	 */
	protected Sprite sprite;
	/**
	 * Animation for the actor
	 */
	protected Animation<Texture> animation;

	protected float pos_x;
	
	protected float pos_y;
	
	protected boolean visible;
	
	protected boolean isPressed=false;
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
	public int compareTo(Object arg0) {
		if(GroupExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((GroupExtension)arg0).getZ());
		}
		else if(ActorExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((ActorExtension)arg0).getZ());
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
		this.setPosition(
				GameStage.singleton.getCamera().position.x + this.pos_x - (GameStage.singleton.VIEWPORT_WIDTH / 2),
				GameStage.singleton.getCamera().position.y + this.pos_y - (GameStage.singleton.VIEWPORT_HEIGHT / 2));
		sprite.draw(batch);
		this.update(Gdx.graphics.getDeltaTime());
	}
	
	protected abstract void update(float delta);
	
	protected abstract Texture[] createTexture();
	
	
	protected Icon(float posX,float posY,float width,float height)
	{

		this.setWidth(width);
		this.setHeight(height);
		this.spriteCreator();
		this.setPosition(posX, posY);
		this.pos_x=posX;
		this.pos_y=posY;
	}
	
	protected void spriteCreator()
	{
		this.animation= new Animation<Texture>(.25f,this.createTexture());
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public void setIconPosition(float x,float y)
	{
		this.pos_x=x;
		this.pos_y=y;
	}


}
