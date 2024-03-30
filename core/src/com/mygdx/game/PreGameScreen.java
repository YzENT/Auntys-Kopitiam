package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class PreGameScreen extends ScreenAdapter {

	GameAssets game;

	public PreGameScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		// adjusting font data
		GameAssets.font.setColor(1, 1, 1, 1);
		GameAssets.font.getData().setScale(1);
		GameAssets.layout.setText(GameAssets.font, GameAssets.PreGameMessage);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1); // clear screen
		InputDetection(); // checks for input every frame
		GameAssets.batch.begin(); // begin rendering
		GameAssets.batch.draw(GameAssets.CafeBg_Blur, 0, 0); // draw background image

		// render message
		GameAssets.font.draw(GameAssets.batch, GameAssets.PreGameMessage,
				(Gdx.graphics.getWidth() - GameAssets.layout.width) / 2,
				(Gdx.graphics.getHeight() / 2) + (GameAssets.layout.height / 2));

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // in game mouse
	}

	public void dispose() {
		GameAssets.font.dispose();
		GameAssets.batch.dispose();
		GameAssets.InGameMouse.dispose();
	}

	public void InputDetection() {
		if (Gdx.input.justTouched()) {
			game.setScreen(new GameScreen(game));
			
		}
	}

}
