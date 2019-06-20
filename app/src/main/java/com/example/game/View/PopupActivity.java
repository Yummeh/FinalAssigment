package com.example.game.View;


import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.game.Game.GameView;
import com.example.game.R;

import androidx.appcompat.app.AppCompatActivity;

public class PopupActivity extends AppCompatActivity {
	
	private Button mTryAgain, home;
	private TextView score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over_popup);
		
		score = findViewById(R.id.score);
		score.setText(String.valueOf(StartActivity.mStopwatchy.getElapsedTime()));
		
		mTryAgain = findViewById(R.id.tryAgainButton);
		mTryAgain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PopupActivity.this, GameActivity.class);
				finish();
				startActivity(intent);
				overridePendingTransition(0,0);
				
			}
		});
		
		home = findViewById(R.id.returnButton);
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		//TODO you know what to do here, set scores and buttons to play again compare highscores maybe
		
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int heigth = dm.heightPixels;
		
		getWindow().setLayout((int) (width*.8), (int) (heigth*.7));
	}
}
