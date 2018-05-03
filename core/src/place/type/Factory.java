package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.GroundUpGame;
import place.Place;

public class Factory extends Place {

	private void createFactory() {
		
		Texture texture = GroundUpGame.getInstance().getAssetManager().get("factory.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public Factory(int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactory();
		this.setPosition(left, top);
	}

}
