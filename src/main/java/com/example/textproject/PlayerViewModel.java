package com.example.textproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.textproject.db.MyRepository;
import com.example.textproject.listeners.GetPlayersListener;
import com.example.textproject.listeners.GetRowIdListener;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    MyRepository repository;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public void insertPlayer(Player player, GetRowIdListener listener) {
        repository.insertPlayer(player, listener);
    }

    public void updatePlayer(Player player) {
        repository.updatePlayer(player);
    }

    public void deletePlayer(long id) {
        repository.deletePlayer(id);
    }

    public void getAllPlayer(GetPlayersListener listener) {
        repository.getAllPlayer(listener);
    }
}
