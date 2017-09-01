package com.example.haruka.rescue_aid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.haruka.rescue_aid.R;

/**
 * Created by Tomoya on 8/24/2017 AD.
 */

public class TestPlatformActivity extends AppCompatActivity {

    private Button gotoTestBtn1, gotoTestBtn2, gotoTestBtn3, gotoTestBtn4, gotoTestBtn5, gotoTestBtn6;
    private Intent testIntent1, testIntent2, testIntent3, testIntent4, testIntent5, testIntent6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_platform);

        gotoTestBtn1 = (Button)findViewById(R.id.gotoTestBtn1);
        gotoTestBtn2 = (Button)findViewById(R.id.gotoTestBtn2);
        gotoTestBtn3 = (Button)findViewById(R.id.gotoTestBtn3);
        gotoTestBtn4 = (Button)findViewById(R.id.gotoTestBtn4);
        gotoTestBtn5 = (Button)findViewById(R.id.gotoTestBtn5);
        gotoTestBtn6 = (Button)findViewById(R.id.gotoTestBtn6);

        testIntent1 = new Intent(this, VoiceRecognizeActivity.class);
        gotoTestBtn1.setText("音声認識テスト");
        gotoTestBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent1);
            }
        });

        testIntent2 = new Intent(this, ShakeTestActivity.class);
        gotoTestBtn2.setText("振動認識テスト");
        gotoTestBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent2);
            }
        });

        testIntent3 = new Intent(this, CardiotachometerActivity.class);
        gotoTestBtn3.setText("心拍数測定テスト");
        gotoTestBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent3);
            }
        });

        //testIntent4 = new Intent(this, LightTestActivity.class);
        testIntent4 = new Intent(this, CVCameraTestActivity.class);
        gotoTestBtn4.setText("フラッシュライトテスト");
        gotoTestBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent4);
            }
        });

        testIntent5 = new Intent(this, LocationTestActivity.class);
        gotoTestBtn5.setText("位置情報テスト");
        gotoTestBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent5);
            }
        });

        testIntent6 = new Intent(this, MetronomeTestActivity.class);
        gotoTestBtn6.setText("メトロノームテスト");
        gotoTestBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(testIntent6);
            }
        });
    }


}