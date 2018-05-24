package person.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GroundUpGame;

import graphic.GameStage;
import person.Person;

public class Worker extends Person {
	
	private void createWorker()	{
		
		Texture texture = this.game.getGame().getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public Worker(GameStage game,int row, int col) {
		super(game,row, col);
		this.setWidth(5);
		this.setHeight(5);
		this.createWorker();
		this.setPosition(col, row);
	}
	
	public Worker() {}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reconstruct(GameStage game) {
		this.game=game;
		this.createWorker();
		this.positionChanged();
		
	}

}
