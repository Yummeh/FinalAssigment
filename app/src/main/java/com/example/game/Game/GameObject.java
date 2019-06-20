package com.example.game.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameObject {
	void update();
	void draw(Canvas canvas);
	void onTouchEvent(MotionEvent event);
}
