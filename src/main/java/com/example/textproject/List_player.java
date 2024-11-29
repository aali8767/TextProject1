package com.example.textproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.textproject.databinding.ActivityListPlayerBinding;
import com.example.textproject.listeners.PlayerClickListener;

import java.util.ArrayList;
import java.util.Objects;


public class List_player extends AppCompatActivity implements PlayerClickListener {
    ActivityListPlayerBinding binding;
    PlayerViewModel viewModel;
    AdapterPlayer adapterPlayer;
    private ActivityResultLauncher<Intent> addPlayerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(PlayerViewModel.class);

        adapterPlayer = new AdapterPlayer(new ArrayList<>(), this);
        binding.recyclerViewPlayers.setHasFixedSize(true);

        binding.recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewPlayers.setAdapter(adapterPlayer);
        viewModel.getAllPlayer(players -> runOnUiThread(() -> adapterPlayer.setPlayers(players)));

        addPlayerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Player player = (Player) Objects.requireNonNull(result.getData().getSerializableExtra(AddEditePlayer.PLAYER_KEY));
                        switch (Objects.requireNonNull(result.getData().getStringExtra(AddEditePlayer.RESULT_KEY))) {
                            case AddEditePlayer.SAVE_KEY: {
                                viewModel.insertPlayer(player, id -> {
                                    player.setId(id);
                                    runOnUiThread(() -> adapterPlayer.insertPlayer(player));
                                });
                                break;
                            }

                            case AddEditePlayer.EDIT_KEY: {
                                long teamId = result.getData().getLongExtra(AddEditePlayer.ID_KEY, 0);
                                player.setId(teamId);

                                viewModel.updatePlayer(player);
                                adapterPlayer.updatePlayer(player);
                                break;
                            }
                        }
                    }
                }
        );

        binding.fabAddPlayer.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), AddEditePlayer.class);
            addPlayerLauncher.launch(intent);
        });
    }

    @Override
    public void onDelete(long id) {
        adapterPlayer.deletePlayer(id);
        viewModel.deletePlayer(id);
    }

    @Override
    public void onItemClicked(Player player) {
        Intent intent = new Intent(getBaseContext(), AddEditePlayer.class);
        intent.putExtra(AddEditePlayer.PLAYER_KEY, player);
        addPlayerLauncher.launch(intent);
    }
}