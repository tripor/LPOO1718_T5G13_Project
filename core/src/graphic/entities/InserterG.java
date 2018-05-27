package graphic.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
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
	/**
	 * Creates the sprite for the class
	 */
	private void createInserter() {
		
		
		Texture texture = this.game.getGame().getAssetManager().get("inserter_base.png");
		Texture texture2 = this.game.getGame().getAssetManager().get("inserter_hand.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
		
		sprite2 =  new Sprite(texture2);
		sprite2.setSize(((InserterL)this.instance).width_hand, ((InserterL)this.instance).height_hand);
		sprite2.setOrigin(((InserterL)this.instance).width_hand, ((InserterL)this.instance).height_hand/2);
		int direction=((InserterL)this.instance).getDirection();
		if(direction!=4)
		{
			sprite.rotate(-90*direction);
			sprite2.rotate(-90*direction);
		}
	}
	
	public InserterG(GameStage game,int posX,int posY,int direction)
	{
		this.instance=new InserterL(posX,posY,direction);
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createInserter();
		this.setPosition(this.instance.getPosX(), this.instance.getPosY());
		this.sprite2.setPosition(this.instance.getPosX()-(((InserterL)this.instance).width_hand-2), this.instance.getPosY());
	}
	
	public InserterG(GameStage game,InserterL in)
	{
		this.instance=in;
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createInserter();
		this.setPosition(this.instance.getPosX(), this.instance.getPosY());
		this.sprite2.setPosition(this.instance.getPosX()-(((InserterL)this.instance).width_hand-2), this.instance.getPosY());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
		sprite2.draw(batch);
		if(!game.menuOpen)
			this.update(Gdx.graphics.getDeltaTime());
	}
	
	
	@Override
	public void update(float delta) {
		this.sprite2.rotate(((InserterL)this.instance).handler(this.game.map()));
		
		
	}
	

}
