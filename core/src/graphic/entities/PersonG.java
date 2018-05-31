package graphic.entities;

import com.badlogic.gdx.graphics.Texture;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.Entity;
import logic.entities.PersonL;

public class PersonG extends ActorExtension{
	
	public PersonG() {
		this.instance= null;
	}
	
	public PersonG(Entity in) {
		this.instance=in;
		super.create();
	}

	@Override
	public void update(float delta) {
		if(this.instance!=null)
		{
			this.setPosition(this.instance.getPosX()*GameStage.singleton.scale(),this.instance.getPosY()*GameStage.singleton.scale());
			if(((PersonL)this.instance).id==1)
			{
				this.setVisible(false);
				this.instance=null;
				GameStage.singleton.unused_person.add(this);
				GameStage.singleton.people().removePerson(this);
			}
			else if(((PersonL)this.instance).id==0)
			{
				this.setVisible(true);
				((PersonL)this.instance).updatePersonPos();
			}
		}
	}
	
	public void setInstance(PersonL mat)
	{
		this.instance=mat;
		super.create();
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[1];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("worker.png");
		return null;
	}
	
}
