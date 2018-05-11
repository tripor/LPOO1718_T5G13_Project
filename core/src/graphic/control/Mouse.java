package graphic.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.map.Map;

public class Mouse extends ActorExtension {
	
	private boolean isSelected=false;
	
	public Mouse(GameStage game)
	{
		this.game=game;
	}
	
	
	public void createMouse(String type,int width, int height) {
		
		Texture texture = this.game.getGame().getAssetManager().get(type);
		
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		
		this.setDebug(true);
		
		this.isSelected=true;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		if(this.isSelected)
		{
			Vector3 mouse_pos = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			this.game.getViewport().unproject(mouse_pos);
			this.setPosition(((int)(mouse_pos.x/Map.division))*10,((int)(mouse_pos.y/Map.division))*10);
			sprite.draw(batch);
		}
	}

}
