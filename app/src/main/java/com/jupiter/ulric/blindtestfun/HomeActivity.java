package com.jupiter.ulric.blindtestfun;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jupiter.ulric.blindtestfun.backgrounds.GraphicsView;
import com.jupiter.ulric.blindtestfun.backgrounds.rotatingBackground.RotatingImageView;
import com.jupiter.ulric.blindtestfun.backgrounds.starAnimationView.StarAnimationView;
import com.jupiter.ulric.blindtestfun.utils.Constants;

public class HomeActivity extends AppCompatActivity {

    //private LinearLayout lin_title;
    private LinearLayout layoutStar;
    private RelativeLayout layout_disc;
    private FrameLayout frame_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        /*lin_title = (LinearLayout)findViewById(R.id.lin_title);
        lin_title.addView(new GraphicsView(this));*/

        layoutStar = (LinearLayout) findViewById(R.id.lin_star);
        layoutStar.addView(new StarAnimationView(this));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Typeface chops = Typeface.createFromAsset(getAssets(), "AgentOrange.ttf");
        TextView loginTextview = (TextView) findViewById(R.id.login);
        loginTextview.setTypeface(chops, Typeface.BOLD);

        layout_disc = (RelativeLayout) findViewById(R.id.layout_disc);
        RotatingImageView disc_view = new RotatingImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        frame_toolbar = (FrameLayout) findViewById(R.id.frame_toolbar);
        ImageView statistics = (ImageView) findViewById(R.id.statistics);
        params.setMargins(0, Constants.DISC_MARGIN_TOP, 0, 0);
        disc_view.setLayoutParams(params);
        layout_disc.addView(disc_view);
    }
}
