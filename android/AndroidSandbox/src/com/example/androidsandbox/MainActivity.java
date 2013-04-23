package com.example.androidsandbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	private TextView textProgressView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textProgressView = (TextView) findViewById(R.id.textProgressValue);

		TextView pTextView = (TextView) findViewById(R.id.textView1);
		pTextView.setText("Alocha");

		Button btnSearch = (Button) findViewById(R.id.buttonSearch);
		btnSearch.setOnClickListener(this);

		Button btnSettings = (Button) findViewById(R.id.buttonSettings);
		btnSettings.setOnClickListener(this);

		
		
		Button btnCancel = (Button) findViewById(R.id.buttonCancel);
		btnCancel.setOnClickListener(this);

		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
				System.err.println("onStopTrackingTouch() " + seekBar);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				System.err.println("onStartTrackingTouch() " + seekBar);
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				textProgressView.setText("ll"+progress);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.err.println("OnPause()");
	}

	@Override
	public void onClick(View v) {
		System.err.println("OnClick() " + v);
	}

}
