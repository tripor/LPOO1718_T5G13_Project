package inserter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.groundup.game.GameStage;

import graphic.ActorExtension;

public class Inserter extends ActorExtension{
	int row, col;
	public static int width=4;
	public static int height=4;
	
	public static int width_hand=8;
	public static int height_hand=4;
	private int direction;
	
	private boolean isRotating = false;
	private boolean rotationDirection=true;
	private int rotating_quantity=0;
	private int rotating_velocity=5;
	
	
	
	private Sprite sprite2;
	
	private void createInserter() {
		
		Texture texture = this.game.getGame().getAssetManager().get("inserter_base.png");
		Texture texture2 = this.game.getGame().getAssetManager().get("inserter_hand.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(width/2, height/2);
		
		sprite2 =  new Sprite(texture2);
		sprite2.setSize(width_hand, height_hand);
		sprite2.setOrigin(width_hand, height_hand/2);
		
		if(this.direction!=4)
		{
			sprite.rotate(-90*direction);
			sprite2.rotate(-90*direction);
		}
		
		this.setDebug(true);
	}

	public Inserter(GameStage game,int col, int row, int width, int height, int direction) {
		this.game=game;
		this.setWidth(width);
		this.setHeight(height);
		this.direction=direction;
		this.createInserter();
		this.setPosition(row, col);
		this.sprite2.setPosition(row-6, (float) (col));
		this.row = row;
		this.col = col;
		this.rotateHand();
	}

	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	public void rotateHand()
	{
		this.isRotating=true;
		this.rotating_quantity=0;
		this.rotationDirection=true;
	}

	@Override
	public void update(float delta) {
		if(this.isRotating)
		{
			if(this.rotationDirection)
			{
				this.sprite2.rotate(-rotating_velocity);
				this.rotating_quantity-=this.rotating_velocity;
				if(this.rotating_quantity<=-180)
				{	
					this.rotationDirection=false;
					this.sprite2.rotate(this.sprite2.getRotation()+180);
					this.rotating_quantity=-180;
				}
			}
			else
			{
				this.sprite2.rotate(rotating_velocity);
				this.rotating_quantity+=this.rotating_velocity;
				if(this.rotating_quantity>=0)
				{	
					this.isRotating=false;
					this.sprite2.rotate(-this.sprite2.getRotation());
					this.rotating_quantity=0;
				}
			}
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		sprite.draw(batch);
		sprite2.draw(batch);
		this.update(Gdx.graphics.getDeltaTime());
	}
}
