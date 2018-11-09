package com.jupiter.ulric.blindtestfun;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.jupiter.ulric.blindtestfun.swipeView.Player;
import com.jupiter.ulric.blindtestfun.swipeView.PlayersDataAdapter;
import com.jupiter.ulric.blindtestfun.swipeView.SwipeController;
import com.jupiter.ulric.blindtestfun.swipeView.SwipeControllerActions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity {

    //private LinearLayout lin_title;
    //private RelativeLayout layout_disc;
    //private FrameLayout frame_toolbar;
    public static Context context;
    //public SwipeMenuListView listView;
    //private List<ApplicationInfo> mAppList;
    //private AppAdapter mAdapter;
    //private List<String> mItems = new ArrayList<>();
    //public static final int nbUsers = 3;

    private PlayersDataAdapter mAdapter;
    SwipeController swipeController = null;
    private List<Player> mPlayers = new ArrayList<>();
    public static final int nbPlayers = 3;



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

        setPlayersDataAdapter();
        setupRecyclerView();

        /*for (int i = 0; i < nbUsers; i++) {
            mItems.add("user" + i);
        }*/
        //mAppList = getPackageManager().getInstalledApplications(0);
        //listView = (SwipeMenuListView) findViewById(R.id.listView);

        /*mAdapter = new AppAdapter();
        listView.setAdapter(mAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


        // set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        mItems.remove(position);
                        //mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        // Right
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        // Left
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

    }

    private void open(ApplicationInfo item) {
        // open app
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(item.packageName);
        List<ResolveInfo> resolveInfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        if (resolveInfoList != null && resolveInfoList.size() > 0) {
            ResolveInfo resolveInfo = resolveInfoList.get(0);
            String activityPackageName = resolveInfo.activityInfo.packageName;
            String className = resolveInfo.activityInfo.name;

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName componentName = new ComponentName(
                    activityPackageName, className);

            intent.setComponent(componentName);
            startActivity(intent);
        }
    }

    class AppAdapter extends BaseAdapter {
        public static final int TYPE_SWIPE = 0;
        public static final int TYPE_NON_SWIPE = 1;

        @Override
        public int getCount() {
            return mItems.size();
            //return mAppList.size();
        }

        @Override
        public String getItem(int position) {
            return mItems.get(position);
        }

        /*@Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            // menu type count
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            // current menu type
            return position == mItems.size() - 1 ? TYPE_NON_SWIPE : TYPE_SWIPE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();

            final String item = mItems.get(position);
            //ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(getResources().getDrawable(R.drawable.avatar2));
            holder.tv_name.setText(item);
            return convertView;
        }

        class ViewHolder {
            CircleImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (CircleImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }
    }

    public static int dp2px(float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
*/

    }




    private void setPlayersDataAdapter(){
        List<Player> players = new ArrayList<>();
        for(int i=0; i<nbPlayers; i++){
            players.add(new Player("player"+i));
        }

        mAdapter = new PlayersDataAdapter(players,context);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
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
