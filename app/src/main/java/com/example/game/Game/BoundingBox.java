package com.example.game.Game;

import android.graphics.PointF;

public class BoundingBox {
	
	private PointF pos;
	private int width;
	private int height;
	
	public PointF getPos() {
		return pos;
	}
	
	public void setPos(PointF pos) {
		this.pos = pos;
	}
	
	public int getWidth() {
		return width;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BoundingBox(PointF pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
	}
}