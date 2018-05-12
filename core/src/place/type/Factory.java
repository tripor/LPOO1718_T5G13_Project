package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GameStage;
import graphic.GroundUpGame;
import place.Place;

public class Factory extends Place {
	
	public static int width=20;
	public static int height=20;

	private void createFactory() {
		
		Texture texture = this.game.getGame().getAssetManager().get("factory.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public Factory(GameStage game,int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(game,top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactory();
		this.setPosition(left, top);
	}

}
