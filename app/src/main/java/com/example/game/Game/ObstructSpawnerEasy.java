package com.example.game.Game;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import java.util.Random;

class ObstructSpawnerEasy {
	
	private PointF velocity = new PointF(0, 20);
	private PointF spawnSize = new PointF(50, 100);
	private PointF spawnPos = new PointF(0, 10);
	
	private float difficultyTimer;
	private float difficultyDelay = 60;
	
	
	private float timeUntilSpawn;
	private float spawnDelay = 70;
	
	private int spawned = 0;
	
	private float width;
	private float heigth;
	
	private Random randomizer;
	
	public ObstructSpawnerEasy(float width, float height) {
		this.width = width;
		this.heigth = height;
		randomizer = new Random();
		timeUntilSpawn = spawnDelay / 3;
		randomizePosition();
		randomizeVelocity();
	}
	
	public Car spawn() {
		checkDifficulty();
		
		if (timeUntilSpawn <= 0) {
			randomizePosition();
			randomizeVelocity();
			timeUntilSpawn = spawnDelay;
			
			
			spawned++;
			return new Car(spawnPos, velocity, spawnSize, Color.RED);
		} else {
			timeUntilSpawn--;
		}
		
		return null;
	}
	
	private void checkDifficulty() {
		if (difficultyTimer <= 0) {
			difficultyTimer = difficultyDelay;
			if (spawnDelay > 8) {
				spawnDelay -= 2;
			}
			if (spawnDelay < 8) {
				spawnDelay = 8;
			}
			return;
		}
		difficultyTimer--;
	}
	
	private void randomizePosition() {
		spawnPos = new PointF(randomizer.nextInt((int) width - (int) (width / 8) - (int) spawnSize.x) + (width / 8) - spawnSize.x, 0);
	}
	
	private void randomizeVelocity() {
		velocity = new PointF(0, randomizer.nextInt(15) + 14);
	}
}
	
