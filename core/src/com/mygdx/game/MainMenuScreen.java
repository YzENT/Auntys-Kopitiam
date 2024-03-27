package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ScreenAdapter;

public class MainMenuScreen extends ScreenAdapter {

	GameAssets game;

	public MainMenuScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		Buttons(); // calls function to render buttons
	}

	public void Buttons() {

		// common spacing used for calculations
		float ModifyXOffsetByPx = 45;
		float ButtonHeightDifference = 10;

		// initialize x amount of buttons
		GameAssets.MassRender = new ButtonBehaviour[5];

		// try and change this to for loop (code below)
		// ok i give up

		// PlayGame button
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.PlayGameButton,
				(Gdx.graphics.getWidth() - GameAssets.PlayGameButton.getWidth()) / 2 + (ModifyXOffsetByPx * 0 / 2),
				(((Gdx.graphics.getHeight() - GameAssets.PlayGameButton.getHeight()) / 2) / 30)
						* ((ButtonHeightDifference * 3) + 2),
				GameAssets.PlayGameButton.getWidth() - (ModifyXOffsetByPx * 0), GameAssets.PlayGameButton.getHeight(),
				game);

		// HowToPlay button
		GameAssets.MassRender[1] = new ButtonBehaviour(GameAssets.HowToPlayButton,
				(Gdx.graphics.getWidth() - GameAssets.HowToPlayButton.getWidth()) / 2 + (ModifyXOffsetByPx * 1 / 2),
				(((Gdx.graphics.getHeight() - GameAssets.HowToPlayButton.getHeight()) / 2) / 30)
						* ((ButtonHeightDifference * 2) + 2),
				GameAssets.HowToPlayButton.getWidth() - (ModifyXOffsetByPx * 1), GameAssets.HowToPlayButton.getHeight(),
				game);

		// About button
		GameAssets.MassRender[2] = new ButtonBehaviour(GameAssets.AboutButton,
				(Gdx.graphics.getWidth() - GameAssets.AboutButton.getWidth()) / 2 + (ModifyXOffsetByPx * 2 / 2),
				(((Gdx.graphics.getHeight() - GameAssets.AboutButton.getHeight()) / 2) / 30)
						* ((ButtonHeightDifference * 1) + 2),
				GameAssets.AboutButton.getWidth() - (ModifyXOffsetByPx * 2), GameAssets.AboutButton.getHeight(), game);

		// Quit button
		GameAssets.MassRender[3] = new ButtonBehaviour(GameAssets.QuitButton,
				(Gdx.graphics.getWidth() - GameAssets.QuitButton.getWidth()) / 2 + (ModifyXOffsetByPx * 3 / 2),
				(((Gdx.graphics.getHeight() - GameAssets.QuitButton.getHeight()) / 2) / 30)
						* ((ButtonHeightDifference * 0) + 2),
				GameAssets.QuitButton.getWidth() - (ModifyXOffsetByPx * 3), GameAssets.QuitButton.getHeight(), game);

		// Settings icon
		GameAssets.MassRender[4] = new ButtonBehaviour(GameAssets.SettingsIcon,
				(Gdx.graphics.getWidth() - (GameAssets.SettingsIcon.getWidth() * 1.2f)),
				(Gdx.graphics.getHeight() - (GameAssets.SettingsIcon.getHeight() * 1.2f)),
				GameAssets.SettingsIcon.getWidth(), GameAssets.SettingsIcon.getHeight(), game);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 1); // clear screen
		InputDetection(); // checks for input every frame
		GameAssets.batch.begin(); // begin rendering

		// Render MainMenuBg
		GameAssets.batch.draw(GameAssets.MainMenuBg, 0, 0, GameAssets.MainMenuBg.getWidth(),
				GameAssets.MainMenuBg.getHeight());

		// render interactive buttons
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.render();
		}

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // render in-game mouse

	}

	@Override
	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();

		GameAssets.MainMenuBg.dispose();
		GameAssets.PlayGameButton.dispose();
		GameAssets.HowToPlayButton.dispose();
		GameAssets.AboutButton.dispose();
		GameAssets.QuitButton.dispose();
		GameAssets.SettingsIcon.dispose();
		GameAssets.batch.dispose();
		GameAssets.InGameMouse.dispose();
		
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}
	}

	public void InputDetection() {
		// logic check to test which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {

				switch (i) {
				case 0:
					game.setScreen(new PreGameScreen(game));
					break;
				case 1:
					//game.setScreen(new HowToPlayScreen(game));
					game.setScreen(new HowToPlayScreen(game));
					break;
				case 2:
					game.setScreen(new AboutScreen(game));
					break;
				case 3:
					System.exit(0);
					break;
				case 4:
					game.setScreen(new SettingsMenu(game));
					break;
				default:
					break;
				}
			}
		}
	}
}
