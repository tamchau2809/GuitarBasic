package chau.guitarbasic;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ChordAdapter extends Adapter {

	TextView tvName;
	
	public ChordAdapter(Context context) {
		super(context);
	}

	@Override
	public View getLayout(Object obj, View v, int position) {
		ChordItem emp = (ChordItem) obj;
		if (v == null) {
			v = (View) m_Inflater.inflate(R.layout.activity_chords_listview, null);
		}

		tvName = (TextView) v.findViewById(R.id.textView1);

		tvName.setText(emp.getTen());
		
		return v;
	}
	public void cusFilter(String input)
	{
		input = input.trim().toLowerCase();
	}
}

