package com.jupiter.ulric.blindtestfun;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.jupiter.ulric.blindtestfun.backgrounds.GraphicsView;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout lin_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        lin_title = (LinearLayout)findViewById(R.id.lin_title);
        lin_title.addView(new GraphicsView(this));

    }
}
