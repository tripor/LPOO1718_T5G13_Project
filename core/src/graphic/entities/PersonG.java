package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Map;
import logic.entities.InserterL;
import logic.entities.PersonL;

public class PersonG extends ActorExtension{
	
	private void createWorker()	{
		
		Texture texture = this.game.getGame().getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public PersonG(GameStage game, Map map, int posX, int posY) {
		this.instance= new PersonL((int)(posX*game.reverseScale()),(int)( posY*game.reverseScale()), map);
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.scale());
		this.setHeight(this.instance.getHeight()*game.scale());
		this.createWorker();
		this.setPosition(this.instance.getPosX()*game.scale(), this.instance.getPosY()*game.scale());
	}
	
	public PersonG(GameStage game, PersonL in) {
		this.instance=in;
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.scale());
		this.setHeight(this.instance.getHeight()*game.scale());
		this.createWorker();
		this.setPosition(this.instance.getPosX()*game.scale(), this.instance.getPosY()*game.scale());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
