package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class PostGameScreen extends ScreenAdapter {

	GameAssets game;

	public PostGameScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		// adjusting font data
		GameAssets.font.setColor(1, 1, 1, 1);
		GameAssets.font.getData().setScale(1);
		GameAssets.layout.setText(GameAssets.font, GameAssets.PostGameMessage);
		Buttons();
	}
	
	public void Buttons() {
		GameAssets.MassRender = new ButtonBehaviour[1];
		
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.GoBackMainMenu,
				((Gdx.graphics.getWidth() / 2) - GameAssets.GoBackMainMenu.getWidth() *0.7f / 2),
				330,
				GameAssets.GoBackMainMenu.getWidth() * 0.7f, GameAssets.GoBackMainMenu.getHeight() * 0.7f, game);
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1); // clear screen
		InputDetection(); // checks for input every frame
		GameAssets.batch.begin(); // begin rendering
		GameAssets.batch.draw(GameAssets.CafeBg_Blur, 0, 0); // draw background image
		
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.render();
		}

		if (GameAssets.ScoreCount > 9) {
			GameAssets.font.draw(GameAssets.batch, GameAssets.PostGameMessage.concat(Integer.toString(GameAssets.ScoreCount)), 600,
			Gdx.graphics.getHeight() - 340);
		}
		else {
			GameAssets.font.draw(GameAssets.batch, GameAssets.PostGameMessage.concat(Integer.toString(GameAssets.ScoreCount)), 640,
			Gdx.graphics.getHeight() - 340);	
		}
		
	    if (GameAssets.HighScore > 9) {
	    GameAssets.font.draw(GameAssets.batch, GameAssets.HighScoreMessage.concat(Integer.toString(GameAssets.HighScore)), 650,
	    		Gdx.graphics.getHeight() - 450);
	    }
	    else {
	    	GameAssets.font.draw(GameAssets.batch, GameAssets.HighScoreMessage.concat(Integer.toString(GameAssets.HighScore)), 690,
	    			Gdx.graphics.getHeight() - 450);
	    }

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // in game mouse
	}

	public void dispose() {
		GameAssets.font.dispose();
		GameAssets.batch.dispose();
		GameAssets.InGameMouse.dispose();
		
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}
		
	}

	public void InputDetection() {
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {
				switch (i) {
				case 0:
					GameAssets.ScoreCount = 0;
					game.setScreen(new MainMenuScreen(game));
					break;

				}
			}
		}
	}

}
