package com.example.dhu_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class LodingActivity extends Activity {
    int RODING_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LodingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, RODING_TIME);
    }
}
