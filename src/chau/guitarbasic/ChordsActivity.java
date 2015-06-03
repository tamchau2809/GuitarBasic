package chau.guitarbasic;

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;
import android.view.View;
import android.content.Intent;

public class ChordsActivity extends Activity {

	ListView lvData;
	ChordAdapter adapter;
	
	EditText ed;
	
	String _name;
	String _chord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chords);
		
		isCreateDB();

		lvData = (ListView) findViewById(R.id.listView1);
		adapter = new ChordAdapter(this);
		adapter.setListView(getAllSinhvien());
		lvData.setAdapter(adapter);
		
		ed = (EditText)findViewById(R.id.editText1);
		
		//lấy vị trí click, và cho hiện hợp âm ở vị trí click
		lvData.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int pos, long id) {
				Intent intent = new Intent(ChordsActivity.this, ChordViewActivity.class);
				Bundle bundle = new Bundle();
				
				//_name = parent.getItemAtPosition(pos).toString();
				
				bundle.putInt("KEY", pos);
				intent.putExtra("ab", bundle);
				startActivity(intent);
			}
		});
	}
	
	private ArrayList<Object> getAllSinhvien() {
		ChordDatabase data = new ChordDatabase(this);
		return data.getAllChord();
	}
	
	private boolean isCreateDB() {
		ChordDatabase data = new ChordDatabase(this);
		try {
			return data.isCreatedDatabase();
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
	}
}
