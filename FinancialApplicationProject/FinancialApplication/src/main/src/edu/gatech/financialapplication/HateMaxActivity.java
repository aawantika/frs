package edu.gatech.financialapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

public class HateMaxActivity extends Activity{
	boolean didHeFinishTheWork;
	private static final String Message = "FINISH your work. Otherwise, I will find you, and image rest of the parts.";
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.activity_main);
		getActionBar().setTitle("Torture Max Activity for fun LOLOLOLOL");
	    ImageView iwill = (ImageView)findViewById(R.id.imageView2);
	    iwill.setImageDrawable(getResources().getDrawable(R.drawable.iwill));
	    ((ImageView)findViewById(R.id.imageView1)).setImageDrawable(getResources().getDrawable(R.drawable.iwill));
	    mHandler.post(mStatusChecker);
	}
	
	Runnable mStatusChecker = new Runnable() {
		public void run(){
			letMeCheckWhetherHeFinishedTheWork();
			mHandler.postDelayed(mStatusChecker, 1000);
		}
	};
	public void letMeCheckWhetherHeFinishedTheWork(){
		if(!didHeFinishTheWork){
			tortureHim();
		}
	}
	
	public void tortureHim(){
		Toast.makeText(getApplicationContext(),Message,Toast.LENGTH_SHORT).show();

	}
}
