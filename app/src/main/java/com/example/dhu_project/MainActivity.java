package com.example.dhu_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_normal;
    TextView tv_hard;
    TextView tv_stop;
    TextView tv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_normal = (TextView) findViewById(R.id.main_normal);
        tv_hard = (TextView) findViewById(R.id.main_hard);
        tv_stop = (TextView) findViewById(R.id.main_stop);
        tv_record = (TextView) findViewById(R.id.main_record);

        tv_normal.setOnClickListener(this);
        tv_hard.setOnClickListener(this);
        tv_stop.setOnClickListener(this);
        tv_record.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_normal:
                newActivity(this, NormalActivity.class);
                break;
            case R.id.main_hard:
                newActivity(this, HardActivity.class);
                break;
            case R.id.main_stop:
                newActivity(this, StopActivity.class);
                break;
            case R.id.main_record:
                newActivity(this, RecordActivity.class);
                break;
        }
    }

    public void newActivity(Context context, Class<?> c) {
        Intent intent = new Intent(context, c);
        startActivity(intent);
        finish();
    }
}