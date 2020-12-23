package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase_Notes extends SQLiteOpenHelper {
    
    public static final String DB_NAME =  "notes_db";
    public static final int DB_VERSION = 4  ;
    public static final String NOTE_TB_NAME   = "notes" ;
    public static final String NOTE_CLN_NOTE  = "text_note" ;
    public static final String NOTE_CLN_TIME  = "time" ;
    public static final String NOTE_CLN_ID  = "id" ;


    public DataBase_Notes(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {     // it's called when we create dataBase .
     db.execSQL("CREATE TABLE " + NOTE_TB_NAME + " ( " + NOTE_CLN_NOTE  + " TEXT , " + NOTE_CLN_TIME  + " TEXT , " + NOTE_CLN_ID +
             " INTEGER  PRIMARY KEY AUTOINCREMENT )");
            // db.execSQL("CREATE TABLE " + Contact_TB_NAME + "( " + Contact_CLN_NAME + " TEXT , " + Contact_CLN_PHONE
        //        + " TEXT , " + Contact_CLN_Type + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    // when every update .
    db.execSQL("DROP TABLE IF EXISTS notes");
    onCreate(db);
    }
    public boolean insertNote (Todo_details note){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();         // key : the name of CLN .
        contentValues.put(NOTE_CLN_NOTE , note.getNote_name());
        contentValues.put(NOTE_CLN_TIME , note.getTime());
        long result =  db.insert(NOTE_TB_NAME,null , contentValues);
        return result != -1 ;
    }

    public ArrayList<Todo_details> getAllNotes (){
     ArrayList<Todo_details> notes = new ArrayList<>();
     SQLiteDatabase db= getReadableDatabase();
   Cursor cursor = db.rawQuery(" SELECT * FROM " + NOTE_TB_NAME , null);
     if(cursor.moveToFirst()){
         do {
             String note = cursor.getString(cursor.getColumnIndex(NOTE_CLN_NOTE));
             String time = cursor.getString(cursor.getColumnIndex(NOTE_CLN_TIME));
             int id  =  cursor.getInt(cursor.getColumnIndex(NOTE_CLN_ID));
             Todo_details todo = new Todo_details(note,id,time);
             notes.add(todo);
         }while (cursor.moveToNext());
         cursor.close();
         }

     return notes ;
    }
//
    public boolean deleteNote(int id ){
        SQLiteDatabase db = getWritableDatabase();
        String args [] = { String.valueOf(id)} ;
        long result = db.delete(NOTE_TB_NAME , "id=? ", args);
        return  result>0 ; }

    }
/*
public boolean deleteStudent(int id ){
        SQLiteDatabase db = getWritableDatabase();
        String args [] = { id + "" };
        long result = db.delete(NOTE_TB_NAME , "id= id", args);
        return  result>0 ; }

    }

 */
