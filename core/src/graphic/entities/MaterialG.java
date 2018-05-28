package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.entities.MaterialL;
/**
 * Class that handles the Graphical part of the material
 *
 */
public class MaterialG extends ActorExtension{
	
	private void createTexture(String type) {
		((MaterialL)this.instance).setType(type);
		
		Texture texture = this.game.getGame().getAssetManager().get(type+".png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		sprite.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}

	public MaterialG(GameStage game)
	{
		this.instance=null;
		this.game=game;
	}
	public MaterialG(GameStage game,MaterialL mat)
	{
		this.instance=mat;
		this.game=game;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createTexture(((MaterialL)this.instance).getType());
		this.setPosition(mat.getPosX(), mat.getPosY());
	}
	
	@Override
	public void update(float delta) {
		if(this.instance!=null)
		{
			this.setPosition(this.instance.getPosX(),this.instance.getPosY());
			if(((MaterialL)this.instance).id==1)
			{
				this.setVisible(false);
				this.instance=null;
				this.game.unused_material.add(this);
				this.game.materials().removeMaterial(this);
			}
			else if(((MaterialL)this.instance).id==0)
			{
				this.setVisible(true);
			}
		}
		
	}
	
	public void setInstance(MaterialL mat)
	{
		this.instance=mat;
		this.setWidth(this.instance.getWidth());
		this.setHeight(this.instance.getHeight());
		this.createTexture(((MaterialL)this.instance).getType());
		this.setPosition(mat.getPosX(), mat.getPosY());
	}

}
