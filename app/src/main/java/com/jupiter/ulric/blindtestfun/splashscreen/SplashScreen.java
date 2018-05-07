package com.jupiter.ulric.blindtestfun.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jupiter.ulric.blindtestfun.MainActivity;

/**
 * Created by ulric on 18/03/2018.
 */

public class SplashScreen extends Activity{

    private static int SPLASH_TIME_OUT = 3000;
    private GameView gv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        gv = new GameView(this);
        setContentView(gv);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
