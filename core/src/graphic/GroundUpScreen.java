package graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import graphic.control.UserControl;
import logic.console.Console;
/**
 * 
 * Class that deals with the Screen for the game
 *
 */
public class GroundUpScreen extends ScreenAdapter {
	/**
	 * The game it self
	 */
	private final GameStage game = GameStage.getInstance();
	private final UserControl userControl;
    
	public GroundUpScreen() {
		userControl = new UserControl();
		Gdx.input.setInputProcessor(this.game);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		// Clear screen
		Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		this.userControl.InputHandler();
		
		// Console.log("DELTA=" + delta + "s");
		game.draw();
	}
	/**
     * Resize the stage viewport when the screen is resized
     *
     * @param width the new screen width
     * @param height the new screen height
     */
    @Override
    public void resize(int width, int height) {
        game.getViewport().update(width, height, true);
    }

}
