package com.jupiter.ulric.blindtestfun.swipe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jupiter.ulric.blindtestfun.model.Player;
import com.jupiter.ulric.blindtestfun.R;

import java.util.List;

public class PlayersDataAdapter extends RecyclerView.Adapter<PlayersDataAdapter.PlayerViewHolder>{
    public List<Player> players;

    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        private TextView id;

        public PlayerViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.tv_name);
            //email = (TextView) view.findViewById(R.id.nationality);
        }
    }

    public PlayersDataAdapter(List<Player> players) {
        this.players = players;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        Player player = players.get(position);
        holder.id.setText(player.getId());
        //holder.email.setText(player.getEmail());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
