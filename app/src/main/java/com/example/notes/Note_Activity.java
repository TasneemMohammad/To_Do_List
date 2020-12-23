package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Note_Activity extends AppCompatActivity {
    Button btn_save ;
    EditText et_writing_note , et_time ;

   // ImageView img_delete ;
    DataBase_Notes db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        db = new DataBase_Notes(this);
        et_writing_note = findViewById(R.id.et_writing_note);
        et_time = findViewById(R.id.et_time);
        btn_save = findViewById(R.id.btn_save);

        Intent data_notes = getIntent();
        String note = data_notes.getExtras().getString("note");
        String time = data_notes.getExtras().getString("time");
        et_writing_note.setText(note);
        et_time.setText(time);



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = et_writing_note.getText().toString();
                String time = et_time.getText().toString();
               Todo_details todo = new Todo_details(note , time);
                boolean res =  db.insertNote(todo);
                if(res) Toast.makeText(Note_Activity.this ,"saved",Toast.LENGTH_SHORT).show();
                else Toast.makeText(Note_Activity.this ,"Error occured",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Note_Activity.this , MainActivity.class);
                startActivity(intent);
            }
        }); }
}
