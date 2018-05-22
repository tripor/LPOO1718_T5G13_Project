package place.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GameStage;

import logic.storage.Demand;
import material.Material;
import place.Place;
/**
 * Class that handle the IronMine logic
 *
 */
public class IronMine extends Place {

	public static int width=20;
	public static int height=20;
	
	private int time_make_material=100;
	private int time=0;

	public Demand _demand = new Demand();

	private void createFactory() {
		
		Texture texture = this.game.getGame().getAssetManager().get("iron_mine.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		if(this.door_atBorder!=4)
			sprite.rotate(-90*door_atBorder);
	}
	
	public IronMine(GameStage game,int top, int left, int width, int height, int doorAtBorder, int doorAtPx) {
		super(game,top, left, width, height, doorAtBorder, doorAtPx);
		this.setWidth(width);
		this.setHeight(height);
		this.createFactory();
		this.setPosition(top, left);
	}
	
	@Override
	public void update(float delta) {
		time++;
		if(time>=this.time_make_material)
		{
			time=0;
			this.addToStorage(this.game.removeUnusedMaterial());
		}
		
	}
	

}
