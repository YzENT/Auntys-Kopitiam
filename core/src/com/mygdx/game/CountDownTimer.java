package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;

public class CountDownTimer {

	GameAssets game;
	public boolean TimerStopped;
	public boolean TimerPaused;
	public boolean TimerActive;
	private float TimeLeft;
	float Delay;
	float Interval;
	private float MaxTime;
	private Timer time = new Timer();
	private Timer.Task CountDown;
	
	
	public CountDownTimer(float delay, float interval) {
		Delay = delay;
		Interval = interval;
	}

	public void StatusCheck() {
		if (TimeLeft <= 0 && !TimerActive) {
			reset();
		} else {
			start();
		}
	}

	public float getTimeLeft() {
		return TimeLeft;
	}

	public void setTimeLeft(float timeLeft) {
		TimeLeft = timeLeft;
	}

	public float getMaxTime() {
		return MaxTime;
	}

	public void setMaxTime(float maxTime) {
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
					if (TimeLeft <= 0) {
						stop();
					} else if (!TimerStopped) {
						TimeLeft = TimeLeft - Delay;
					}
				}
			};
			time.scheduleTask(CountDown, Delay, Interval);
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
			time.scheduleTask(CountDown, Delay, Interval);
			TimerPaused = false;
		}
	}

	public void dispose() {
		game.dispose();
		GameAssets.CountDownTimer.dispose();
	}
}

