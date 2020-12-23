package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.viewHolderNote> {

ArrayList<Todo_details> Notes ;
    OnClickListener listener ;

    public Recycler_Adapter(ArrayList<Todo_details> notes , OnClickListener listener) {
       this.Notes = notes;
        this.listener = listener;
    }
    @NonNull
    @Override
    public viewHolderNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_notes,null,false);
        return new viewHolderNote(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderNote holder, int position) {
        Todo_details note = Notes.get(position);
    holder.tv_note.setText(note.getNote_name());
    holder.tv_time.setText(note.getTime());
    holder.img_delete.setImageResource(R.drawable.ic_delete_sweep_black_24dp);
  //  holder.getAdapterPosition()
    }
    @Override
    public int getItemCount() {
        return  Notes.size();
    }

    public class viewHolderNote extends RecyclerView.ViewHolder {
        TextView tv_note ;
        TextView tv_time ;
        ImageView img_delete ;
        public viewHolderNote(@NonNull View itemView) {
            super(itemView);
            tv_note = itemView.findViewById(R.id.tv_note_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            img_delete = itemView.findViewById(R.id.img_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(new Todo_details(tv_note.getText().toString(),tv_time.getText().toString()));
                }
            });
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteItem(getAdapterPosition());

                }
            });
        }}}
