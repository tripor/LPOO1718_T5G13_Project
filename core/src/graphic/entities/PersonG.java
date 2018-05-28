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
		this.instance= new PersonL(posX, posY, map);
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createWorker();
		this.setPosition(this.instance.getPosX(), this.instance.getPosY());
	}
	
	public PersonG(GameStage game, PersonL in) {
		this.instance=in;
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.VIEWPORT_WIDTH/256);
		this.setHeight(this.instance.getHeight()*game.VIEWPORT_WIDTH/256);
		this.createWorker();
		this.setPosition(this.instance.getPosX()*game.VIEWPORT_WIDTH/256, this.instance.getPosY()*game.VIEWPORT_WIDTH/256);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
