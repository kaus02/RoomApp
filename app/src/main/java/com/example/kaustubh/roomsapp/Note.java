package com.example.kaustubh.roomsapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

import Utils.Constants;

@Entity(tableName = Constants.TABLE_NAME_NOTE)
public class Note implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo(name = "note_content")
    private String content;



    private String title;

    private Date date;

    public Note(String content, String title) {
        this.content = content;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    protected Note(Parcel in) {
        note_id = in.readInt();
        content = in.readString();
        title = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        if(note_id!= note.note_id)
            return false;
        return title!=null ? title.equals(note.title) : note.title==null;
    }

    @Override
    public int hashCode() {
        int result = note_id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Note{" +
                "note_id=" + note_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(note_id);
        dest.writeString(content);
        dest.writeString(title);
    }
}
