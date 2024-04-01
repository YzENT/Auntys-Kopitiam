package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class HowToPlayScreen extends ScreenAdapter {

	GameAssets game;

	public HowToPlayScreen(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		Buttons();
	}

	public void Buttons() {

		GameAssets.MassRender = new ButtonBehaviour[3];

		// BackArrowKey Icon
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.ReturnIcon,
				GameAssets.ReturnIcon.getWidth() * 0.2f,
				(Gdx.graphics.getHeight() - (GameAssets.ReturnIcon.getHeight() * 1.2f)),
				GameAssets.ReturnIcon.getWidth(), GameAssets.ReturnIcon.getHeight(), game);

		// Left Arrow Key
		GameAssets.MassRender[1] = new ButtonBehaviour(GameAssets.LeftArrowKey,
				(Gdx.graphics.getWidth() - GameAssets.DialogTexture.getWidth()) / 2,
				(Gdx.graphics.getHeight() - GameAssets.LeftArrowKey.getHeight()) / 2,
				GameAssets.LeftArrowKey.getWidth(), GameAssets.LeftArrowKey.getHeight(), game);

		// Right Arrow Key
		GameAssets.MassRender[2] = new ButtonBehaviour(GameAssets.RightArrowKey,
				Gdx.graphics.getWidth() - GameAssets.RightArrowKey.getWidth()
						- ((Gdx.graphics.getWidth() - GameAssets.DialogTexture.getWidth()) / 2),
				(Gdx.graphics.getHeight() - GameAssets.RightArrowKey.getHeight()) / 2,
				GameAssets.RightArrowKey.getWidth(), GameAssets.RightArrowKey.getHeight(), game);

	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 0); // clear screen
		InputDetection(); // checks input for every frame
		GameAssets.batch.begin(); // rendering begins

		// Render MainMenuBg
		GameAssets.batch.draw(GameAssets.MainMenuBg_Blur, 0, 0, GameAssets.MainMenuBg_Blur.getWidth(),
				GameAssets.MainMenuBg_Blur.getHeight());

		// Render Dialog Texture
		GameAssets.batch.draw(GameAssets.DialogTexture,
				(Gdx.graphics.getWidth() - GameAssets.DialogTexture.getWidth()) / 2,
				(Gdx.graphics.getHeight() - GameAssets.DialogTexture.getHeight()) / 2,
				GameAssets.DialogTexture.getWidth(), GameAssets.DialogTexture.getHeight());

		// Render Tutorial Images based on value 'GameAssets.TutorialImageIndex'
		GameAssets.batch.draw(GameAssets.TutorialImages.get(GameAssets.TutorialImageIndex),
				(Gdx.graphics.getWidth() - GameAssets.TutorialImages.get(GameAssets.TutorialImageIndex).getWidth()) / 2,
				(Gdx.graphics.getHeight() - GameAssets.TutorialImages.get(GameAssets.TutorialImageIndex).getHeight()) * 0.75f);

		// render interactive buttons
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			ButtonBehaviour button = GameAssets.MassRender[i];

			// Rendering arrow keys based on CurrentIndex value
			if ((GameAssets.TutorialImageIndex != 0 || i != 1) && (GameAssets.TutorialImageIndex != GameAssets.TutorialImages.size - 1 || i != 2)) {
				button.render();
			}
		}

		GameAssets.batch.end(); // end of rendering
		GameAssets.InGameMouse.render(); // render in game mouse
	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */
		game.dispose();
		GameAssets.DialogTexture.dispose();
		GameAssets.MainMenuBg_Blur.dispose();
		GameAssets.ReturnIcon.dispose();
		GameAssets.LeftArrowKey.dispose();
		GameAssets.RightArrowKey.dispose();
		for (Texture image : GameAssets.TutorialImages) {
			image.dispose();
		}
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}
		GameAssets.batch.dispose();
		GameAssets.PageFlipSFX.dispose();
		GameAssets.InGameMouse.dispose();
	}

	private void nextImage() {
		if (GameAssets.TutorialImageIndex < GameAssets.TutorialImageCount - 1) {
			GameAssets.TutorialImageIndex = GameAssets.TutorialImageIndex + 1;
			if (GameAssets.SFXToggle) {
				GameAssets.PageFlipSFX.play();
			}
		}
	}

	private void previousImage() {
		if (GameAssets.TutorialImageIndex > 0) {
			GameAssets.TutorialImageIndex = GameAssets.TutorialImageIndex - 1;
			if (GameAssets.SFXToggle) {
				GameAssets.PageFlipSFX.play();
			}
		}
	}

	public void InputDetection() {
		// logic check to see which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {
				switch (i) {
				case 0:
					GameAssets.TutorialImageIndex = 0;
					game.setScreen(new MainMenuScreen(game));
					break;
				case 1:
					previousImage();
					break;
				case 2:
					nextImage();
					break;
				default:
					break;
				}
			}
		}
	}

}
