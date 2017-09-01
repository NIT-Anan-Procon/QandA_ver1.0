package com.example.haruka.rescue_aid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haruka.rescue_aid.R;
import com.example.haruka.rescue_aid.utils.EmergencyExplanation;

import java.util.ArrayList;

import jp.fsoriented.cactusmetronome.lib.Click;
import jp.fsoriented.cactusmetronome.lib.DefaultHighClickCallback;
import jp.fsoriented.cactusmetronome.lib.Metronome;

/**
 * Created by Tomoya on 9/1/2017 AD.
 */

public class MetronomeTestActivity  extends AppCompatActivity {

    Metronome mMetronome;
    Button metronomeButton;

    TextView textView;
    ImageView imageView;


    private static class BpmUtil {
        public static int getSampleLength(double bpm) {
            // 1beatあたりの長さ（sample）
            return (int)(60 * Metronome.FREQUENCY / bpm);
        }
    }

    private static enum NoteEnum {
        // basic notes
        BASIC_4(new double[]{0}, 1.0/8),
        BASIC_8(new double[]{0, 0.5}, 1.0/8);

        // 4分音符の長さを0..1としたときに、クリックがどこにあるかを表す
        // Clickを作るもとになる

        /** いつ発音するか */
        private final double[] beats;
        /** 音の長さ */
        private final double length;

        /** コンストラクタ */
        private NoteEnum(double[] beats, double length) {
            this.beats = beats;
            this.length = length;
        }

        /**
         * 指定されたリストに、この{@code NoteEnum}が表す音を追加する。
         *
         * @param destination Clickの書き込み先
         * @param lengthOfQuarter 4分音符の長さ（サンプル）
         * @param index 何個目の4分音符か。0はじまり。
         */
        public void addNewClicks(ArrayList<Click> destination, int lengthOfQuarter, int index) {
            for (int i=0; i<beats.length; i++) {
                double beat = beats[i];
                int when = (int)(beat * lengthOfQuarter) + lengthOfQuarter * index;
                int len = (int)(length * lengthOfQuarter);
                Click c;
                if (index == 0 && i == 0) {
                    c = new Click(when, len, new DefaultHighClickCallback());
                } else {
                    c = new Click(when, len);
                }
                destination.add(c);
            }
        }
    }

    public void onStartClick(View view) {
        mMetronome.finish();

        int tempo = 100;

        // 再生するクリック音のリストを作成する
        ArrayList<Click> list = new ArrayList<Click>();
        int samples = BpmUtil.getSampleLength(tempo);
        int beatsPerMeasure = 4;
        NoteEnum note = NoteEnum.BASIC_4;
        for (int i=0; i<beatsPerMeasure ; i++) {
            note.addNewClicks(list, samples, i);
        }

        // 再生する
        mMetronome.start();
        mMetronome.setPattern(list, samples * beatsPerMeasure);
    }

    public void onStopClick(View view) {
        mMetronome.finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome_test);


        textView = (TextView)findViewById(R.id.textview_explain_heart_massage);
        EmergencyExplanation emergencyExplanation = new EmergencyExplanation(this, "");
        textView.setText(emergencyExplanation.getText());
        imageView = (ImageView)findViewById(R.id.imageview_explain_heart_massage);
        imageView.setImageDrawable(emergencyExplanation.getImage());

        mMetronome = new Metronome();
        metronomeButton = (Button)findViewById(R.id.btn_metronome);
        metronomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onStartClick(v);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        mMetronome.finish();
    }



}
