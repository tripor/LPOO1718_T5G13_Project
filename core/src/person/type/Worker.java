package person.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GroundUpGame;
import person.Person;

public class Worker extends Person {
	
	private void createWorker()	{
		
		Texture texture = GroundUpGame.getInstance().getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public Worker(int row, int col) {
		super(row, col);
		this.setWidth(20);
		this.setHeight(20);
		this.createWorker();
		this.setPosition(col, row);
	}

}
