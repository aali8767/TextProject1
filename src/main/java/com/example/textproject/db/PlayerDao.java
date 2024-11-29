package com.example.textproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.textproject.Player;

import java.util.List;

@Dao
@TypeConverters({Converters.class})
public interface PlayerDao {

    @Insert
    long insertPlayer(Player player);

    @Update
    void updatePlayer(Player player);

    @Query("DELETE FROM Player WHERE id =:id")
    void deletePlayer(long id);

    @Query("SELECT * FROM Player")
    List<Player> getAllPlayer();
}
