package com.example.textproject.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.textproject.Player;
import com.example.textproject.Team;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Team.class, Player.class}, version = 1,exportSchema = false)
public abstract class DataBass extends RoomDatabase {

    public abstract PlayerDao playerDao();

    public abstract TeamDao teamDao();

    private static volatile DataBass INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DataBass getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DataBass.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DataBass.class, "players_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}