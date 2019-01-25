package com.jupiter.ulric.blindtestfun.activities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.jupiter.ulric.blindtestfun.R;
import com.jupiter.ulric.blindtestfun.model.Player;
import com.jupiter.ulric.blindtestfun.swipe.PlayersDataAdapter;
import com.jupiter.ulric.blindtestfun.swipe.SwipeController;
import com.jupiter.ulric.blindtestfun.swipe.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    public static Context context;
    private PlayersDataAdapter mAdapter;
    private PlayersDataAdapter mAdapter2;
    private SwipeController swipeController = null;
    private SwipeController swipeController2 = null;
    private int nbPlayers = 5;

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Typeface chops = Typeface.createFromAsset(getAssets(), "AgentOrange.ttf");
        TextView loginTextview = (TextView) findViewById(R.id.login);
        //loginTextview.setTypeface(chops, Typeface.BOLD);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView2.setNestedScrollingEnabled(false);
        setPlayersDataAdapter();
        setupRecyclerView();
    }

    private void setPlayersDataAdapter() {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i<nbPlayers; i++){
            players.add(new Player("player"+i));
        }
        mAdapter = new PlayersDataAdapter(players);
        mAdapter2 = new PlayersDataAdapter(players);
    }

    private void setupRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(mAdapter2);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.players.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });

        swipeController2 = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter2.players.remove(position);
                mAdapter2.notifyItemRemoved(position);
                mAdapter2.notifyItemRangeChanged(position, mAdapter2.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        ItemTouchHelper itemTouchhelper2 = new ItemTouchHelper(swipeController2);
        itemTouchhelper.attachToRecyclerView(recyclerView);
        itemTouchhelper2.attachToRecyclerView(recyclerView2);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        recyclerView2.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController2.onDraw(c);
            }
        });
    }
}
