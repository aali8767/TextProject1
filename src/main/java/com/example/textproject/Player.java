package com.example.textproject;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.textproject.db.Converters;

import java.io.Serializable;
import java.util.Date;

@Entity(foreignKeys = @ForeignKey(entity = Team.class, parentColumns = {"id"}, childColumns = {"teamId"}))
@TypeConverters({Converters.class})
public class Player implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private long shirtNumber;
    private Date date;
    private long teamId;

    public Player() {

    }

    public Player(long id, String name, long shirtNumber, Date date, long teamId) {
        this.id = id;
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.date = date;
        this.teamId = teamId;
    }

    @Ignore
    public Player(String name, long shirtNumber, Date date, long teamId) {
        this.name = name;
        this.shirtNumber = shirtNumber;
        this.date = date;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(long shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
