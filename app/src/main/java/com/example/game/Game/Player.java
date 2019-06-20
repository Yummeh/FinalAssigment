package com.example.game.Game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

class Player implements GameObject {
	
	public BoundingBox boundingBox;
	public boolean kill = false;
	
	protected PointF position, velocity, size;
	int speed = 0;
	boolean move = false;
	protected float resistance = 0.8f;
	
	Rect rect;
	Paint paint;
	SurfaceHolder surfaceHolder;
	int screenWidth;
	
	public Player(SurfaceHolder surfaceHolder, PointF position, PointF velocity, PointF size, int color) {
		this.surfaceHolder = surfaceHolder;
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		
		boundingBox = new BoundingBox(position, (int) size.x, (int) size.y);
		
		screenWidth = surfaceHolder.getSurfaceFrame().width();
		paint = new Paint();
		paint.setColor(color);
		
		rect = new Rect((int) position.x, (int) position.y, (int) position.x + (int) size.x, (int) position.y + (int) size.y);
	}
	
	@Override
	public void update() {
		
		position.x += velocity.x;
		position.y += velocity.y;
		rect.set((int) position.x, (int) position.y, (int) position.x + (int) size.x, (int) position.y + (int) size.y);
		
		velocity.x *= resistance;
		velocity.y *= resistance;
		
		if (move) {
			velocity.x = speed;
		}
		
		clipInsideScreen();
	}
	
	private void clipInsideScreen() {
		if (position.x < 0) {
			position.x = size.x;
		}
		if (position.x + size.x * 2 > surfaceHolder.getSurfaceFrame().width()) {
			position.x = surfaceHolder.getSurfaceFrame().width() - size.x * 2;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(rect, paint);
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				move = true;
				break;
			case MotionEvent.ACTION_UP:
				move = false;
				break;
		}
		if (event.getX() < surfaceHolder.getSurfaceFrame().width() / 2) {
			speed = -20;
		} else {
			speed = 20;
		}
		
	}
	
}
