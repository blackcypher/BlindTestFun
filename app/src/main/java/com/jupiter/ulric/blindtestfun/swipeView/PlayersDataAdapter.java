package com.jupiter.ulric.blindtestfun.swipeView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jupiter.ulric.blindtestfun.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayersDataAdapter extends RecyclerView.Adapter<PlayersDataAdapter.PlayerViewHolder> {
    public List<Player> players;
    private Context mContext;

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_icon;
        TextView tv_name;

        public PlayerViewHolder(View view) {
            super(view);
            iv_icon = (CircleImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }

    public PlayersDataAdapter(List<Player> players, Context context) {
        this.players = players;
        this.mContext = context;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_app, parent, false);

        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.tv_name.setText(player.getName());
        holder.iv_icon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.avatar2));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}