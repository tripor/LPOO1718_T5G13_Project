package conveyor;

import graphic.ActorExtension;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;
import material.Material;

public class Conveyor extends ActorExtension{
	
	private int velocity = 1;
	private int movement_row = 0;
	private int movement_col = 0;
	public static int width  = 10;
	public static int height = 10;
	private int direction;
	
	private void createConveyor() {
		
		Texture[] frames=new Texture[12];
		
		frames[0] = this.game.getGame().getAssetManager().get("conveyor1.png");
		frames[1] = this.game.getGame().getAssetManager().get("conveyor2.png");
		frames[2] = this.game.getGame().getAssetManager().get("conveyor3.png");
		frames[3] = this.game.getGame().getAssetManager().get("conveyor4.png");
		frames[4] = this.game.getGame().getAssetManager().get("conveyor5.png");
		frames[5] = this.game.getGame().getAssetManager().get("conveyor6.png");
		frames[6] = this.game.getGame().getAssetManager().get("conveyor7.png");
		frames[7] = this.game.getGame().getAssetManager().get("conveyor8.png");
		frames[8] = this.game.getGame().getAssetManager().get("conveyor9.png");
		frames[9] = this.game.getGame().getAssetManager().get("conveyor10.png");
		frames[10] = this.game.getGame().getAssetManager().get("conveyor11.png");
		frames[11] = this.game.getGame().getAssetManager().get("conveyor12.png");
		
		this.animation= new Animation<Texture>(.05f,frames);
		
		sprite = new Sprite(animation.getKeyFrame(0));
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		if(this.direction!=4)
			sprite.rotate(-90*direction);
	}

	public Conveyor(GameStage game, int row, int col, int width, int height, int direction) {
		this.game=game;
		this.setWidth(width);
		this.setHeight(height);
		this.direction=direction;
		this.createConveyor();
		this.setPosition(row, col);
		
		if(direction == 1) {	// Upwards
			movement_col = +this.velocity;
		}
		else if(direction == 2) {	// go right
			movement_row = +this.velocity;
		}
		else if(direction == 3) {	// Downwards
			movement_col = -this.velocity;
		}
		else if(direction == 4) {	// go left
			movement_row = -this.velocity;
		}
	}
	
	public Conveyor() {}
	
	public int getRowMovement() {
		return movement_row;
	}
	public int getColMovement() {
		return movement_col;
	}
	
	public void moveMaterials()
	{
		ArrayList<Actor> elements= this.game.map().getMap((int)this.getX(),(int)this.getY());
		ArrayList<Material> to_move = new ArrayList<Material>();
		for(Actor it:elements)
		{
			if(Material.class.isAssignableFrom(it.getClass()))
			{
				to_move.add((Material) it);
			}
		}
		for (Material it : to_move) {
			if (this.getX() <= it.getX() + it.getWidth() / 2
					&& it.getX() + it.getWidth() / 2 <= this.getX() + this.getWidth()
					&& this.getY() <= it.getY() + it.getHeight() / 2
					&& it.getY() + it.getHeight() / 2 <= this.getY() + this.getHeight())
			it.moveMaterial(movement_row, movement_col);
		}
		
	}
	
	

	public int getDirection() {
		return direction;
	}

	@Override
	public void update(float delta) {
		this.moveMaterials();
        sprite.setRegion(animation.getKeyFrame(this.game.game.getGameScreen().conveyorStateTime, true));
		
	}
	
	public void reconstruct(GameStage game) {
		this.game=game;
		this.createConveyor();
		this.positionChanged();
	}
}
