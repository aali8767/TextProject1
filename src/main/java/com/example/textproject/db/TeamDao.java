package com.example.textproject.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.textproject.Team;

import java.util.List;

@Dao
@TypeConverters({Converters.class})
public interface TeamDao {
    @Insert
    long insertTeam(Team team);

    @Update
    void updateTeam(Team team);

    @Query("DELETE FROM Team WHERE id =:id")
    void deleteTeam(long id);

    @Query("SELECT * FROM Team")
    List<Team> getAllTeam();
}
