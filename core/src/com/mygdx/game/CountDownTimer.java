package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;

public class CountDownTimer {

	GameAssets game;

	public CountDownTimer() {
	}

	public void StatusCheck() {
		if (GameAssets.TimeLeft <= 0 && !GameAssets.TimerActive) {
			reset();
		} else {
			start();
		}
	}

	public void reset() {
		GameAssets.TimeLeft = 90;
		GameAssets.TimerStopped = false;
		GameAssets.TimerPaused = false;
		GameAssets.TimerActive = false;
		start();
	}

	public void start() {
		if (!GameAssets.TimerActive) {
			GameAssets.CountDown = new Timer.Task() {
				@Override
				public void run() {
					if (GameAssets.TimeLeft <= 0) {
						stop();
					} else if (!GameAssets.TimerStopped) {
						GameAssets.TimeLeft = GameAssets.TimeLeft - 1;
					}
				}
			};
			GameAssets.time.scheduleTask(GameAssets.CountDown, 1, 1);
			GameAssets.TimerActive = true;
		}
	}

	public void stop() {
		if (GameAssets.TimerActive) {
			GameAssets.CountDown.cancel();
			GameAssets.TimerStopped = true;
			GameAssets.TimerActive = false;
		}
	}

	public void pause() {
		if (GameAssets.TimerActive && !GameAssets.TimerPaused) {
			GameAssets.CountDown.cancel();
			GameAssets.TimerPaused = true;
		}
	}

	public void resume() {
		if (GameAssets.TimerActive && GameAssets.TimerPaused) {
			GameAssets.time.scheduleTask(GameAssets.CountDown, 1, 1);
			GameAssets.TimerPaused = false;
		}
	}

	public void dispose() {
		game.dispose();
		GameAssets.CountDownTimer.dispose();
	}
}
