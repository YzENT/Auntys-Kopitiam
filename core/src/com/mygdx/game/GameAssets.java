package com.mygdx.game;

import java.io.File;
import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.audio.Music;

public class GameAssets extends Game {

	/*
	 * Please use this class for initialization of data to achieve resource efficiency so that 
	 * classes don't have to initialize resources every time a new screen is being called.
	 */

	// Textures
	public static Texture MainMenuBg;
	public static Texture MainMenuBg_Blur;
	public static Texture PlayGameButton;
	public static Texture HowToPlayButton;
	public static Texture AboutButton;
	public static Texture QuitButton;
	public static Texture SettingsIcon;
	public static Texture CafeBg;
	public static Texture CafeBg_Blur;
	public static Texture DialogTexture;
	public static Array<Texture> TutorialImages; // Array of textures for tutorial images
	public static Texture LeftArrowKey;
	public static Texture RightArrowKey;
	public static Texture ReturnIcon;
	public static Texture PauseIcon;
	public static Texture ResumeIcon;
	public static Texture HomeIcon;
	public static Texture Tea;
	public static Texture Milk;
	public static Texture Water;
	public static Texture Syrup;
	public static Texture Sugar;
	public static Texture Coffee;
	public static Texture Milo;
	public static Texture Ice;
	public static Texture MixingCup;
	public static Texture MixingCupFront;
	public static Texture ServeDrinkIcon;
	public static Texture DustbinIcon;
	public static Texture VolumeUnmuteIcon;
	public static Texture VolumeMuteIcon;
	public static Texture GoBackMainMenu;
	
	// For ingredient animations
	public static Texture[] Pour;

	// Cursor
	public static Pixmap cursorPixmap;
	public static Sound MouseClickSFX; // https://pixabay.com/sound-effects/search/mouse%20click/
	public static Sound PageFlipSFX; // https://pixabay.com/sound-effects/search/flip/

	// Java, LibGDX classes
	public static SpriteBatch batch;
	public static BitmapFont font;
	public static GlyphLayout layout;
	public static ShapeRenderer ShapeRenderer;
	public static File TutorialAssetsDirectory;
	public static Random random;

	// Custom Classes shared across the game
	public static InGameMouseBehaviour InGameMouse;
	public static ButtonBehaviour MassRender[];
	public static CountDownTimer CountDownTimer;
	public static IngredientsLogic IngredientsLogic;
	
	// In-Game Constants
	public static int ScoreCount = 0;
	public static int DrinkType;
	public static String[] DrinkNames = { "Kopi", "Teh Tarik", "Cham", "Milo", "Sirap Bandung" };
	public static int TimeAnimate;
	public static boolean AnimateFade = false;

	// Strings
	public static String AboutMessage = "This game is a coursework made by students of UNMY (2023-2024 Batch, Group 15)\n"
			+ "Module Info: COMP1023 Software Engineering\n" + "Team Members:\n\n" + "Chea Yong Zen\n"
			+ "Foo Kei Thong\n" + "Taibah Rushdi Khalid\n" + "Muhammad Syukran Shabaruddin\n" + "Siow Jia Xuan\n"
			+ "Sidney Soh Zhi Lei\n\n" + "Press (ESC) to return to main menu";

	public static String PreGameMessage = "CLICK ANYWHERE TO BEGIN";
	public static String PostGameMessage = "Your score is: ";
	public static String HighScoreMessage = "High Score: ";
	public static String IngredientString = "";

	public static String[] TutorialMessage = {
    "Welcome to Aunty’s Kopitiam!\n\nIn this game, you must make the correct drinks according to the customer’s orders.\nThe more correct orders you complete, the better!",
    "Customer orders will be given on the board located at the top left of the screen.",
    "You must click the correct ingredients and add the correct amount\nof each ingredient according to the customer order.",
    "Once you have made the drink, submit it to the customer by clicking on the tick icon!\nIf you have made a mistake, discard it by clicking on the dustbin icon.",
    "The time left and number of drinks you have\ncorrectly made are located at top of screen.",
    "You can pause game by clicking pause button at top right."
};

	// Ingredient counts
	public static int System_SugarCount;
	public static int System_IceCount;
	public static int System_TeaCount;
	public static int System_MiloCount;
	public static int System_SyrupCount;
	public static int System_CoffeeCount;
	public static int System_MilkCount;
	public static int System_WaterCount;

	public static int User_SugarCount;
	public static int User_IceCount;
	public static int User_TeaCount;
	public static int User_MiloCount;
	public static int User_SyrupCount;
	public static int User_CoffeeCount;
	public static int User_MilkCount;
	public static int User_WaterCount;
	
	// Score
	public static int HighScore;
	public static Preferences HighScoreKeeper;
	
	//In-game SFX's
	public static Sound CorrectDrinkSFX;
	public static Sound IncorrectDrinkSFX;
	
	//In-game Music
	public static Music MusicMain; //https://youtu.be/GoWgI1V_WDA?si=MKF4WJJX9ijCF5LQ
	
	//Miscellaneous
	public static boolean SFXToggle = true;
	public static boolean MusicToggle = true;
	public static boolean AnimationPlay = false;
	public static int TutorialImageIndex = 0;
	public static int TutorialImageCount = 0;

	@Override
	public void create() {

		/*
		 * Majority assets are initialized here to prevent increase in RAM usage every
		 * time a screen is called Except a few which require specific integers/size,
		 * those are initialized within the classes themselves Using the template from
		 * this class
		 */

		MainMenuBg = new Texture(Gdx.files.internal("SharedAssets/GameMenuBg.png"));
		MainMenuBg_Blur = new Texture(Gdx.files.internal("SharedAssets/GameMenuBg_Blur.png"));
		PlayGameButton = new Texture(Gdx.files.internal("Texture/MainMenu/PlayGameButton.png"));
		HowToPlayButton = new Texture(Gdx.files.internal("Texture/MainMenu/HowToPlayButton.png"));
		AboutButton = new Texture(Gdx.files.internal("Texture/MainMenu/AboutButton.png"));
		QuitButton = new Texture(Gdx.files.internal("Texture/MainMenu/QuitButton.png"));
		SettingsIcon = new Texture(Gdx.files.internal("Texture/Buttons/SettingsIcon.png"));
		CafeBg = new Texture(Gdx.files.internal("SharedAssets/CafeBg.png"));
		CafeBg_Blur = new Texture(Gdx.files.internal("SharedAssets/CafeBg_Blur.png"));
		DialogTexture = new Texture(Gdx.files.internal("Texture/Miscellaneous/Overlay.png"));
		TutorialImages = new Array<>();
		LeftArrowKey = new Texture(Gdx.files.internal("Texture/Buttons/LeftArrowKey.png"));
		RightArrowKey = new Texture(Gdx.files.internal("Texture/Buttons/RightArrowKey.png"));
		ReturnIcon = new Texture(Gdx.files.internal("Texture/Buttons/ReturnIcon.png"));
		PauseIcon = new Texture(Gdx.files.internal("Texture/Buttons/PauseIcon.png"));
		ResumeIcon = new Texture(Gdx.files.internal("Texture/Buttons/ResumeIcon.png"));
		HomeIcon = new Texture(Gdx.files.internal("Texture/Buttons/HomeIcon.png"));
		Tea = new Texture(Gdx.files.internal("Texture/Ingredients/Tea.png"));
		Milk = new Texture(Gdx.files.internal("Texture/Ingredients/Milk.png"));
		Water = new Texture(Gdx.files.internal("Texture/Ingredients/Water.png"));
		Syrup = new Texture(Gdx.files.internal("Texture/Ingredients/Syrup.png"));
		Sugar = new Texture(Gdx.files.internal("Texture/Ingredients/Sugar.png"));
		Coffee = new Texture(Gdx.files.internal("Texture/Ingredients/Coffee.png"));
		Milo = new Texture(Gdx.files.internal("Texture/Ingredients/Milo.png"));
		Ice = new Texture(Gdx.files.internal("Texture/Ingredients/Ice.png"));
		MixingCup = new Texture(Gdx.files.internal("Texture/Ingredients/MixingCup.png"));
		MixingCupFront = new Texture(Gdx.files.internal("Texture/Ingredients/MixingCupFront.png"));
		
		Pour = new Texture[8];
		Pour[0] = new Texture(Gdx.files.internal("Texture/Ingredients/TeaPour.png"));
		Pour[1] = new Texture(Gdx.files.internal("Texture/Ingredients/MilkPour.png"));
		Pour[2] = new Texture(Gdx.files.internal("Texture/Ingredients/WaterPour.png"));
		Pour[3] = new Texture(Gdx.files.internal("Texture/Ingredients/SyrupPour.png"));
		Pour[4] = new Texture(Gdx.files.internal("Texture/Ingredients/SugarPour.png"));
		Pour[5] = new Texture(Gdx.files.internal("Texture/Ingredients/CoffeePour.png"));
		Pour[6] = new Texture(Gdx.files.internal("Texture/Ingredients/MiluPour.png"));
		Pour[7] = new Texture(Gdx.files.internal("Texture/Ingredients/IcePour.png"));
		
		ServeDrinkIcon = new Texture(Gdx.files.internal("Texture/Buttons/ServeDrinkIcon.png"));
		DustbinIcon = new Texture(Gdx.files.internal("Texture/Buttons/DustbinIcon.png"));
		VolumeUnmuteIcon = new Texture(Gdx.files.internal("Texture/Buttons/VolumeUnmuteIcon.png"));
		VolumeMuteIcon = new Texture(Gdx.files.internal("Texture/Buttons/VolumeMuteIcon.png"));
		GoBackMainMenu = new Texture(Gdx.files.internal("Texture/Buttons/GoBackMainMenu.png"));

		cursorPixmap = new Pixmap(Gdx.files.internal("SharedAssets/EmptyImage.png"));
		MouseClickSFX = Gdx.audio.newSound(Gdx.files.internal("SharedAssets/MouseClick.mp3"));
		PageFlipSFX = Gdx.audio.newSound(Gdx.files.internal("SharedAssets/PageFlip.mp3"));
		
		CorrectDrinkSFX= Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/CorrectBuzzer.mp3"));
		IncorrectDrinkSFX= Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/LoudBuzzer.mp3"));
		
		MusicMain = Gdx.audio.newMusic(Gdx.files.internal("Sound/Music/Main.mp3"));
		MusicMain.setLooping(true);
		MusicMain.play();
		
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("SharedAssets/Party Confetti Font/PartyConfettiRegular-eZOn3.fnt"));
		layout = new GlyphLayout();
		ShapeRenderer = new ShapeRenderer();
		
		//Tutorial Images
		//Please note this code will have bug if the command `gradlew desktop:dist` is executed instead of `gradlew desktop:run`
		GameAssets.TutorialAssetsDirectory = new File(Gdx.files.internal("TutorialAssets").path());
		File[] files = GameAssets.TutorialAssetsDirectory.listFiles();
		TutorialImageCount = files.length;
		
		// load resources based on file name
		for (int i = 0; i < GameAssets.TutorialImageCount; i++) {
			GameAssets.TutorialImages.add(new Texture("TutorialAssets/" + i + ".png"));
		}
		
		random = new Random();

		InGameMouse = new InGameMouseBehaviour();
		CountDownTimer = new CountDownTimer(1.0f,1.0f);
		CountDownTimer.setMaxTime(90);
		IngredientsLogic = new IngredientsLogic();
		
		HighScoreKeeper = Gdx.app.getPreferences("My Preferences");

		if (!HighScoreKeeper.contains("highscore")) {
		    // If file does not exist, create a new one and put in a high score of 0
		    HighScoreKeeper.putInteger("highscore", 0);
		}

		// Retrieve the high score from the preferences file
		HighScore = HighScoreKeeper.getInteger("highscore");

		//set screen to main menu
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // super important
	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		MainMenuBg.dispose();
		MainMenuBg_Blur.dispose();
		PlayGameButton.dispose();
		HowToPlayButton.dispose();
		AboutButton.dispose();
		QuitButton.dispose();
		SettingsIcon.dispose();
		CafeBg.dispose();
		CafeBg_Blur.dispose();
		DialogTexture.dispose();
		
		for (Texture texture : TutorialImages) {
			texture.dispose();
		}
		
		LeftArrowKey.dispose();
		RightArrowKey.dispose();
		ReturnIcon.dispose();
		PauseIcon.dispose();
		ResumeIcon.dispose();
		HomeIcon.dispose();
		Tea.dispose();
		Milk.dispose();
		Water.dispose();
		Syrup.dispose();
		Sugar.dispose();
		Coffee.dispose();
		Milo.dispose();
		Ice.dispose();
		MixingCup.dispose();
		MixingCupFront.dispose();

		ServeDrinkIcon.dispose();
		DustbinIcon.dispose();
		VolumeUnmuteIcon.dispose();
		VolumeMuteIcon.dispose();
		GoBackMainMenu.dispose();
	
		for (Texture texture : Pour) {
			texture.dispose();
		}

		cursorPixmap.dispose();
		MouseClickSFX.dispose();
		PageFlipSFX.dispose();
		
		CorrectDrinkSFX.dispose();
		IncorrectDrinkSFX.dispose();
		
		MusicMain.dispose();

		batch.dispose();
		font.dispose();
		layout = null;
		ShapeRenderer.dispose();
		InGameMouse.dispose();
		for (ButtonBehaviour button : MassRender) {
			button.dispose();
		}
		
		CountDownTimer.dispose();
		IngredientsLogic.dispose();
	}

}