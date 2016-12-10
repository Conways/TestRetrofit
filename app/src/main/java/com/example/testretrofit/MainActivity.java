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

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);

        ((Button) this.findViewById(R.id.get)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                CookClient.getInstance().getCookDetialById(5, new HttpResultCallBack() {

                    @Override
                    public void onStart() {
                        progressDialog.setMessage("请求中..");
                        progressDialog.show();
                    }

                    @Override
                    public void onSuccuss(BaseModel baseModel) {
                        Toast.makeText(MainActivity.this, (((CookDetail) baseModel).toString()),
                                Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailed(FailedResult failedResult) {
                        Toast.makeText(MainActivity.this, failedResult.getMsg(),
                                Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

            }
        });
    }


}
