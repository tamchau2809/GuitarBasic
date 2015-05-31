package chau.guitarbasic;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChordViewActivity extends Activity  {
	
	TextView tvName, tvChord;
	ImageView imgView;
	ScrollView sView;
	
	public static final String tb_Sinhvien = "qlsv";

	public static String DB_PATH = "/data/data/chau.guitarbasic/databases/";
	private static String DB_NAME = "chords.db";
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chord_view);
		
		tvName = (TextView)findViewById(R.id.textView_chordname);
		tvChord = (TextView)findViewById(R.id.textview_chordview);
		
		Intent calledIntent = getIntent();
		Bundle packageFromCaller = calledIntent.getBundleExtra("ab");
		int num = packageFromCaller.getInt("KEY");
		//String ber = String.valueOf(num);
		
		SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
				SQLiteDatabase.OPEN_READWRITE);
		
		String[] columns = { "ID", "Ten", "HopAm" };
		
		Cursor cursor = db.query(tb_Sinhvien, columns, null, null, null, null, null);
		
		cursor.moveToPosition(num);
		tvName.setText(cursor.getString(1));
		tvChord.setText(cursor.getString(2));
		
		imgView = (ImageView)findViewById(R.id.btnScroll);
		imgView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//scrollRight(new );
			}
		});
	}
	
	public void scrollRight(final HorizontalScrollView h){
		new CountDownTimer(2000, 20) { 

		    public void onTick(long millisUntilFinished) { 
		        h.scrollTo((int) (2000 - millisUntilFinished), 0); 
		    } 

		    public void onFinish() { 

		    } 
		 }.start(); }
}
