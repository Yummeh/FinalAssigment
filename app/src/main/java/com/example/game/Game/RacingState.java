package com.example.game.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.Random;

public class RacingState extends GameState {
	
	private int spawnDelay = 0;
	
	private int maxDelay = 80;
	private int minDealy = 40;
	
	private int maxSpeed = 40;
	private int minSpeed = 15;
	
	private int maxSize = 100;
	private int minSize = 40;
	
	private boolean spawnObstructs;
	
	
	private SurfaceHolder surfaceHolder;
	private Random random;
	private ObstructSpawnerEasy obstructSpawnerEasy;
	
	public RacingState(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
		random = new Random();
		obstructSpawnerEasy = new ObstructSpawnerEasy(surfaceHolder.getSurfaceFrame().width(), surfaceHolder.getSurfaceFrame().height());
		int px = surfaceHolder.getSurfaceFrame().width() / 2;
		int py = surfaceHolder.getSurfaceFrame().height() / 10 * 7;
		
		gameObjects.add(new Player(surfaceHolder, new PointF(px, py), new PointF(0, 0), new PointF(50, 80), Color.GREEN));
	}
	
	@Override
	public void update() {
		super.update();
		
		Car g = obstructSpawnerEasy.spawn();
		if (g != null) {
			gameObjects.add(g);
		}
		
		int j = 1;
		while (j < gameObjects.size()) {
			Car A = (Car) gameObjects.get(j);
			Player B = (Player) gameObjects.get(0);
			if (detectCollision(A.boundingBox, B.boundingBox)) {
				gameOver = true;
			}
			j++;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
	}
	
	boolean detectCollision(BoundingBox A, BoundingBox B) {
		if (A.getX() < B.getX() + B.getWidth() &&
				A.getX() + A.getWidth() > B.getX() &&
				A.getY() < B.getY() + B.getHeight() &&
				A.getY() + A.getHeight() > B.getY()) {
			return true;
		}
		return false;
	}
}
