package icon;

import com.badlogic.gdx.graphics.g2d.Batch;

import graphic.ActorExtension;
import com.groundup.game.GameStage;

public abstract class Icon extends ActorExtension {

	protected final float pos_x;
	protected final float pos_y;
	
	protected boolean visible;
	
	public Icon(GameStage game,int pos_x,int pos_y)
	{
		this.game=game;
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		this.setPosition(this.game.getCamera().position.x + this.pos_x - (GameStage.VIEWPORT_WIDTH/2), this.game.getCamera().position.y + this.pos_y -(GameStage.VIEWPORT_HEIGHT/2));
		sprite.draw(batch);
	}


}
