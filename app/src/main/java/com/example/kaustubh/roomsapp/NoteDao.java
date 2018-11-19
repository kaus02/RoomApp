package com.example.kaustubh.roomsapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import Utils.Constants;

@Dao
public interface NoteDao {

    @Query("Select * from "+ Constants.TABLE_NAME_NOTE)
    List<Note> getAll();

    @Insert
    void insert(Note note);

    @Update
    void update(Note repos);

    @Delete
    void delete(Note note);

    @Delete
    void delete(Note... note);

}
