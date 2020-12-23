package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv_notes ;
    FloatingActionButton float_btn_add_note ;
    ArrayList<Todo_details> notes ;
    Recycler_Adapter adapter ;
    DataBase_Notes db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_notes = (RecyclerView) findViewById(R.id.rv_notes);
        float_btn_add_note = findViewById(R.id.float_btn_add_note) ;
        notes = new ArrayList<>();
        db = new DataBase_Notes(this);
        notes = db.getAllNotes();
        adapter = new Recycler_Adapter(notes, new OnClickListener() {
            @Override
            public void onClickItem(Todo_details to_do) {
                Intent intent = new Intent(MainActivity.this ,Note_Activity.class);
                intent.putExtra("note" , to_do.getNote_name() );
                intent.putExtra("time" ,to_do.getTime()) ;
                 startActivity(intent);
            }

            @Override
            public void onDeleteItem(int position) {
                Todo_details todo  = notes.get(position);
              boolean res =   db.deleteNote(position);
               notes.remove(position);
               // notes.remove(notes.get(position));

                if(res)  Toast.makeText(MainActivity.this ,"deleted",Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this ,"Error occured",Toast.LENGTH_SHORT).show();
                adapter.notifyItemRemoved(position);
                adapter.notifyDataSetChanged();
            }
        } );
                float_btn_add_note.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Note_Activity.class);
                        intent.putExtra("note" , " ");
                        intent.putExtra("time" , " ");
                        startActivity(intent);
                    }
                });
        rv_notes.setAdapter(adapter);
        rv_notes.setLayoutManager(new LinearLayoutManager(this));
    }
}
