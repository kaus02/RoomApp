package com.example.kaustubh.roomsapp;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kaustubh.roomsapp.NotesRecyclerAdapter.OnIemClick;
import com.example.kaustubh.roomsapp.databinding.ActivityMainBinding;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnIemClick{

    ActivityMainBinding binding;
    private LinearLayoutManager notesLayoutManager;
    private NoteDatabase noteDatabase;
    private NotesRecyclerAdapter notesAdapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivityForResult(new Intent(MainActivity.this,AddNoteActivity.class),100);
            }
        });
    }

    @Override
    protected void onResume() {
        initialiseDislplay();

        displayList();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100 && resultCode>0){
            if(resultCode==1){

            }
        }
    }

    private void displayList() {
        noteDatabase = NoteDatabase.getInstance(MainActivity.this);
        notesAdapter = new NotesRecyclerAdapter(MainActivity.this,this,notes);

    }

    private void initialiseDislplay() {
        notesLayoutManager = new LinearLayoutManager(this);
        binding.content.notesRecyclerView.setLayoutManager(notesLayoutManager);
        new RetrieverTask(this).execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getPositionView(int i) {

    }

    private class RetrieverTask extends AsyncTask<Void,Void,List<Note>> {

        WeakReference<MainActivity> mainActivityWeakReference;

        public RetrieverTask(MainActivity ctx) {
            mainActivityWeakReference = new WeakReference<MainActivity>(ctx);
        }


        @Override
        protected List<Note> doInBackground(Void... voids) {

            if(mainActivityWeakReference!=null){
                return mainActivityWeakReference.get().noteDatabase.getNoteDao().getAll();
            }else{
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if(notes!=null && notes.size()>0){
                mainActivityWeakReference.get().notes = notes;

            }
        }
    }


}
