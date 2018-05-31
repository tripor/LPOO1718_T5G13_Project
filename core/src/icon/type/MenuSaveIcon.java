package icon.type;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import graphic.GameStage;
import icon.Icon;
import icon.MenuSaveHolder;

public class MenuSaveIcon extends Icon  { 
	
	public MenuSaveIcon(int posX,int posY,final int width,final int height) {
		super(posX,posY,width,height);
		
		this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            	if(GameStage.singleton.icons().getHolder()!=4)
            	{
            		GameStage.singleton.icons().setHolder(4);
            		((MenuSaveHolder)GameStage.singleton.icons().getCurrentHolder()).setText("");
            	}
            	else
            	{
            		GameStage.singleton.saveGame(((MenuSaveHolder)GameStage.singleton.icons().getCurrentHolder()).getText());
            		GameStage.singleton.icons().setHolder(3);
            	}
            }
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
            {
    	        sprite.setRegion(animation.getKeyFrame(1, false));
            }
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor)
            {
    	        sprite.setRegion(animation.getKeyFrame(0, false));
            }
        });
	}

	@Override
	public void update(float delta) {
	}

	@Override
	protected Texture[] createTexture() {
		Texture[] frames=new Texture[2];
		frames[0] = GameStage.singleton.getGame().getAssetManager().get("menu_save.png");
		frames[1] = GameStage.singleton.getGame().getAssetManager().get("menu_save_mouse.png");
		return frames;
	}
}
