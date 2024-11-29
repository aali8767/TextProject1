package com.example.textproject;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.textproject.db.Converters;

import java.io.Serializable;
import java.util.Date;

@Entity
@TypeConverters({Converters.class})
public class Team implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private Date date;

    public Team() {

    }

    public Team(long id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    @Ignore
    public Team(String name, Date date) {
        this.name = name;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
