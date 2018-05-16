package material.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GameStage;

import logic.storage.Demand;
import material.Material;

public class IronPlate extends Material {

	public static int width=4;
	public static int height=4;

	private void createIronPlate() {
		
		Texture texture = this.game.getGame().getAssetManager().get("nothing.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		
		this.setDebug(true);
	}
	
	public IronPlate(GameStage game,int top, int left) {
		super(game,top, left);
		this.setWidth(width);
		this.setHeight(height);
		this.createIronPlate();
		this.setPosition(left, top);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
