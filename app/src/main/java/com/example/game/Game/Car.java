package com.example.game.Game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Car implements GameObject {
	
	public BoundingBox boundingBox;
	public boolean kill = false;
	
	private PointF position, velocity, size;
	Rect rect;
	Paint paint;
	
	int time = 500;
	
	public Car(PointF position, PointF velocity, PointF size, int color) {
		this.position = position;
		this.velocity = velocity;
		this.size = size;
		
		boundingBox = new BoundingBox(position, (int) size.x, (int) size.y);
		
		paint = new Paint();
		paint.setColor(color);
		
		rect = new Rect((int) position.x, (int) position.y, (int) position.x + (int) size.x, (int) position.y + (int) size.y);
	}
	
	
	@Override
	public void update() {
		position.x += velocity.x;
		position.y += velocity.y;
		rect.set((int) position.x, (int) position.y, (int) position.x + (int) size.x, (int) position.y + (int) size.y);
		
		if (time <= 0) {
			kill = true;
		} else {
			time--;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawRect(rect, paint);
	}
	
	@Override
	public void onTouchEvent(MotionEvent event) {
		
	}
	
}
