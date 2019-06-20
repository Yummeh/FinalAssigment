package com.example.game.View;

public class Stopwatchy {
	
	private boolean active = false;
	
	private long start = 0;
	private long finish = 0;
	
	public Stopwatchy() {
	}
	
	public void start() {
		active = true;
		start = System.currentTimeMillis() / 100;
	}
	
	public void stop() {
		active = false;
		finish = System.currentTimeMillis() / 100;
	}
	
	public long getElapsedTime() {
		if (active) {
			return System.currentTimeMillis() / 100 - start;
		} else {
			return finish - start;
		}
	}
}
