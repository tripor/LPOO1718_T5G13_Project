package person.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GroundUpGame;
import person.Person;

public class Worker extends Person {
	
	private void createWorker(GroundUpGame game)
	{
		Texture texture=game.getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	public Worker(GroundUpGame game, int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createWorker(game);
		this.setPosition(top, left);
	}
	
	
	public Worker() {
		super();
	}

}
