package com.example.game.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.game.Game.GameView;
import com.example.game.R;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
	private Context mContext = this;
	private Activity mActivity = this;
	
	public static Stopwatchy mStopwatchy = new Stopwatchy();
	
	private Button mStartGame;
	private Button mHighscores;
	private boolean running;
	
	private Dialog dialog;
	private Button tryAgain, returnToHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		Log.d("hello", "yes");
		dialog = new Dialog(this);
		mStartGame = findViewById(R.id.startGame);
		mHighscores = findViewById(R.id.highScores);
		
		
		
		mStartGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this, GameActivity.class);
				startActivity(intent);
				StartActivity.mStopwatchy.start();
				overridePendingTransition(0,0);
			}
		});
		
		mHighscores.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this, HighScoresActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public void setStartView() {
		setContentView(R.layout.activity_start_screen);
	}
	
}
