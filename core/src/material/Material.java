package material;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.groundup.game.GameStage;

import conveyor.Conveyor;
import graphic.ActorExtension;

public class Material extends ActorExtension {
	
	public int current_row;
	public int current_col;
	
	public static int width=4;
	public static int height=4;
	
	public String type;

	private void createTexture(String type) {
		this.type=type;
		
		Texture texture = this.game.getGame().getAssetManager().get(type+".png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
	}
	
	public Material(GameStage game,int row, int col) {
		this.game=game;
		this.current_col = col;
		this.current_row = row;
		this.setWidth(width);
		this.setHeight(height);
		this.createTexture("iron_plate");
		this.setPosition(row, col);
	}
	
	public void setCurrentPos(int row, int col) {
		this.current_row=row;
		this.current_col=col;
		this.setPosition(col, row);
	}
	
	public int getRow() {
		return this.current_row;
	}
	public int getCol() {
		return this.current_col;
	}
	
	private void moveTo(int new_row,int new_col)
	{
		this.game.materials().removeMaterialFromMap(this);
		this.setPosition(new_row, new_col);
		this.game.materials().addMaterialToMap(this);
	}

	public void moveMaterial(int row_movement, int col_movement) {
		int new_row = (int) (this.getX() + row_movement);
		int new_col = (int) (this.getY() + col_movement);
		ArrayList<Actor> elements = this.game.map().getPixelMap(new_row+Material.width/2, new_col+Material.height/2);
		for (Actor it : elements) {
			if (Conveyor.class.isAssignableFrom(it.getClass()) || this==it) {

			} else {
				return;
			}
		}
		this.moveTo(new_row, new_col);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
