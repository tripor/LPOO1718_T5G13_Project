package icon;

import graphic.ActorExtension;
import graphic.GameStage;

public class Icon extends ActorExtension {

	protected final float pos_x;
	protected final float pos_y;
	
	public Icon(GameStage game,int pos_x,int pos_y)
	{
		this.game=game;
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
}
