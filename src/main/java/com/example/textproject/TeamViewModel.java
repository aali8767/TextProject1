package com.example.textproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.textproject.db.MyRepository;
import com.example.textproject.listeners.GetRowIdListener;
import com.example.textproject.listeners.GetTeamsListener;

public class TeamViewModel extends AndroidViewModel {
    MyRepository repository;

    public TeamViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public void insertTeam(Team team, GetRowIdListener listener) {
        repository.insertTeam(team, listener);
    }

    public void updateTeam(Team team) {
        repository.updateTeam(team);

    }

    public void deleteTeam(long id) {
        repository.deleteTeam(id);

    }

    public void getAllTeam(GetTeamsListener listener) {
        repository.getAllTeam(listener);
    }
}
