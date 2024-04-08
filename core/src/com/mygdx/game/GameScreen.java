package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

public class GameScreen extends ScreenAdapter {

	GameAssets game;
	private int IngredientIndex; // decides which ingredient animation plays
	public boolean resetAnimation; // flag to restart animation 
	CountDownTimer AnimateTimer = new CountDownTimer(0.2f,0.05f);

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
		GameAssets.MassRender[0] = new ButtonBehaviour(
			GameAssets.PauseIcon,
			(Gdx.graphics.getWidth() - (GameAssets.PauseIcon.getWidth() * 1.2f)),
			(Gdx.graphics.getHeight() - (GameAssets.PauseIcon.getHeight() * 1.2f)), 
			GameAssets.PauseIcon.getWidth(),
			GameAssets.PauseIcon.getHeight(), 
			game);

		// Ingredients
		GameAssets.MassRender[1] = new ButtonBehaviour(
			GameAssets.Tea, 
			120, 
			505, 
			GameAssets.Tea.getWidth(),
			GameAssets.Tea.getHeight(), 
			game);

		GameAssets.MassRender[2] = new ButtonBehaviour(
			GameAssets.Milk, 
			470, 
			480, 
			GameAssets.Milk.getWidth(),
			GameAssets.Milk.getHeight(), 
			game);

		GameAssets.MassRender[3] = new ButtonBehaviour(
			GameAssets.Water, 
			135, 
			180, 
			GameAssets.Water.getWidth(),
			GameAssets.Water.getHeight(), 
			game);

		GameAssets.MassRender[4] = new ButtonBehaviour(
			GameAssets.Syrup, 
			495, 
			180, 
			GameAssets.Syrup.getWidth(),
			GameAssets.Syrup.getHeight(),
			game);

		GameAssets.MassRender[5] = new ButtonBehaviour(
			GameAssets.Sugar, 
			1275, 
			505, 
			GameAssets.Sugar.getWidth(),
			GameAssets.Sugar.getHeight(),
			game);

		GameAssets.MassRender[6] = new ButtonBehaviour(
			GameAssets.Coffee, 
			1585, 
			505, 
			GameAssets.Coffee.getWidth(),
			GameAssets.Coffee.getHeight(), 
			game);

		GameAssets.MassRender[7] = new ButtonBehaviour(
			GameAssets.Milo, 
			1275, 
			180, 
			GameAssets.Milo.getWidth(),
			GameAssets.Milo.getHeight(), 
			game);

		GameAssets.MassRender[8] = new ButtonBehaviour(
			GameAssets.Ice, 
			1585, 
			200, 
			GameAssets.Ice.getWidth(),
			GameAssets.Ice.getHeight(), 
			game);

		GameAssets.MassRender[9] = new ButtonBehaviour(
			GameAssets.ServeDrinkIcon, 
			770, 
			20,
			GameAssets.ServeDrinkIcon.getWidth(), 
			GameAssets.ServeDrinkIcon.getHeight(), 
			game);

		GameAssets.MassRender[10] = new ButtonBehaviour(
			GameAssets.DustbinIcon, 
			1000, 
			20,
			GameAssets.DustbinIcon.getWidth(), 
			GameAssets.DustbinIcon.getHeight(), 
			game);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 1); // clear screen
		GameAssets.batch.begin(); // begin rendering
		GameAssets.batch.draw(GameAssets.CafeBg, 0, 0); // draw background image
		
		RenderDrinkCount(); //render ingredients requirements
		GameAssets.batch.draw(GameAssets.MixingCup, 793 , 173);
		
		RenderMathableUI(); //render timer + score

		// render interactive buttons
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.render();
		}

		//if "ESC" key is pressed, trigger pause method
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			pause();
		}
		
		if (!GameAssets.AnimationPlay) { // when animation is playing, user cannot spam click ingredients
			InputDetection(); // checks for input every frame
		}
		else {
			AnimateIngredients(); // plays the animation
		}

		GameAssets.batch.end(); // end rendering

		//if time runs out, swap screen
		if (GameAssets.CountDownTimer.getTimeLeft() == 0) {
			//logic for current and highets score
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

		//pauses all ongoing sound
		GameAssets.CorrectDrinkSFX.pause();
		GameAssets.IncorrectDrinkSFX.pause();

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
		AnimateTimer.dispose();
		
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}

	}

	public void InputDetection() {
		//logic to check which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched() ) {
				
				//if it falls within the range of ingredient then toggle animation
				if (i != 0 && i < 9) {
					resetAnimation = true;
					GameAssets.AnimationPlay = true;
				}
				
				switch (i) {
				case 0:
					pause();
					break;
				case 1:
					GameAssets.User_TeaCount++;
					IngredientIndex = 0;	
					break;
				case 2:
					GameAssets.User_MilkCount++;
					IngredientIndex = 1;
					break;
				case 3:
					GameAssets.User_WaterCount++;
					IngredientIndex = 2;
					break;
				case 4:
					GameAssets.User_SyrupCount++;
					IngredientIndex = 3;
					break;
				case 5:
					GameAssets.User_SugarCount++;
					IngredientIndex = 4;
					break;
				case 6:
					GameAssets.User_CoffeeCount++;
					IngredientIndex = 5;
					break;
				case 7:
					GameAssets.User_MiloCount++;
					IngredientIndex = 6;
					break;
				case 8:
					GameAssets.User_IceCount++;
					IngredientIndex = 7;
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
		if (GameAssets.CountDownTimer.getTimeLeft() <= 10) { //if timer is less than 10
			if (GameAssets.CountDownTimer.getTimeLeft() % 2 == 0) { //if value is divisable by 2
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
		GameAssets.font.draw(GameAssets.batch, String.format("%02.0f", GameAssets.CountDownTimer.getTimeLeft()), 795,
				Gdx.graphics.getHeight() - 95);
		
		GameAssets.font.setColor(1, 1, 1, 1); // only allow the colour of timer to be altered, anything below this code, text should be pure white
		
		//render score count
		GameAssets.font.draw(GameAssets.batch, Integer.toString(GameAssets.ScoreCount), 1170,
		Gdx.graphics.getHeight() - 95);
	}
	
	public void AnimateIngredients() {
		//animation goes here
		if (resetAnimation) { // resets the timer to 1.4 seconds
			AnimateTimer.setMaxTime(1.4f);
			resetAnimation = false;
		}
		
		AnimateTimer.StatusCheck();
		
		if (AnimateTimer.getTimeLeft() <= 0) { // checks if animation is finished, else play animation
			GameAssets.AnimationPlay = false;
		}
		else {
			if (IngredientIndex == 4 || IngredientIndex == 7) { // animations for ice and sugar
				if (AnimateTimer.getTimeLeft() > 0.7) {
					GameAssets.batch.draw(GameAssets.Pour[IngredientIndex], 910 , 414);
				}
				else {
					GameAssets.batch.draw(GameAssets.Pour[IngredientIndex], 910 , 414-100);
				}
			}
			else { // animations for liquids and milu powder
				GameAssets.batch.draw(GameAssets.Pour[IngredientIndex], 938 , 414);
				GameAssets.batch.draw(GameAssets.MixingCupFront, 793 , 353);
			}
		}

	}

}