package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.groundup.game.GameStage;
import graphic.GroundUpGame;
import place.Place;

public class Factory extends Place {
	
	public static int width=40;
	public static int height=40;

	private void createFactory() {
		
		Texture texture = this.game.getGame().getAssetManager().get("factory.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		if(this.door_atBorder!=4)
			sprite.rotate(-90*door_atBorder);
		
		this.setDebug(true);
	}
	
	public Factory(GameStage game,int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(game,top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactory();
		this.setPosition(top, left);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
