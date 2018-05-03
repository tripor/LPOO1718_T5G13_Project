package person;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import logic.console.Console;
import logic.map.Map;
import logic.path.byAStar.AStar;
import logic.path.byAStar.Node;

public class Person extends Actor{
	
	protected Sprite sprite;
	
	public int current_row;
	public int current_col;
	
	public int target_row;
	public int target_col;
	
	private Map map = Map.getInstance();
	public String unique_id = "undefined";
	
	List<Node> path = new ArrayList<Node>();
	
	public Person() {
	}
	
	public Person(String id) {
		this.unique_id = id;
	}
	
	public String getId() {
		return this.unique_id;
	}
	
	public List<Node> getPath(int _target_row, int _target_col, boolean should_replace_global) {

		this.target_row = _target_row;
		this.target_col = _target_col;
		
		Node initialNode = new Node(current_row, current_col);
        Node finalNode = new Node(target_row, target_col);
        int rows = map.getMapHeight();
        int cols = map.getMapWidth();
        
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        aStar.setBlocks(map.getPlaceList());
        
        List<Node> thisPath = aStar.findPath();
        
        if(should_replace_global) {
            this.path = thisPath;
        }
        return thisPath;
	}
	
	public List<Node> getPath(int _target_row, int _target_col){
		return getPath(_target_row, _target_col, true);
	}
	
	public void setCurrentRow(int row) {
		this.current_row = row;
	}
	public void setCurrentCol(int col) {
		this.current_col = col;
	}
	
	public Node popPath() {
		
		Node latestNode = path.remove(0);
		
		while (Math.abs(latestNode.getCol() - current_col) > 1
			|| Math.abs(latestNode.getRow() - current_row) > 1) {
			
			List<Node> smallerPath = this.getPath(latestNode.getRow(), latestNode.getCol(), false);
//			Console.log(">> " + smallerPath.size() + " steps added.");
			
			for(Node n : path) {
				smallerPath.add(n);
			}
			// performance VS smallerPath.addAll(path);
			
			path = smallerPath;
			latestNode = path.remove(0);
		}
		
		current_col = latestNode.getCol();
		current_row = latestNode.getRow();
		
//		Console.log(this.toString());

		return latestNode;
	}

	public String toString() {
		return "[Person " + unique_id + "] "
				+ " Row " + current_row
				+ " Col " + current_col;
	}
	
	public int getRow() {
		return this.current_row;
	}
	public int getCol() {
		return this.current_col;
	}
	
	@Override
	public void setPosition(float x,float y)
	{
		super.setPosition(x, y);
	}
	
	@Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
    }
	
	@Override
    protected void rotationChanged() {
        super.rotationChanged();
        sprite.setRotation(getRotation());
    }
	
	@Override
    public void act(float delta) {
        super.act(delta);
    }
	
	@Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
	public void sizePlace(float amountX,float amountY)
	{
		this.setWidth(amountX);
		this.setHeight(amountY);
		sprite.setSize(this.getWidth(), this.getHeight());
	}
}
