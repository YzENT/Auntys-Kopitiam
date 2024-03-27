package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class InGameMouseBehaviour {

	public InGameMouseBehaviour() {

		// Hide system cursor
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(GameAssets.cursorPixmap, 0, 0));

	}

	public void render() {

		// Cursor
		float CursorSize = 15;

		// create shape of cursor (just a basic circle)
		GameAssets.ShapeRenderer.begin(ShapeType.Filled);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		GameAssets.ShapeRenderer.setColor(1, 0, 1, 0.5f);

		// if input, play sound
		if (Gdx.input.justTouched()) {
			PlaySFX();
		}

		// cursor changes size when left/right click
		if (Gdx.input.isTouched()
				&& (Gdx.input.isButtonPressed(Input.Buttons.LEFT) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
			CursorSize = CursorSize - 5;
		}
		GameAssets.ShapeRenderer.circle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), CursorSize);
		GameAssets.ShapeRenderer.end();

	}

	public void PlaySFX() {
		if (GameAssets.SFXToggle) {
			GameAssets.MouseClickSFX.play();
		}
	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		GameAssets.ShapeRenderer.dispose();
		GameAssets.MouseClickSFX.dispose();
	}

}
