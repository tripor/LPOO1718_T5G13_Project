package material;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GameStage;

import graphic.ActorExtension;
import logic.path.byAStar.AStar;
import logic.path.byAStar.Node;

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
		
		this.setDebug(true);
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
	
	

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
}
