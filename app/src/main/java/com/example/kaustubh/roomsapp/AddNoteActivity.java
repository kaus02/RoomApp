package com.example.kaustubh.roomsapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.example.kaustubh.roomsapp.databinding.ActivityAddNoteBinding;

import java.lang.ref.WeakReference;

public class AddNoteActivity extends AppCompatActivity {



    ActivityAddNoteBinding binding;
    Context mContext;
    private Note note;
    private NoteDatabase noteDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        mContext = AddNoteActivity.this;

        final NoteDatabase noteDatabase = NoteDatabase.getInstance(mContext);

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                note = new Note(binding.noteText.getText().toString(),binding.noteTitle.getText().toString());

                new InsertTask(AddNoteActivity.this,note).execute();
            }
        });
    }

    private void setResult(Note note, int flag){
        setResult(flag,new Intent().putExtra("note",note));
        finish();
    }

    private class InsertTask extends AsyncTask<Void, Void ,Boolean> {

        private WeakReference<AddNoteActivity> addNoteActivityWeakReference;
        private Note note;

        public InsertTask(AddNoteActivity context,Note note) {
            this.addNoteActivityWeakReference = new WeakReference<>(context);
            this.note = note;
        }


        @Override
        protected Boolean doInBackground(Void... voids) {
            addNoteActivityWeakReference.get().noteDatabase.getNoteDao().insert(note);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean bool) {
            if(bool){
                addNoteActivityWeakReference.get().setResult(note,1);
                addNoteActivityWeakReference.get().finish();
            }
        }
    }
}
