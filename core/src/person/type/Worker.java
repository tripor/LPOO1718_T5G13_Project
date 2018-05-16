package person.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.groundup.game.GameStage;
import graphic.GroundUpGame;
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

}
