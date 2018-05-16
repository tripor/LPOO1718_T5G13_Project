package material;

import java.util.ArrayList;
import java.util.List;

import com.groundup.game.GameStage;

import graphic.ActorExtension;
import logic.path.byAStar.AStar;
import logic.path.byAStar.Node;

public abstract class Material extends ActorExtension {
	
	public int current_row;
	public int current_col;
	
	public Material(GameStage game,int row, int col) {
		this.game=game;
		this.current_col = col;
		this.current_row = row;
	}
	
	public void setCurrentRow(int row) {
		this.current_row = row;
	}
	public void setCurrentCol(int col) {
		this.current_col = col;
	}
	
	public void setCurrentPos(int row, int col) {
		setCurrentRow(row);
		setCurrentCol(col);
		this.setPosition(col, row);
	}
	
	public int getRow() {
		return this.current_row;
	}
	public int getCol() {
		return this.current_col;
	}
}
