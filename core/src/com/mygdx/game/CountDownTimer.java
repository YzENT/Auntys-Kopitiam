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
		//if timer = 0 seconds AND is NOT active then resets
		if (TimeLeft <= 0 && !TimerActive) {
			reset();
		} else { //if not then start timer
			start();
		}
	}

	public float getTimeLeft() {
		//returns TimeLeft in class CountDownTimer
		return TimeLeft;
	}

	public void setTimeLeft(float timeLeft) {
		//sets TimeLeft in class CountDownTimer
		TimeLeft = timeLeft;
	}

	public float getMaxTime() {
		//returns MaxTime in class CountDownTimer
		return MaxTime;
	}

	public void setMaxTime(float maxTime) {
		//sets MaxTime in class CountDownTimer
		MaxTime = maxTime;
	}



	public void reset() {
		//method to reset timer
		TimeLeft = getMaxTime();
		TimerStopped = false;
		TimerPaused = false;
		TimerActive = false;
		start();
	}

	public void start() {
		//method to create timer
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
		//method to stop timer
		if (TimerActive) {
			CountDown.cancel();
			TimerStopped = true;
			TimerActive = false;
		}
	}

	public void pause() {
		//pauses timer only if its active AND NOT paused
		if (TimerActive && !TimerPaused) {
			CountDown.cancel();
			TimerPaused = true;
		}
	}

	public void resume() {
		//resumes timer only if there is an active timer AND it's paused
		if (TimerActive && TimerPaused) {
			time.scheduleTask(CountDown, Delay, Interval);
			TimerPaused = false;
		}
	}

	public void dispose() {

		/*
		 * Disposal of resources used in this class
		 */

		game.dispose();
		GameAssets.CountDownTimer.dispose();
	}
}

