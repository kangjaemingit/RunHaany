package com.example.dhu_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HardActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView vv;
    private String str_videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";

    private TextView tv_time_mm;
    private TextView tv_time_ss;
    private TextView tv_time_pp;
    private TextView tv_bpm;

    private Button btn_end;

    CountDownTimer countDownTimer;

    RelativeLayout relativeLayout;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        // TextView 연결
        tv_time_mm = findViewById(R.id.time_mm);
        tv_time_ss = findViewById(R.id.time_ss);
        tv_time_pp = findViewById(R.id.time_pp);
        tv_bpm = findViewById(R.id.bpm);

        // 레이아웃 연결
        relativeLayout = findViewById(R.id.relativelayout);
        linearLayout = findViewById(R.id.end_BoxLayout);

        // 버튼 연결
        btn_end = findViewById(R.id.btn_end);


        // CountDown
        countDownTimer = new CountDownTimer(5*1000,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 시간을 분, 초, 밀리초 단위로 보여주게 하기 0.01 초 단위
                tv_time_mm.setText(""+millisUntilFinished/(60*1000));
                millisUntilFinished = millisUntilFinished%(60*1000);
                tv_time_ss.setText(":"+millisUntilFinished/(1000));
                millisUntilFinished = millisUntilFinished%(1000);
                tv_time_pp.setText("."+millisUntilFinished/(10));
            }

            @Override
            public void onFinish() {
                // 끝났다는 메시지 띄우기
                // 숨겨놓은 리니어 레이아웃 보이기
                linearLayout.setVisibility(View.VISIBLE);
                btn_end.setOnClickListener(HardActivity.this);
                vv.pause();
                tv_bpm.setText("0");
            }
        };

        // VideoView 연결
        vv = findViewById(R.id.videoVideo_hard);
        vv.setVideoURI(Uri.parse(str_videoUrl));

        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
                countDownTimer.start();
            }
        });

        // 비디오뷰 클릭시 이벤트
        vv.setOnClickListener(this);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        //
        switch (v.getId()){
            case R.id.btn_end:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
        }

        if (vv.isPlaying()) vv.pause();
        else {
            vv.start();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(vv != null && vv.isPlaying()) vv.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(vv != null) vv.stopPlayback();

    }

}
