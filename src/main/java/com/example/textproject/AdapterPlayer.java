package com.example.textproject;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.textproject.databinding.ItemPlayerBinding;
import com.example.textproject.listeners.PlayerClickListener;

import java.util.List;

public class AdapterPlayer extends RecyclerView.Adapter<AdapterPlayer.ViewPlayerHolder> {
    private List<Player> players;
    private final PlayerClickListener listener;

    public int getPlayerIndex(long id) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    public void insertPlayer(Player player) {
        players.add(player);
        notifyItemInserted(players.size() - 1);
    }

    public void deletePlayer(long id) {
        int playerIndex = getPlayerIndex(id);
        players.remove(playerIndex);
        notifyItemRemoved(playerIndex);
    }

    public void updatePlayer(Player player) {
        int playerIndex = getPlayerIndex(player.getId());
        players.remove(playerIndex);
        players.add(playerIndex, player);
        notifyItemChanged(playerIndex);
    }

    public AdapterPlayer(List<Player> players, PlayerClickListener listener) {
        this.players = players;
        this.listener = listener;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public PlayerClickListener getListener() {
        return listener;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewPlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPlayerHolder(
                ItemPlayerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPlayerHolder holder, int position) {
        Player player = players.get(position);
        holder.id.setText(String.valueOf(player.getId()));
        holder.teamId.setText(String.valueOf(player.getTeamId()));
        holder.data.setText(String.valueOf(player.getDate()));
        holder.shirtNumber.setText(String.valueOf(player.getShirtNumber()));
        holder.name.setText(player.getName());
        holder.delete_player.setOnClickListener(v -> {
            listener.onDelete(player.getId());
        });
        holder.binding.getRoot().setOnClickListener(v -> {
            listener.onItemClicked(player);
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewPlayerHolder extends RecyclerView.ViewHolder {
        ItemPlayerBinding binding;
        TextView id, name, data, shirtNumber, teamId;
        ImageView delete_player;

        public ViewPlayerHolder(ItemPlayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            id = binding.idPlayer;
            name = binding.playerName;
            data = binding.date;
            shirtNumber = binding.ShirtNumber;
            teamId = binding.teamId;
            delete_player = binding.deletePlayer;
        }

        void bind() {

        }
    }
}
