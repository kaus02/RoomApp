package com.example.kaustubh.roomsapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;


import com.example.kaustubh.roomsapp.databinding.NoteHeaderBinding;

import java.util.List;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    Context ctx;
    private List<Note> list;
    private LayoutInflater layoutInflater;
    private OnIemClick onIemClick;

    public interface OnIemClick{
        void getPositionView(int i);
    }

    public NotesRecyclerAdapter(Context ctx, OnIemClick onIemClick, List<Note> notes) {
        this.onIemClick = onIemClick;
        this.ctx = ctx;
        list =  notes;
    }

    @NonNull
    @Override
    public NotesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(layoutInflater==null){
            layoutInflater = LayoutInflater.from(ctx);
        }
        NoteHeaderBinding noteHeaderBinding = DataBindingUtil.inflate(layoutInflater,R.layout.note_header,viewGroup,false);
        return new ViewHolder(noteHeaderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.binding.text.setText(list.get(i).getContent());
        viewHolder.binding.title.setText(list.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemClickListener {
        private final NoteHeaderBinding binding;
        public ViewHolder(NoteHeaderBinding noteHeaderBinding) {
            super(noteHeaderBinding.getRoot());
            binding = noteHeaderBinding;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onIemClick.getPositionView(getAdapterPosition());
        }
    }
}
