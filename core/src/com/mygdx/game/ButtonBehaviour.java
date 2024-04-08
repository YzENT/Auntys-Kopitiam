package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ButtonBehaviour {

	GameAssets game;

	private Texture ButtonImage;
	private Rectangle RectangleButton;

	public ButtonBehaviour(Texture ButtonRenderPrep, float x, float y, float width, float height, GameAssets game) {
		// takes in data
		ButtonImage = ButtonRenderPrep;
		RectangleButton = new Rectangle();
		RectangleButton.x = x;
		RectangleButton.y = y;
		RectangleButton.width = width;
		RectangleButton.height = height;
		this.game = game;
	}

	public Rectangle getRectangle() {
		// collision detection
		return RectangleButton;
	}

	public void render() {

		// scale for button zoom when mouse overlaps
		float ButtonScaleWhenMouseOverlap = 1.1f;

		// if overlap then increase in size
		if (RectangleButton.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())) {
			ButtonImage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

			GameAssets.batch.draw(
				ButtonImage,
				RectangleButton.x - ((RectangleButton.width * ButtonScaleWhenMouseOverlap) - RectangleButton.width) / 2,
				RectangleButton.y - ((RectangleButton.height * ButtonScaleWhenMouseOverlap) - RectangleButton.height) / 2,
				RectangleButton.width * ButtonScaleWhenMouseOverlap,
				RectangleButton.height * ButtonScaleWhenMouseOverlap
			);

		} else { // else just render the normal dimension
			ButtonImage.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

			GameAssets.batch.draw(
				ButtonImage, 
				RectangleButton.x, 
				RectangleButton.y, 
				RectangleButton.width,
				RectangleButton.height
				);
		}

	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();
		ButtonImage.dispose();
		GameAssets.batch.dispose();
	}

}
