package com.example.game.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.game.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HighScoresActivity extends AppCompatActivity {
	
	private Button getScore;
	private TextView score;
	private FirebaseDatabase database;
	private DatabaseReference myRef;
	
	private int i = 0;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscores);
		database = FirebaseDatabase.getInstance();
		getScore = findViewById(R.id.getString);
		
		getScore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				myRef = database.getReference().child("Member");
				myRef.setValue(i);
				i++;
				score.setText(database.getReference().child("Member").toString());
			}
		});
		
		
		score = findViewById(R.id.fbScore);
	}
}
