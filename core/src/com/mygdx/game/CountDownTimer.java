package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;

public class CountDownTimer {

	GameAssets game;
	public static boolean TimerStopped;
	public static boolean TimerPaused;
	public static boolean TimerActive;
	public static int TimeLeft;
	private int MaxTime;
	private Timer time = new Timer();
	private Timer.Task CountDown;
	
	
	public CountDownTimer() {
	}

	public void StatusCheck() {
		if (CountDownTimer.TimeLeft <= 0 && !TimerActive) {
			reset();
		} else {
			start();
		}
	}

	public int getMaxTime() {
		return MaxTime;
	}

	public void setMaxTime(int maxTime) {
		this.MaxTime = maxTime;
	}



	public void reset() {
		TimeLeft = getMaxTime();
		TimerStopped = false;
		TimerPaused = false;
		TimerActive = false;
		start();
	}

	public void start() {
		if (!TimerActive) {
			CountDown = new Timer.Task() {
				@Override
				public void run() {
					if (CountDownTimer.TimeLeft <= 0) {
						stop();
					} else if (!TimerStopped) {
						CountDownTimer.TimeLeft = CountDownTimer.TimeLeft - 1;
					}
				}
			};
			time.scheduleTask(CountDown, 1, 1);
			TimerActive = true;
		}
	}

	public void stop() {
		if (TimerActive) {
			CountDown.cancel();
			TimerStopped = true;
			TimerActive = false;
		}
	}

	public void pause() {
		if (TimerActive && !TimerPaused) {
			CountDown.cancel();
			TimerPaused = true;
		}
	}

	public void resume() {
		if (TimerActive && TimerPaused) {
			time.scheduleTask(CountDown, 1, 1);
			TimerPaused = false;
		}
	}

	public void dispose() {
		game.dispose();
		GameAssets.CountDownTimer.dispose();
	}
}

