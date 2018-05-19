package conveyor;

import graphic.ActorExtension;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;
import material.Material;

public class Conveyor extends ActorExtension{
	
	private int velocity=1;
	private int movement_row = 0;
	private int movement_col = 0;
	public static int width=10;
	public static int height=10;
	private int direction;
	
	private void createConveyor() {
		
		Texture texture = this.game.getGame().getAssetManager().get("conveyor1.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		if(this.direction!=4)
			sprite.rotate(-90*direction);
		
		//this.setDebug(true);
	}

	public Conveyor(GameStage game,int row, int col, int width, int height, int direction) {
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
	
	
	
	
	/*public boolean connectedFrom(Place p) {
		
		// we need to get it's REVERSED direction
		// in order to know whether this conveyor can get things from the building
		
		int movement_row = 0 - this.movement_row;
		int movement_col = 0 - this.movement_col;
		
		if(p.including(this.row - movement_row, this.col - movement_col)) {
			return true;
		}
		return false;
	}

	
	public int[] checkNextGrid(Conveyor thisObj, int row_delta, int col_delta) {

		int row = thisObj.getRow() + row_delta,
			col = thisObj.getCol() + col_delta;
		
		if(this.game.conveyors().getConveyor(row, col) != null) {
			int[] result = {row, col};
			return result;
		}
		return null;
	}
	
	public int[] next(Conveyor thisObj) {
		
		boolean is_intersection = thisObj.getRowMovement() == 0
									&& thisObj.getColMovement() == 0;
		
		if(is_intersection) {
			
			int[] tmp = null;
			
			for(int row = -1; row <=1; row++) {
				for(int col = -1; col<=1; col++) {

					// TODO: T-shape conveyors, need to make product self-balancing.
					tmp = checkNextGrid(thisObj, row, col);
					if(tmp != null) {
						return tmp;
					}
				}
			}
			return null;
		}
		else {
			return checkNextGrid(thisObj, thisObj.getRowMovement(), thisObj.getColMovement());
		}	
	}*/

	@Override
	public void update(float delta) {
		this.moveMaterials();
		
	}
}
