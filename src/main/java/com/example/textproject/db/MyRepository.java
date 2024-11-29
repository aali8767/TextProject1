package com.example.textproject.db;

import android.app.Application;

import androidx.room.Delete;

import com.example.textproject.Player;
import com.example.textproject.Team;
import com.example.textproject.listeners.GetPlayersListener;
import com.example.textproject.listeners.GetRowIdListener;
import com.example.textproject.listeners.GetTeamsListener;

public class MyRepository {
    TeamDao teamDao;
    PlayerDao playerDao;

    public MyRepository(Application application) {
        DataBass dataBass = DataBass.getDatabase(application);
        teamDao = dataBass.teamDao();
        playerDao = dataBass.playerDao();
    }

    public void insertTeam(Team team, GetRowIdListener listener) {
        DataBass.databaseWriteExecutor.execute(() -> {
            listener.onGetTeamId(teamDao.insertTeam(team));
        });
    }

    public void updateTeam(Team team) {
        DataBass.databaseWriteExecutor.execute(() -> {
            teamDao.updateTeam(team);
        });
    }

    @Delete
    public void deleteTeam(long id) {
        DataBass.databaseWriteExecutor.execute(() -> {
            teamDao.deleteTeam(id);
        });
    }

    public void getAllTeam(GetTeamsListener listener) {
        DataBass.databaseWriteExecutor.execute(() -> {
            listener.onGetTeams(teamDao.getAllTeam());
        });
    }

    public void insertPlayer(Player player, GetRowIdListener listener) {
        DataBass.databaseWriteExecutor.execute(() -> {
            listener.onGetTeamId(playerDao.insertPlayer(player));
        });
    }

    public void updatePlayer(Player player) {
        DataBass.databaseWriteExecutor.execute(() -> {
            playerDao.updatePlayer(player);
        });
    }

    public void deletePlayer(long id) {
        DataBass.databaseWriteExecutor.execute(() -> {
            playerDao.deletePlayer(id);
        });
    }

    public void getAllPlayer(GetPlayersListener listener) {
        DataBass.databaseWriteExecutor.execute(() -> {
            listener.onGetPlayers(playerDao.getAllPlayer());
        });
    }
}
