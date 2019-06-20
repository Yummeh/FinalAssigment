package com.example.game.Game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;;
import com.example.game.View.PopupActivity;
import com.example.game.R;
import com.example.game.View.StartActivity;
import com.example.game.View.Stopwatchy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.DialogFragment;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
	private MainThread mThread;
	private boolean activityStarted = false;
	public int gameStateIndex = 0;
	private List<GameState> gameStates;
	private GameState mGameState;
	private Context context;
	private Activity activity;
	private DialogFragment dialog;
	private Button mTryAgain, mReturnToHome;
	private Paint mScorePaint = new Paint();
	private DecimalFormat df = new DecimalFormat("0.#");
	private SurfaceHolder holder;
	
	public GameView(Context context, Activity activity) {
		super(context);
		getHolder().addCallback(this);
		mThread = new MainThread(getHolder(), this);
		setFocusable(true);
		this.context = context;
		this.activity = activity;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread = new MainThread(getHolder(), this);
		dialog = new DialogFragment();
		gameStates = new ArrayList<>();
		gameStates.add(new RacingState(getHolder()));
		
		mGameState = gameStates.get(gameStateIndex);
		mScorePaint.setColor(Color.WHITE);
		mScorePaint.setTextSize(60);
		mScorePaint.setTextAlign(Paint.Align.LEFT);
		mThread.setRunning(true);
		mThread.start();
		StartActivity.mStopwatchy = new Stopwatchy();
		StartActivity.mStopwatchy.start();
		this.holder = holder;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			mThread.setRunning(false);
			mThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if (mGameState.gameOver && !activityStarted) {
			activityStarted = true;
			
			//mThread.setRunning(false);
			StartActivity.mStopwatchy.stop();
			Intent intent = new Intent(context, PopupActivity.class);
			context.startActivity(intent);
			activity.finish();
		}
		mGameState.update();
	}
	
	private void dialogPopup() {
		activity.setContentView(R.layout.game_over_popup);
		
		mTryAgain = findViewById(R.id.tryAgainButton);
		mReturnToHome = findViewById(R.id.returnButton);
		mTryAgain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.recreate();
			}
		});
		mReturnToHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				activity.finish();
			}
		});
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		mGameState.draw(canvas);
		canvas.drawText(String.valueOf(df.format(StartActivity.mStopwatchy.getElapsedTime())), 180, 60, mScorePaint);
		canvas.drawText("Score: ", 0, 60, mScorePaint);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGameState.onTouchEvent(event);
		return true;
	}
}