package com.example.androidsandbox;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements
		android.view.View.OnClickListener {

	private TextView textProgressView;

	private Handler handler = new Handler();

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

				textProgressView.setText("ll" + progress);
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

		// Wersja z Handlerem
		handler.post(new Runnable() {

			@Override
			public void run() {
				System.err.println("HANDLER OK");

			}
		});

		System.err.println("onClick() " + Thread.currentThread());

		// Wersja z AsyncTask + przetwarzanie szeregowe
		new MyAsyncTask().execute(12);

		// Wersja z AsyncTask + przetwarzanie równoległe
		new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 12);

	}

	class MyAsyncTask extends AsyncTask<Integer, Integer, Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			System.err.println("OK doInBackground() " + Thread.currentThread());

			for (int i = 0; i < 10; i++) {
				// zrob coś cząstkowego

				// update progresu
				publishProgress(i);
			}

			return null;
		}

		// wywoła się w wątku UI (!!!)
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			System.err.println("Progres: " + values[0]*10 + ", " + Thread.currentThread());
		}

	}

}
