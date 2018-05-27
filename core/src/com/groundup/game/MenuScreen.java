package com.groundup.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import graphic.MenuStage;

public class MenuScreen extends ScreenAdapter {
	/**
	 * The game it self
	 */
	private final MenuStage game;

	/**
	 * Constructor for the Game Screen
	 * 
	 * @param game
	 *            The Game
	 */
	public MenuScreen(GroundUpGame game) {
		this.game = new MenuStage(game);
		InputProcessor inputProcessorOne = this.game;
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(inputProcessorOne);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		// Clear screen
		Gdx.gl.glClearColor(103 / 255f, 69 / 255f, 117 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		game.act();
		game.draw();
	}

	/**
	 * Resize the stage viewport when the screen is resized
	 *
	 * @param width
	 *            the new screen width
	 * @param height
	 *            the new screen height
	 */
	@Override
	public void resize(int width, int height) {
		game.getViewport().update(width, height, true);
	}
}
