package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;

public class PauseMenu extends ScreenAdapter {

	GameAssets game;

	public PauseMenu(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		Buttons();
	}

	public void Buttons() {

		GameAssets.MassRender = new ButtonBehaviour[2];

		// Resume Icon
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.ResumeIcon, 700, 
				(Gdx.graphics.getHeight() - GameAssets.ResumeIcon.getHeight())/2, GameAssets.ResumeIcon.getWidth(), GameAssets.ResumeIcon.getHeight(), game);
		
		//Home Icon
		GameAssets.MassRender[1] = new ButtonBehaviour(GameAssets.HomeIcon, 1100,
				(Gdx.graphics.getHeight() - GameAssets.HomeIcon.getHeight())/2, GameAssets.HomeIcon.getWidth(), GameAssets.HomeIcon.getHeight(), game);

	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 0);// clear screen
		InputDetection(); // checks for input every frame
		GameAssets.batch.begin(); // begin rendering
		GameAssets.batch.draw(GameAssets.CafeBg_Blur, 0, 0); // draw background image

		// render buttons
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.render();
		}

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // in game mouse
	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();
		GameAssets.batch.dispose();
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}
		GameAssets.InGameMouse.dispose();
	}

	public void InputDetection() {
		// logic check to see which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {
				switch (i) {
				case 0:
					GameAssets.CountDownTimer.resume();
					GameAssets.CorrectDrinkSFX.resume();
					GameAssets.IncorrectDrinkSFX.resume();
					game.setScreen(new GameScreen(game));
					break;
				case 1:
					GameAssets.CountDownTimer.setTimeLeft(0);
					GameAssets.CountDownTimer.TimerActive = false;
					GameAssets.ScoreCount = 0;
					game.setScreen(new MainMenuScreen(game));
				default:
					break;
				}
			}
		}
	}

}
