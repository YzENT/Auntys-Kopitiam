package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class AboutScreen extends ScreenAdapter {

	GameAssets game;

	public AboutScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		// adjusting font data
		GameAssets.font.getData().setScale((float) 0.5);
		GameAssets.font.setColor(1, 1, 1, 1);
		GameAssets.layout.setText(GameAssets.font, GameAssets.AboutMessage);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 0); // clear screen
		InputDetection(); //check input for every frame
		GameAssets.batch.begin(); // begin rendering

		// Render MainMenuBg
		GameAssets.batch.draw(
			GameAssets.MainMenuBg_Blur, 
			0, 
			0, 
			GameAssets.MainMenuBg_Blur.getWidth(),
			GameAssets.MainMenuBg_Blur.getHeight()
		);

		// render AboutMessage
		GameAssets.font.draw(
			GameAssets.batch, 
			GameAssets.AboutMessage,
			(Gdx.graphics.getWidth() - GameAssets.layout.width) / 2,
			(Gdx.graphics.getHeight() / 2) + (GameAssets.layout.height / 2)
		);

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // in game mouse

	}

	@Override
	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();
		GameAssets.MainMenuBg_Blur.dispose();
		GameAssets.batch.dispose();
		GameAssets.font.dispose();
		GameAssets.InGameMouse.dispose();
	}
	
	public void InputDetection() {
		// Return to main menu when ESC key is pressed
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}
	}
}