package com.mygdx.game;

import java.io.File;
import java.util.Random;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

public class GameAssets extends Game {

	/*
	 * Please use this class for resource efficiency so that classes don't have to
	 * initialize resources every time a new screen is being called.
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
	public static Array<Texture> TutorialImages; // Array of textures for efficiency in adding/removing tutorial images
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
	public static File directory;
	public static Screen PreviousScreen; // don't use this when there's a screen within a screen (e.g: Main Menu ->
										 // GameScreen -> Settings), create individuals
	public static Preferences SaveState;
	public static Random random;

	// Custom Classes shared across the game
	public static InGameMouseBehaviour InGameMouse;
	public static ButtonBehaviour MassRender[];
	public static CountDownTimer CountDownTimer;
	public static IngredientsLogic IngredientsLogic;
	
	// In-Game Constants
	public static int TimeLeft = 90;
	public static Timer time;
	public static Timer.Task CountDown;
	public static boolean TimerStopped = false;
	public static boolean TimerPaused = false;
	public static boolean TimerActive = false;
	public static int ScoreCount = 0;
	public static int DrinkType;
	public static String[] DrinkNames = { "Kopi", "Teh Tarik", "Cham", "Milo", "Sirap Bandung" };
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
	public static Sound[] CorrectDrinkSFX;
	public static Sound[] IncorrectDrinkSFX;
	
	//Miscellaneous
	public static boolean SFXToggle = true;
	public static boolean MusicToggle = true;
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
		PlayGameButton = new Texture(Gdx.files.internal("MainMenuAssets/PlayGameButton.png"));
		HowToPlayButton = new Texture(Gdx.files.internal("MainMenuAssets/HowToPlayButton.png"));
		AboutButton = new Texture(Gdx.files.internal("MainMenuAssets/AboutButton.png"));
		QuitButton = new Texture(Gdx.files.internal("MainMenuAssets/QuitButton.png"));
		SettingsIcon = new Texture(Gdx.files.internal("SharedAssets/SettingsIcon.png"));
		CafeBg = new Texture(Gdx.files.internal("SharedAssets/CafeBg.png"));
		CafeBg_Blur = new Texture(Gdx.files.internal("SharedAssets/CafeBg_Blur.png"));
		DialogTexture = new Texture(Gdx.files.internal("SecondaryOverlay/Overlay.png"));
		TutorialImages = new Array<>();
		LeftArrowKey = new Texture(Gdx.files.internal("Texture/LeftArrowKey.png"));
		RightArrowKey = new Texture(Gdx.files.internal("Texture/RightArrowKey.png"));
		ReturnIcon = new Texture(Gdx.files.internal("SharedAssets/ReturnIcon.png"));
		PauseIcon = new Texture(Gdx.files.internal("Texture/PauseIcon.png"));
		ResumeIcon = new Texture(Gdx.files.internal("Texture/ResumeIcon.png"));
		HomeIcon = new Texture(Gdx.files.internal("Texture/HomeIcon.png"));
		Tea = new Texture(Gdx.files.internal("Texture/Ingredients/Tea.png"));
		Milk = new Texture(Gdx.files.internal("Texture/Ingredients/Milk.png"));
		Water = new Texture(Gdx.files.internal("Texture/Ingredients/Water.png"));
		Syrup = new Texture(Gdx.files.internal("Texture/Ingredients/Syrup.png"));
		Sugar = new Texture(Gdx.files.internal("Texture/Ingredients/Sugar.png"));
		Coffee = new Texture(Gdx.files.internal("Texture/Ingredients/Coffee.png"));
		Milo = new Texture(Gdx.files.internal("Texture/Ingredients/Milo.png"));
		Ice = new Texture(Gdx.files.internal("Texture/Ingredients/Ice.png"));
		
		Pour = new Texture[8];
		Pour[0] = new Texture(Gdx.files.internal("Texture/Ingredients/TeaPour"));
		Pour[1] = new Texture(Gdx.files.internal("Texture/Ingredients/MilkPour"));
		Pour[2] = new Texture(Gdx.files.internal("Texture/Ingredients/WaterPour"));
		Pour[3] = new Texture(Gdx.files.internal("Texture/Ingredients/SyrupPour"));
		Pour[4] = new Texture(Gdx.files.internal("Texture/Ingredients/SugarPour"));
		Pour[5] = new Texture(Gdx.files.internal("Texture/Ingredients/CoffeePour"));
		Pour[6] = new Texture(Gdx.files.internal("Texture/Ingredients/MiloPour"));
		Pour[7] = new Texture(Gdx.files.internal("Texture/Ingredients/IcePour"));
		
		ServeDrinkIcon = new Texture(Gdx.files.internal("Texture/ServeDrinkIcon.png"));
		DustbinIcon = new Texture(Gdx.files.internal("Texture/DustbinIcon.png"));
		VolumeUnmuteIcon = new Texture(Gdx.files.internal("Texture/VolumeUnmuteIcon.png"));
		VolumeMuteIcon = new Texture(Gdx.files.internal("Texture/VolumeMuteIcon.png"));
		GoBackMainMenu = new Texture(Gdx.files.internal("Texture/GoBackMainMenu.png"));

		cursorPixmap = new Pixmap(Gdx.files.internal("EmptyImage.png"));
		MouseClickSFX = Gdx.audio.newSound(Gdx.files.internal("SharedAssets/MouseClick.mp3"));
		PageFlipSFX = Gdx.audio.newSound(Gdx.files.internal("SharedAssets/PageFlip.mp3"));

		CorrectDrinkSFX = new Sound[5];
		CorrectDrinkSFX[0] = Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/CorrectBuzzer.mp3"));
		CorrectDrinkSFX[1] = Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/DonPolloWaitWait.mp3"));
		CorrectDrinkSFX[2] = Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/MetroBoominMakeItBoom.mp3"));
		CorrectDrinkSFX[3] = Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/NewBugatti.mp3"));
		CorrectDrinkSFX[4] = Gdx.audio.newSound(Gdx.files.internal("Sound/CorrectDrink/OhMaGawh.mp3"));
		
		IncorrectDrinkSFX = new Sound[5];
		IncorrectDrinkSFX[0] = Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/BruhSlowed.mp3"));
		IncorrectDrinkSFX[1] = Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/DramaticFart.mp3"));
		IncorrectDrinkSFX[2] = Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/LoudBuzzer.mp3"));
		IncorrectDrinkSFX[3] = Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/LoudSiren.mp3"));
		IncorrectDrinkSFX[4] = Gdx.audio.newSound(Gdx.files.internal("Sound/WrongDrink/TiktokJamalMeme.mp3"));
		
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("SharedAssets/Party Confetti Font/PartyConfettiRegular-eZOn3.fnt"));
		layout = new GlyphLayout();
		ShapeRenderer = new ShapeRenderer();
		random = new Random();

		InGameMouse = new InGameMouseBehaviour();
		CountDownTimer = new CountDownTimer();
		IngredientsLogic = new IngredientsLogic();
		
		HighScoreKeeper = Gdx.app.getPreferences("My Preferences");

		if (!HighScoreKeeper.contains("highscore")) {
		    // If file does not exist, create a new one and put in a high score of 0
		    HighScoreKeeper.putInteger("highscore", 0);
		}

		// Retrieve the high score from the preferences file
		HighScore = HighScoreKeeper.getInteger("highscore");

		time = new Timer();

		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); // super important
	}

	public void dispose() {

		/*
		 * Disposal of unused resources
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
		VolumeUnmuteIcon.dispose();
		VolumeMuteIcon.dispose();
		
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
		
		for (Texture texture : Pour) {
			texture.dispose();
		}
		
		ServeDrinkIcon.dispose();
		DustbinIcon.dispose();
		GoBackMainMenu.dispose();

		cursorPixmap.dispose();
		MouseClickSFX.dispose();
		PageFlipSFX.dispose();
		
		for (Sound CorSound : CorrectDrinkSFX) {
			CorSound.dispose();
		}

		for (Sound IncorSound : IncorrectDrinkSFX) {
			IncorSound.dispose();
		}

		batch.dispose();
		font.dispose();
		layout = null;
		ShapeRenderer.dispose();
		PreviousScreen.dispose();

		InGameMouse.dispose();
		for (ButtonBehaviour button : MassRender) {
			button.dispose();
		}
		
		CountDownTimer.dispose();
		IngredientsLogic.dispose();
	}

}