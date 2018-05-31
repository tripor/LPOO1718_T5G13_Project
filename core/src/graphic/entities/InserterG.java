package graphic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.InserterL;
/**
 * Class that handle the graphical part of the Inserter 
 *
 */
public class InserterG extends ActorExtension{
	

	/**
	 * Sprite for the hand
	 */
	private transient Sprite sprite2;
	
	public InserterG(int posX, int posY, int direction) {
		this.instance = new InserterL((int)(posX * GameStage.singleton.reverseScale()),(int)( posY * GameStage.singleton.reverseScale()), direction);
		this.create();
	}

	public InserterG(Entity in) {
		this.instance = in;
		this.create();
	}
	
	@Override
	protected void create()
	{
		this.setWidth(this.instance.getWidth() * GameStage.singleton.scale());
		this.setHeight(this.instance.getHeight() * GameStage.singleton.scale());
		this.spriteCreator();
		this.setPosition(this.instance.getPosX() * GameStage.singleton.scale(), this.instance.getPosY() * GameStage.singleton.scale());
		this.sprite2.setPosition(
				this.instance.getPosX() * GameStage.singleton.scale()
						- (((InserterL) this.instance).width_hand * GameStage.singleton.scale() - 2 * GameStage.singleton.scale()),
				this.instance.getPosY() * GameStage.singleton.scale());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
		sprite2.draw(batch);
		if(!GameStage.singleton.menuOpen)
			this.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	protected Texture[] createTexture() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void spriteCreator()
	{
		Texture texture = GameStage.singleton.getGame().getAssetManager().get("inserter_base.png");
		Texture texture2 = GameStage.singleton.getGame().getAssetManager().get("inserter_hand.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		
		sprite2 =  new Sprite(texture2);
		sprite2.setSize(((InserterL)this.instance).width_hand*GameStage.singleton.scale(), ((InserterL)this.instance).height_hand*GameStage.singleton.scale());
		sprite2.setOrigin(((InserterL)this.instance).width_hand*GameStage.singleton.scale(), ((InserterL)this.instance).height_hand*GameStage.singleton.scale()/2);
		int direction=((InserterL)this.instance).getDirection();
		if(direction!=4)
		{
			sprite.rotate(-90*direction);
			sprite2.rotate(-90*direction);
		}
	}
	
	@Override
	public void update(float delta)
	{
		this.sprite2.rotate(this.instance.handler());
	}
	

}
