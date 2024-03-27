package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter {

	GameAssets game;

	public GameScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		Buttons();
		GameAssets.CountDownTimer.StatusCheck();
		GameAssets.IngredientsLogic.DecideDrinks();
	}

	public void Buttons() {
		GameAssets.MassRender = new ButtonBehaviour[11];

		// Pause icon
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.PauseIcon,
				(Gdx.graphics.getWidth() - (GameAssets.PauseIcon.getWidth() * 1.2f)),
				(Gdx.graphics.getHeight() - (GameAssets.PauseIcon.getHeight() * 1.2f)), GameAssets.PauseIcon.getWidth(),
				GameAssets.PauseIcon.getHeight(), game);

		// render ingredients
		GameAssets.MassRender[1] = new ButtonBehaviour(GameAssets.Tea, 120, 505, GameAssets.Tea.getWidth(),
				GameAssets.Tea.getHeight(), game);
		GameAssets.MassRender[2] = new ButtonBehaviour(GameAssets.Milk, 470, 480, GameAssets.Milk.getWidth(),
				GameAssets.Milk.getHeight(), game);
		GameAssets.MassRender[3] = new ButtonBehaviour(GameAssets.Water, 135, 180, GameAssets.Water.getWidth(),
				GameAssets.Water.getHeight(), game);
		GameAssets.MassRender[4] = new ButtonBehaviour(GameAssets.Syrup, 495, 180, GameAssets.Syrup.getWidth(),
				GameAssets.Syrup.getHeight(), game);
		GameAssets.MassRender[5] = new ButtonBehaviour(GameAssets.Sugar, 1275, 505, GameAssets.Sugar.getWidth(),
				GameAssets.Sugar.getHeight(), game);
		GameAssets.MassRender[6] = new ButtonBehaviour(GameAssets.Coffee, 1585, 505, GameAssets.Coffee.getWidth(),
				GameAssets.Coffee.getHeight(), game);
		GameAssets.MassRender[7] = new ButtonBehaviour(GameAssets.Milo, 1275, 180, GameAssets.Milo.getWidth(),
				GameAssets.Milo.getHeight(), game);
		GameAssets.MassRender[8] = new ButtonBehaviour(GameAssets.Ice, 1585, 200, GameAssets.Ice.getWidth(),
				GameAssets.Ice.getHeight(), game);

		GameAssets.MassRender[9] = new ButtonBehaviour(GameAssets.ServeDrinkIcon, 770, 20,
				GameAssets.ServeDrinkIcon.getWidth(), GameAssets.ServeDrinkIcon.getHeight(), game);
		GameAssets.MassRender[10] = new ButtonBehaviour(GameAssets.DustbinIcon, 1000, 20,
				GameAssets.DustbinIcon.getWidth(), GameAssets.DustbinIcon.getHeight(), game);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 1); // clear screen
		InputDetection(); // checks for input every frame
		
		GameAssets.batch.begin(); // begin rendering
		GameAssets.batch.draw(GameAssets.CafeBg, 0, 0); // draw background image
		RenderDrinkCount(); //render ingredients requirements
		RenderMathableUI(); //render timer + score

		// render interactive buttons
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.render();
		}

		GameAssets.batch.end(); // end rendering

		//if time runs out, swap screen
		if (GameAssets.TimeLeft == 0) {
			
			if (GameAssets.ScoreCount > GameAssets.HighScore) {
	            GameAssets.HighScoreKeeper.putInteger("highscore", GameAssets.ScoreCount);
	            GameAssets.HighScoreKeeper.flush();
	            GameAssets.HighScore = GameAssets.HighScoreKeeper.getInteger("highscore", 0);
	            System.out.println(GameAssets.HighScore);
		    }
			
			game.setScreen(new PostGameScreen(game));
		}

		GameAssets.InGameMouse.render(); // render in-game mouse
	}

	@Override
	public void pause() {
		GameAssets.CountDownTimer.pause(); //pauses timer
		game.setScreen(new PauseMenu(game)); //switches to pause menu
	}

	@Override
	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();
		GameAssets.font.dispose();
		GameAssets.batch.dispose();
		GameAssets.InGameMouse.dispose();
		GameAssets.CafeBg.dispose();
		GameAssets.CountDownTimer.dispose();
		GameAssets.PauseIcon.dispose();
		GameAssets.Tea.dispose();
		GameAssets.Milk.dispose();
		GameAssets.Water.dispose();
		GameAssets.Syrup.dispose();
		GameAssets.Sugar.dispose();
		GameAssets.Coffee.dispose();
		GameAssets.Milo.dispose();
		GameAssets.Ice.dispose();
		GameAssets.ServeDrinkIcon.dispose();
		GameAssets.DustbinIcon.dispose();
		GameAssets.CountDownTimer.dispose();

		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}

	}

	public void InputDetection() {
		//logic to check which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched() ) {
				switch (i) {
				case 0:
					for (Sound CorSound : GameAssets.CorrectDrinkSFX) {
						CorSound.pause();
					}
					for (Sound IncorSound : GameAssets.IncorrectDrinkSFX) {
						IncorSound.pause();
					}
					pause();
					break;
				case 1:
					GameAssets.User_TeaCount++;
					break;
				case 2:
					GameAssets.User_MilkCount++;
					break;
				case 3:
					GameAssets.User_WaterCount++;
					break;
				case 4:
					GameAssets.User_SyrupCount++;
					break;
				case 5:
					GameAssets.User_SugarCount++;
					break;
				case 6:
					GameAssets.User_CoffeeCount++;
					break;
				case 7:
					GameAssets.User_MiloCount++;
					break;
				case 8:
					GameAssets.User_IceCount++;
					break;
				case 9:
					//if user values = system values, increase score count
					if (GameAssets.IngredientsLogic.CountCheck()) {
						GameAssets.ScoreCount++;
						GameAssets.IngredientsLogic.CorrectDrinkSFX();
					} else {
						GameAssets.IngredientsLogic.IncorrectDrinkSFX();
					}
					GameAssets.IngredientsLogic.ResetUserCount(); //reset
					GameAssets.IngredientsLogic.DecideDrinks(); //and choose next drink
					break;
				case 10:
					GameAssets.IngredientsLogic.ResetUserCount(); //player disposes drink count and resets
					break;
				default:
					break;
				}
			}
		}
	}

	public void AlternateTimerColour() {
		GameAssets.font.setColor(1, 1, 1, 1); //set font to white (bug may occur without this)
		if (GameAssets.TimeLeft <= 10) { //if timer is less than 10
			if (GameAssets.TimeLeft % 2 == 0) { //if value is a factor of 2
				GameAssets.font.setColor(1, 0, 0, 1); //set it to red
			} else {
				GameAssets.font.setColor(1, 1, 1, 1); //alternate it to white
			}
		}
	}

	public void RenderDrinkCount() {
		//renders ingredients menu
		GameAssets.font.getData().setScale(0.28f);
		GameAssets.font.setColor(0, 0, 0, 1);

		GameAssets.IngredientString = GameAssets.DrinkNames[GameAssets.DrinkType] + "\n";

		if (GameAssets.System_SugarCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString
					.concat("\nSugar: " + GameAssets.System_SugarCount);
		}
		if (GameAssets.System_IceCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString.concat("\nIce: " + GameAssets.System_IceCount);
		}
		if (GameAssets.System_TeaCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString.concat("\nTea: " + GameAssets.System_TeaCount);
		}
		if (GameAssets.System_MiloCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString.concat("\nMilo: " + GameAssets.System_MiloCount);
		}
		if (GameAssets.System_SyrupCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString
					.concat("\nSyrup: " + GameAssets.System_SyrupCount);
		}
		if (GameAssets.System_CoffeeCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString
					.concat("\nCoffee: " + GameAssets.System_CoffeeCount);
		}
		if (GameAssets.System_MilkCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString.concat("\nMilk: " + GameAssets.System_MilkCount);
		}
		if (GameAssets.System_WaterCount != 0) {
			GameAssets.IngredientString = GameAssets.IngredientString
					.concat("\nWater: " + GameAssets.System_WaterCount);
		}

		GameAssets.font.draw(GameAssets.batch, GameAssets.IngredientString, 100, 1035);
	}

	public void RenderMathableUI() {
		//font data
		GameAssets.font.setColor(1, 1, 1, 1);
		GameAssets.font.getData().setScale(1);
		
		AlternateTimerColour(); // code to alter colour for timer
		//render time left
		GameAssets.font.draw(GameAssets.batch, String.format("%02d", GameAssets.TimeLeft), 795,
				Gdx.graphics.getHeight() - 95);
		
		GameAssets.font.setColor(1, 1, 1, 1); // only allow the colour of timer to be altered, anything below this code, text should be pure white
		
		//render score count
		GameAssets.font.draw(GameAssets.batch, Integer.toString(GameAssets.ScoreCount), 1170,
		Gdx.graphics.getHeight() - 95);
	}
	
	public void FadeAnimation() {
		//animation goes here
	}
}