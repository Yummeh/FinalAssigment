package com.example.game.Game;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;

import java.util.Random;

public class ObstructSpawner {
	
	private final int BASE_MIN_SPAWN_DELAY = 30;
	private final int BASE_MAX_SPAWN_DELAY = 50;
	private final PointF BASE_MIN_SIZE = new PointF(1, 2);
	private final PointF BASE_MAX_SIZE = new PointF(2, 4);
	private final PointF BASE_MIN_VEL = new PointF(0, 1);
	private final PointF BASE_MAX_VEL = new PointF(0, 1.5f);
	
	private boolean spawn = false;
	
	private float speedDiff;
	private float minSpeedDif = 20;
	private float maxSpeedDif = 40;
	private float speedDiffIncrease = 2;
	
	private float sizeDiff;
	private float minSizeDiff = 5;
	private float maxSizeDiff = 12;
	private float sizeDiffIncrease = 1f;
	
	private float spawnDelayDiff;
	private float minSpawnDelayDif = 20;
	private float maxSpawnDelayDif = 100;
	private float spawnDelayDiffIncrease = -5;
	
	private float timeUntilDiffIncrease = 0;
	private float difficultyDelay = 300; //300ms == 10s
	
	private float timeUntilSpawn = 0;
	private float spawnDelay = 0; //milliseconds
	private float minSpawnDelay;
	private float maxSpawnDelay;
	
	private PointF spawnSize;
	private PointF minSpawnSize;
	private PointF maxSpawnSize;
	
	private PointF spawnPos;
	private PointF minSpawnPos;
	private PointF maxSpawnPos;
	
	private PointF velocity;
	private PointF minVel;
	private PointF maxVel;
	
	//private imgs for cars
	
	private float width;
	private float heigth;
	
	private Random randomizer;
	
	
	public ObstructSpawner() {
	}
	
	public ObstructSpawner(float width, float height) {
		this.width = width;
		this.heigth = height;
		randomizer = new Random();
		
		updateSettings();
	}
	
	public void update() {
	}
	
	public Car spawn() {
		updateDifficulty();
		
		if (timeUntilSpawn <= 0) {
			randomizeSpeed();
			randomizeSize();
			randomizeSpawnDelay();
			randomizePosition();
			timeUntilSpawn = spawnDelay;
			
			return new Car(spawnPos, velocity, spawnSize, Color.RED);
		} else {
			timeUntilSpawn--;
		}
		
		return null;
	}
	
	private void updateDifficulty() {
		if (timeUntilDiffIncrease <= 0) {
			if (speedDiff >= minSpeedDif) {
				speedDiff += speedDiffIncrease;
			}
			if (spawnDelayDiff >= minSpawnDelayDif) {
				spawnDelayDiff += spawnDelayDiffIncrease;
			}
			if (sizeDiff >= minSizeDiff) {
				sizeDiff += sizeDiffIncrease;
			}
			updateSettings();
		} else {
			timeUntilDiffIncrease--;
		}
	}
	
	private void updateSettings() {
		maxSpawnSize = new PointF(BASE_MAX_SIZE.x * sizeDiff, BASE_MAX_SIZE.y * sizeDiff);
		minSpawnSize = new PointF(BASE_MIN_SIZE.x * sizeDiff, BASE_MIN_SIZE.y * sizeDiff);
		maxVel = new PointF(0, BASE_MAX_VEL.y * speedDiff);
		minVel = new PointF(0, BASE_MIN_VEL.y * speedDiff);
		maxSpawnDelay = BASE_MAX_SPAWN_DELAY * (3 - 3 * spawnDelayDiff);
		minSpawnDelay = BASE_MIN_SPAWN_DELAY * (3 - 3 * spawnDelayDiff);
		Log.d("Yes", String.valueOf(spawnPos));
	}
	
	
	private void randomizeSpeed() {
		velocity = new PointF(0, randomizer.nextInt((int) maxVel.y) + minVel.y + 1);
	}
	
	private void randomizeSize() {
		spawnSize = new PointF(randomizer.nextInt((int) maxSpawnSize.x) + minSpawnSize.x + 1, randomizer.nextInt((int) maxSpawnSize.y) + minSpawnSize.y + 1);
	}
	
	private void randomizeSpawnDelay() {
		spawnDelay = randomizer.nextInt((int) minSpawnDelay) + maxSpawnDelay + 1;
	}
	
	private void randomizePosition() {
		spawnPos = new PointF(randomizer.nextInt((int) width - (int) spawnSize.x), -spawnSize.y);
	}
	
	
}
