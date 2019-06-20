package com.example.game.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

public class GameState implements GameObject {
	
	public boolean gameOver = false;
	public List<GameObject> gameObjects;
	
	public GameState () {
		gameObjects = new ArrayList<>();
	}
	
	@Override
	public void update() {
		int i = 0;
		while (i < gameObjects.size()) {
			gameObjects.get(i).update();
			i++;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		int i = 0;
		while (i < gameObjects.size()) {
			gameObjects.get(i).draw(canvas);
			i++;
		}
	}
	
	@Override
	public void onTouchEvent(MotionEvent motionEvent) {
		int i = 0;
		while (i < gameObjects.size()) {
			gameObjects.get(i).onTouchEvent(motionEvent);
			i++;
		}
	}
}
