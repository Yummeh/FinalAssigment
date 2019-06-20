package com.example.game.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.game.Game.GameView;

public class GameActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this, this));
	}
}
