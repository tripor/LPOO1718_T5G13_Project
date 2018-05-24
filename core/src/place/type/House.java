package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GameStage;
import place.Place;

public class House extends Place {

	public static int width=30;
	public static int height=30;

	private void createHouse() {
		
		Texture texture = this.game.getGame().getAssetManager().get("nothing.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		if(this.door_atBorder!=4)
			sprite.rotate(-90*door_atBorder);
		
		this.setDebug(true);
	}
	
	public House(GameStage game,int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(game,top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createHouse();
		this.setPosition(top, left);
	}
	
	public House(){
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void reconstruct(GameStage game) {
		this.game=game;
		this.createHouse();
		this.positionChanged();
	}
}
