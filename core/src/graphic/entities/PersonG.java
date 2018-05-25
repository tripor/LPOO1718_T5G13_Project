package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.entities.InserterL;
import logic.entities.PersonL;

public class PersonG extends ActorExtension{
	
	private void createWorker()	{
		
		Texture texture = this.game.getGame().getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public PersonG(GameStage game,int posX, int posY) {
		this.instance= new PersonL(posX,posY);
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createWorker();
		this.setPosition(this.instance.getPosX(), this.instance.getPosY());
	}
	
	public PersonG(GameStage game,InserterL in) {
		this.instance=in;
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createWorker();
		this.setPosition(this.instance.getPosX(), this.instance.getPosY());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}