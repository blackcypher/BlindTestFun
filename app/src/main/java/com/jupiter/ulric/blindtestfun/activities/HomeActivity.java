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
    private PlayersDataAdapter mAdapterCurrent;
    private PlayersDataAdapter mAdapterEnded;
    private SwipeController swipeController = null;
    private SwipeController swipeController2 = null;
    private int nbPlayers = 5;

    private RecyclerView recyclerViewCurrent;
    private RecyclerView recyclerViewEnded;
                                                            
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
        loginTextview.setTypeface(chops, Typeface.BOLD);

        recyclerViewCurrent = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewEnded = (RecyclerView) findViewById(R.id.recyclerView2);
        mAdapterCurrent = getPlayersDataAdapter(nbPlayers);
        mAdapterEnded = getPlayersDataAdapter(nbPlayers+5);

        setupRecyclerView(recyclerViewCurrent, mAdapterCurrent);
        setupRecyclerView(recyclerViewEnded, mAdapterEnded);
    }

    private PlayersDataAdapter getPlayersDataAdapter(int nbPlayers) {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i<nbPlayers; i++){
            players.add(new Player("player"+i));
        }
        PlayersDataAdapter adapter = new PlayersDataAdapter(players);
        return adapter;
    }

    private void setupRecyclerView(RecyclerView recyclerView, final PlayersDataAdapter mAdapter) {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        final SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.players.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }
}
