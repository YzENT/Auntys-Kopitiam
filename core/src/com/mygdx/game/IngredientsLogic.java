package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;

public class IngredientsLogic{
	
	public void ResetUserCount() {
		// reset user values
		GameAssets.User_SugarCount = 0;
		GameAssets.User_IceCount = 0;
		GameAssets.User_TeaCount = 0;
		GameAssets.User_MiloCount = 0;
		GameAssets.User_SyrupCount = 0;
		GameAssets.User_CoffeeCount = 0;
		GameAssets.User_MilkCount = 0;
		GameAssets.User_WaterCount = 0;
	}

	public void DecideDrinks() {

		GameAssets.DrinkType = GameAssets.random.nextInt(5); // randomly choose drink type

		switch (GameAssets.DrinkType) {
		case 0:
			Kopi();
			break;
		case 1:
			TehTarik();
			break;
		case 2:
			Cham();
			break;
		case 3:
			Milo();
			break;
		case 4:
			SirapBandung();
			break;
		default:
			break;
		}
	}

	public void Kopi() {
		GameAssets.System_SugarCount = GameAssets.random.nextInt(3) + 1; // Range: 1-3
		GameAssets.System_IceCount = GameAssets.random.nextInt(2); // Range: 0-1
		GameAssets.System_TeaCount = 0; // Range: 0
		GameAssets.System_MiloCount = 0; // Range: 0
		GameAssets.System_SyrupCount = GameAssets.random.nextInt(4); // Range: 0-3
		GameAssets.System_CoffeeCount = GameAssets.random.nextInt(3) + 1; // Range: 1-3
		GameAssets.System_MilkCount = 0; // Range: 0
		GameAssets.System_WaterCount = GameAssets.random.nextInt(3) + 3; // Range: 3-5
	}

	public void TehTarik() {
		GameAssets.System_SugarCount = GameAssets.random.nextInt(3) + 2; // Range: 2-4
		GameAssets.System_IceCount = GameAssets.random.nextInt(2); // Range: 0-1
		GameAssets.System_TeaCount = GameAssets.random.nextInt(3) + 1; // Range: 1-3
		GameAssets.System_MiloCount = 0; // Range: 0
		GameAssets.System_SyrupCount = 0; // Range: 0
		GameAssets.System_CoffeeCount = 0; // Range: 0
		GameAssets.System_MilkCount = GameAssets.random.nextInt(3) + 3; // Range: 3-5
		GameAssets.System_WaterCount = GameAssets.random.nextInt(2); // Range: 0-1
	}

	public void Cham() {
		GameAssets.System_SugarCount = GameAssets.random.nextInt(4) + 2; // Range: 2-5
		GameAssets.System_IceCount = GameAssets.random.nextInt(2); // Range: 0-1
		GameAssets.System_TeaCount = GameAssets.random.nextInt(3) + 1; // Range: 1-3
		GameAssets.System_MiloCount = 0; // Range: 0
		GameAssets.System_SyrupCount = GameAssets.random.nextInt(4); // Range: 0-3
		GameAssets.System_CoffeeCount = GameAssets.random.nextInt(3) + 1; // Range: 1-3
		GameAssets.System_MilkCount = GameAssets.random.nextInt(3) + 3; // Range: 3-5
		GameAssets.System_WaterCount = GameAssets.random.nextInt(2); // Range: 0-1
	}

	public void Milo() {
		GameAssets.System_SugarCount = GameAssets.random.nextInt(4) + 2; // Range: 2-5
		GameAssets.System_IceCount = GameAssets.random.nextInt(2); // Range: 0-1
		GameAssets.System_TeaCount = 0; // Range: 0
		GameAssets.System_MiloCount = GameAssets.random.nextInt(2) + 3; // Range: 3-4
		GameAssets.System_SyrupCount = GameAssets.random.nextInt(3); // Range: 0-2
		GameAssets.System_CoffeeCount = 0; // Range: 0
		GameAssets.System_MilkCount = GameAssets.random.nextInt(2) + 2; // Range: 2-3
		GameAssets.System_WaterCount = 0; // Range: 0
	}

	public void SirapBandung() {
		GameAssets.System_SugarCount = GameAssets.random.nextInt(4) + 2; // Range: 2-5
		GameAssets.System_IceCount = GameAssets.random.nextInt(2); // Range: 0-1
		GameAssets.System_TeaCount = 0; // Range: 0
		GameAssets.System_MiloCount = 0; // Range: 0
		GameAssets.System_SyrupCount = GameAssets.random.nextInt(3) + 3; // Range: 3-5
		GameAssets.System_CoffeeCount = 0; // Range: 0
		GameAssets.System_MilkCount = GameAssets.random.nextInt(3) + 3; // Range: 3-5
		GameAssets.System_WaterCount = GameAssets.random.nextInt(4) + 3; // Range: 3-6
	}

	
	public boolean CountCheck() {
		// method to check if user's input is same as system's randomly generated number
		if (GameAssets.System_SugarCount == GameAssets.User_SugarCount
				&& GameAssets.System_IceCount == GameAssets.User_IceCount
				&& GameAssets.System_TeaCount == GameAssets.User_TeaCount
				&& GameAssets.System_MiloCount == GameAssets.User_MiloCount
				&& GameAssets.System_SyrupCount == GameAssets.User_SyrupCount
				&& GameAssets.System_CoffeeCount == GameAssets.User_CoffeeCount
				&& GameAssets.System_MilkCount == GameAssets.User_MilkCount
				&& GameAssets.System_WaterCount == GameAssets.User_WaterCount) {
			return true;
		} else {
			return false;
		}
	}
	
	public void CorrectDrinkSFX() {
		if (GameAssets.SFXToggle) {
			GameAssets.CorrectDrinkSFX[GameAssets.random.nextInt(5)].play();
			
			
		}
	}
	
	public void IncorrectDrinkSFX() {
		if (GameAssets.SFXToggle) {
			GameAssets.IncorrectDrinkSFX[GameAssets.random.nextInt(5)].play();
		}
	}
	
	public void dispose() {
		for (Sound CorSound : GameAssets.CorrectDrinkSFX) {
			CorSound.dispose();
		}

		for (Sound IncorSound : GameAssets.IncorrectDrinkSFX) {
			IncorSound.dispose();
		}
	}
}