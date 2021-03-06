package com.example.haruka.rescue_aid.views;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.haruka.rescue_aid.R;
import com.example.haruka.rescue_aid.listener.DragViewListener2;

/**
 * Created by skriulle on 9/22/2017 AD.
 * This is call when you open phone app to call ambulance
 */

public class CallOverlayOld extends Service {

    private static View view;
    private static WindowManager windowManager;
    private int dpScale ;
    private static TextView textView;
    public static String text = "";
    public String title;
    LayoutInflater layoutInflater;
    WindowManager.LayoutParams params;

    final static String notext = "　　[拡大]　　";
    @Override
    public void onCreate() {
        super.onCreate();
        dpScale = (int)getResources().getDisplayMetrics().density;
        title = "";
    }

    private void makeSmall(){
        windowManager.removeView(view);
        view = layoutInflater.inflate(R.layout.service_layer_small, null);
        windowManager.addView(view, params);
        DragViewListener2 dragViewListener = new DragViewListener2(windowManager, view, params, layoutInflater);
        (view).setOnTouchListener(dragViewListener);
        /*((LinearLayout) view).getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeBig();
            }
        });*/
    }

    private void makeBig(){
        view = layoutInflater.inflate(R.layout.service_layer_old, null);

        // Viewを画面上に追加
        windowManager.addView(view, params);

        View close = ((LinearLayout)((LinearLayout)(((LinearLayout)view).getChildAt(0))).getChildAt(0)).getChildAt(1);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowManager.removeView(view);
                view = null;
            }
        });
        DragViewListener2 dragViewListener = new DragViewListener2(windowManager, view, params, layoutInflater);
        (view).setOnTouchListener(dragViewListener);
        dragViewListener.setText(text);

        textView = (TextView) (((LinearLayout)(((LinearLayout)view).getChildAt(0))).getChildAt(1));
        textView.setText(text);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        layoutInflater = LayoutInflater.from(this);

        windowManager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        params.gravity=  Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        makeBig();
        return super.onStartCommand(intent, flags, startId);
    }



    public static void setText(String text){
        CallOverlayOld.text = text;
        try {
            textView.setText(text);
        } catch (NullPointerException e){
            Log.e("CallOverlay", e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("debug","onDestroy");
        windowManager.removeView(view);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void removeCallOver() {
        try {
            if(DragViewListener2.view != null) {
                windowManager.removeView(DragViewListener2.view);
            }
            view = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}