package com.example.textproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.textproject.db.MyRepository;
import com.example.textproject.listeners.GetTeamsListener;

public class AddEditPlayerViewModel extends AndroidViewModel {
    MyRepository repository;

    public AddEditPlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new MyRepository(application);
    }

    public void getAllTeam(GetTeamsListener listener) {
        repository.getAllTeam(listener);
    }
}