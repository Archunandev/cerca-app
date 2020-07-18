package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int timeout=2000;
    TextView splashtxt;
    ImageView splashimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashtxt=findViewById(R.id.splashtxt);
        splashimg=findViewById(R.id.splashimg);

        Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.splashanim);
        splashimg.startAnimation(animation);
        splashtxt.startAnimation(animation);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this,GridView.class);
                startActivity(intent);
                finish();

            }
        },timeout);

    }
}
