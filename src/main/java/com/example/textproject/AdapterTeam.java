package com.example.textproject;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.textproject.databinding.ItemTeamBinding;
import com.example.textproject.listeners.TeamClickListener;

import java.util.List;

public class AdapterTeam extends RecyclerView.Adapter<AdapterTeam.VeiwTeamHolder> {
    private List<Team> teams;
    private final TeamClickListener listener;

    public int getTeamIndex(long id) {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getId() == id) {
                return i;

            }
        }
        return 0;
    }

    public void insertTeam(Team team) {
        teams.add(team);
        notifyItemInserted(teams.size() - 1);
    }

    public void deleteTeam(long id) {
        int teamIndex = getTeamIndex(id);
        teams.remove(teamIndex);
        notifyItemRemoved(teamIndex);
    }

    public void updateTeam(Team team) {
        int teamIndex = getTeamIndex(team.getId());
        teams.remove(teamIndex);
        teams.add(teamIndex, team);
        notifyItemChanged(teamIndex);
    }

    public AdapterTeam(List<Team> teams, TeamClickListener listener) {
        this.teams = teams;
        this.listener = listener;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VeiwTeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTeamBinding teamBinding = ItemTeamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VeiwTeamHolder(teamBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VeiwTeamHolder holder, int position) {
        Team team = teams.get(position);
        holder.data.setText(String.valueOf(team.getDate()));
        holder.name.setText(team.getName());
        holder.id.setText(String.valueOf(team.getId()));
        holder.delete_team.setOnClickListener(v -> listener.onDelete(team.getId()));
        holder.binding.getRoot().setOnClickListener(v -> {
            listener.onItemClicked(team);
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class VeiwTeamHolder extends RecyclerView.ViewHolder {
        TextView id, name, data;
        ImageView delete_team;
        private ItemTeamBinding binding;

        public VeiwTeamHolder(ItemTeamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            id = binding.idTeam;
            name = binding.nameTeam;
            data = binding.DateTeam;
            delete_team = binding.deleteTeam;
        }
    }
}
