package chau.guitarbasic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mBtnChords, mBtnTuner, mBtnAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mBtnChords = (Button)findViewById(R.id.btn1);
		mBtnChords.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent("chau.guitarbasic.ChordsActivity"));
				Intent intent = new Intent(MainActivity.this,ChordsActivity.class);
				startActivity(intent);
			}
		});
		
		
		mBtnAbout = (Button)findViewById(R.id.btn3);
		mBtnAbout.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,AboutActivity.class);
				startActivity(intent);
			}
		});
		
		mBtnTuner = (Button)findViewById(R.id.btn2);
		mBtnTuner.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,TunerActivity.class);
				startActivity(intent);
			}
		});
	}
}
