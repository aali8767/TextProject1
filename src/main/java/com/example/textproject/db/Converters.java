package com.example.textproject.db;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Long toLong(Date date) {
        return date.getTime();

    }

    @TypeConverter
    public static Date toDate(Long date) {
        return new Date(date);
    }

}
