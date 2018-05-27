package com.groundup.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import graphic.GameStage;
import graphic.control.UserControl;
/**
 * 
 * Class that deals with the Screen for the game
 *
 */
public class GroundUpScreen extends ScreenAdapter {
	/**
	 * The game it self
	 */
	private final GameStage game;
	/**
	 * Control for the user
	 */
	public final UserControl userControl;

    /**
     * Controls all the conveyor frames on the screen
     */
    public float conveyorStateTime=0;
    
	
	/**
	 * Constructor for the Game Screen
	 * @param game The Game
	 */
	public GroundUpScreen(GroundUpGame game) {
		this.game=new GameStage(game,this);
		userControl = new UserControl(this.game);
		InputProcessor inputProcessorOne = this.game;
		InputProcessor inputProcessorTwo = this.userControl;
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(inputProcessorOne);
		inputMultiplexer.addProcessor(inputProcessorTwo);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		// Clear screen
		Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		this.userControl.InputHandler(); 
		
		
		this.conveyorStateTime+=Gdx.graphics.getDeltaTime();
		this.game.updateMaterials();

		game.act();
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
    /**
     * 
     * @return Returns the game stage
     */
	public GameStage getGame() {
		return game;
	}
    
    
}
