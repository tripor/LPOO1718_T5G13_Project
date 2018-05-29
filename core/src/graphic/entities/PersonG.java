package graphic.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import graphic.ActorExtension;
import graphic.GameStage;
import logic.entities.PersonL;

public class PersonG extends ActorExtension{
	
	private void createWorker()	{
		
		Texture texture = this.game.getGame().getAssetManager().get("worker.png");
		
		sprite = new Sprite(texture);
		sprite.setSize(this.getWidth(), this.getHeight());
		
		this.setDebug(true);
	}
	
	public PersonG(GameStage game) {
		this.instance= null;
		this.game=game;
	}
	
	public PersonG(GameStage game, PersonL in) {
		this.instance=in;
		this.game=game;
		this.setWidth(this.instance.getWidth()*game.scale());
		this.setHeight(this.instance.getHeight()*game.scale());
		this.createWorker();
		this.setPosition(this.instance.getPosX()*game.scale(), this.instance.getPosY()*game.scale());
	}

	@Override
	public void update(float delta) {
		if(this.instance!=null)
		{
			this.setPosition(this.instance.getPosX()*game.scale(),this.instance.getPosY()*game.scale());
			if(((PersonL)this.instance).id==1)
			{
				this.setVisible(false);
				this.instance=null;
				this.game.unused_person.add(this);
				this.game.people().removePerson(this);;
			}
			else if(((PersonL)this.instance).id==0)
			{
				this.setVisible(true);
			}
		}
	}
	
	public void setInstance(PersonL mat)
	{
		this.instance=mat;
		this.setWidth(this.instance.getWidth()*game.scale());
		this.setHeight(this.instance.getHeight()*game.scale());
		this.createWorker();
		this.setPosition(mat.getPosX()*game.scale(), mat.getPosY()*game.scale());
	}
	
}
