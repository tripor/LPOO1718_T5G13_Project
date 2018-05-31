package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.MaterialL;
/**
 * Class that handles the Graphical part of the material
 *
 */
public class MaterialG extends ActorExtension{

	public MaterialG()
	{
		this.instance=null;
	}
	public MaterialG(Entity mat)
	{
		this.instance=mat;
		super.create();
	}
	
	@Override
	public void update(float delta) {
		if(this.instance!=null)
		{
			this.setPosition(this.instance.getPosX()*GameStage.singleton.scale(),this.instance.getPosY()*GameStage.singleton.scale());
			if(((MaterialL)this.instance).getId()==1)
			{
				this.setVisible(false);
				this.instance=null;
				GameStage.singleton.unused_material.add(this);
				GameStage.singleton.materials().removeMaterial(this);
			}
			else if(((MaterialL)this.instance).getId()==0)
			{
				this.setVisible(true);
			}
		}
		
	}
	
	public void setInstance(MaterialL mat)
	{
		this.instance=mat;
		super.create();
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get(((MaterialL)this.instance).getType()+".png");
		return frames;
	}

}
