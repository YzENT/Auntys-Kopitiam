package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;

public class SettingsMenu extends ScreenAdapter {

	GameAssets game;

	public SettingsMenu(GameAssets game) {
		this.game = game;
	}

	@Override
	public void show() {
		GameAssets.font.setColor(1, 1, 1, 1);
		GameAssets.font.getData().setScale(1);
	}
	
	public void Buttons() {
		GameAssets.MassRender = new ButtonBehaviour[3];
		
		// LeaderBoard Icon
		GameAssets.MassRender[0] = new ButtonBehaviour(GameAssets.ReturnIcon,
				GameAssets.ReturnIcon.getWidth() * 0.2f,
				(Gdx.graphics.getHeight() - (GameAssets.ReturnIcon.getHeight() * 1.2f)),
				GameAssets.ReturnIcon.getWidth(), GameAssets.ReturnIcon.getHeight(), game);
		
		GameAssets.MassRender[1] = new ButtonBehaviour(ToggleUI(GameAssets.SFXToggle), 
				(Gdx.graphics.getWidth() - ToggleUI(GameAssets.SFXToggle).getWidth()) * 0.4f, 
				(Gdx.graphics.getHeight() - ToggleUI(GameAssets.SFXToggle).getHeight()) * 0.7f, 
				ToggleUI(GameAssets.SFXToggle).getWidth(), ToggleUI(GameAssets.SFXToggle).getHeight(), game);
		
		GameAssets.MassRender[2] = new ButtonBehaviour(ToggleUI(GameAssets.MusicToggle), 
				(Gdx.graphics.getWidth() - ToggleUI(GameAssets.MusicToggle).getWidth()) * 0.4f, 
				(Gdx.graphics.getHeight() - ToggleUI(GameAssets.MusicToggle).getHeight()) * 0.3f, 
				ToggleUI(GameAssets.MusicToggle).getWidth(), ToggleUI(GameAssets.MusicToggle).getHeight(), game);
	}

	@Override
	public void render(float delta) {

		ScreenUtils.clear(0, 0, 0, 0);// clear screen
		Buttons(); //this has to be placed here because boolean has to be checked every frame to update texture
		InputDetection(); //checks for input every frame
		GameAssets.batch.begin(); // begin rendering
		
		// Render MainMenuBg
		GameAssets.batch.draw(GameAssets.MainMenuBg_Blur, 0, 0, GameAssets.MainMenuBg_Blur.getWidth(),
				GameAssets.MainMenuBg_Blur.getHeight());
		
		GameAssets.font.draw(GameAssets.batch, "SFX VOLUME", 950, 750);
		GameAssets.font.draw(GameAssets.batch, "MUSIC VOLUME", 950, 415);

		// render interactive buttons
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
		GameAssets.MainMenuBg_Blur.dispose();
		GameAssets.ReturnIcon.dispose();
		GameAssets.VolumeMuteIcon.dispose();
		GameAssets.VolumeUnmuteIcon.dispose();
		GameAssets.font.dispose();
		GameAssets.batch.dispose();
		GameAssets.InGameMouse.dispose();
		
		for (ButtonBehaviour button : GameAssets.MassRender) {
			button.dispose();
		}
	}
	
	public void InputDetection() {
		// logic check to see which button is being pressed
		for (int i = 0; i < GameAssets.MassRender.length; i++) {
			if (GameAssets.MassRender[i].getRectangle().contains(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {
				switch (i) {
				case 0:
					game.setScreen(new MainMenuScreen(game));
					break;
				case 1:
					if (GameAssets.SFXToggle) {
						GameAssets.SFXToggle = false;
					}
					else if (!GameAssets.SFXToggle) {
						GameAssets.SFXToggle = true;
					}
					break;
				case 2:
					if (GameAssets.MusicToggle) {
						GameAssets.MusicToggle = false;
					}
					else if (!GameAssets.MusicToggle) {
						GameAssets.MusicToggle = true;
					}
				default:
					break;
				}
			}
		}
	}
	
	public Texture ToggleUI(boolean status) {
		if (status) {
			return GameAssets.VolumeUnmuteIcon;
		}
		else {
			return GameAssets.VolumeMuteIcon;
		}
	}

}
