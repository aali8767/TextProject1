package com.example.textproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.textproject.databinding.ActivityListTeamBinding;
import com.example.textproject.listeners.TeamClickListener;

import java.util.ArrayList;
import java.util.Objects;

public class List_team extends AppCompatActivity implements TeamClickListener {
    ActivityListTeamBinding binding;
    TeamViewModel viewModel;
    AdapterTeam adapterTeam;
    private ActivityResultLauncher<Intent> addTeamLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(TeamViewModel.class);

        adapterTeam = new AdapterTeam(new ArrayList<>(), this);
        binding.recyclerViewTeams.setHasFixedSize(true);
        binding.recyclerViewTeams.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewTeams.setAdapter(adapterTeam);

        viewModel.getAllTeam(teams -> runOnUiThread(() -> adapterTeam.setTeams(teams)));

        addTeamLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Team team = (Team) Objects.requireNonNull(result.getData().getSerializableExtra(AddEditeTeam.TEAM_KEY));
                        System.out.println(team.getName());
                        switch (Objects.requireNonNull(result.getData().getStringExtra(AddEditeTeam.RESULT_KEY))) {
                            case AddEditeTeam.SAVE_KEY: {
                                viewModel.insertTeam(team, id -> {
                                    team.setId(id);
                                    runOnUiThread(() -> adapterTeam.insertTeam(team));
                                });
                                break;
                            }

                            case AddEditeTeam.EDIT_KEY: {
                                long teamId = result.getData().getLongExtra(AddEditeTeam.ID_KEY, 0);
                                team.setId(teamId);

                                viewModel.updateTeam(team);
                                adapterTeam.updateTeam(team);
                                break;
                            }
                        }
                    }
                });

        binding.fabAddTeam.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), AddEditeTeam.class);
            addTeamLauncher.launch(intent);
        });
    }

    @Override
    public void onDelete(long id) {
        adapterTeam.deleteTeam(id);
        viewModel.deleteTeam(id);
    }

    @Override
    public void onItemClicked(Team team) {
        Intent intent = new Intent(getBaseContext(), AddEditeTeam.class);
        intent.putExtra(AddEditeTeam.TEAM_KEY, team);
        addTeamLauncher.launch(intent);
    }
}