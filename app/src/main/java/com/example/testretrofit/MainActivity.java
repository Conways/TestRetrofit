package com.example.testretrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		((Button) this.findViewById(R.id.get)).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new GetFamilyNoTask().execute();
				// new CookClient().getCookDetialById(10);

			}
		});
	}

	class GetFamilyNoTask extends AsyncTask<String, String, HttpResult> {

		private ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setCancelable(false);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected HttpResult doInBackground(String... params) {
			return new CookClient().getCookDetialById(5);
		}

		@Override
		protected void onPostExecute(HttpResult result) {
			dialog.dismiss();
			if (result.getBaseModel() != null) {
				Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(MainActivity.this, result.getState(), Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}

	}
	class GetFamilyNoTas extends AsyncTask<String, String, String> {
		
		private ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(MainActivity.this);
			dialog.setCancelable(false);
			dialog.show();
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(String... params) {
			return new CookClient().request();
		}
		
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			if (result!= null) {
				Toast.makeText(MainActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}
			
			super.onPostExecute(result);
		}
		
	}

}
