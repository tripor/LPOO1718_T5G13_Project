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
		sprite2.setSize(((InserterL)this.instance).width_hand*game.scale(), ((InserterL)this.instance).height_hand*game.scale());
		sprite2.setOrigin(((InserterL)this.instance).width_hand*game.scale(), ((InserterL)this.instance).height_hand*game.scale()/2);
		int direction=((InserterL)this.instance).getDirection();
		if(direction!=4)
		{
			sprite.rotate(-90*direction);
			sprite2.rotate(-90*direction);
		}
	}
	
	public InserterG(GameStage game, int posX, int posY, int direction) {
		this.instance = new InserterL((int)(posX * game.reverseScale()),(int)( posY * game.reverseScale()), direction);
		this.game = game;
		this.setWidth(this.instance.getWidth() * game.scale());
		this.setHeight(this.instance.getHeight() * game.scale());
		this.createInserter();
		this.setPosition(this.instance.getPosX() * game.scale(), this.instance.getPosY() * game.scale());
		this.sprite2.setPosition(
				this.instance.getPosX() * game.scale()
						- (((InserterL) this.instance).width_hand * game.scale() - 2 * game.scale()),
				this.instance.getPosY() * game.scale());
	}

	public InserterG(GameStage game, InserterL in) {
		this.instance = in;
		this.game = game;
		this.setWidth(this.instance.getWidth() * game.scale());
		this.setHeight(this.instance.getHeight() * game.scale());
		this.createInserter();
		this.setPosition(this.instance.getPosX() * game.scale(), this.instance.getPosY() * game.scale());
		this.sprite2.setPosition(
				this.instance.getPosX() * game.scale()
						- (((InserterL) this.instance).width_hand * game.scale() - 2 * game.scale()),
				this.instance.getPosY() * game.scale());
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
