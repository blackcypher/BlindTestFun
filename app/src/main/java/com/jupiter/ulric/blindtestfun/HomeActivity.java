package com.jupiter.ulric.blindtestfun;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jupiter.ulric.blindtestfun.backgrounds.rotatingBackground.RotatingImageView;
import com.jupiter.ulric.blindtestfun.backgrounds.starAnimationView.StarAnimationView;
import com.jupiter.ulric.blindtestfun.utils.Constants;

public class HomeActivity extends AppCompatActivity {

    //private LinearLayout lin_title;
    //private RelativeLayout layout_disc;
    //private FrameLayout frame_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Typeface chops = Typeface.createFromAsset(getAssets(), "AgentOrange.ttf");
        TextView loginTextview = (TextView) findViewById(R.id.login);
        loginTextview.setTypeface(chops, Typeface.BOLD);

    }
}
