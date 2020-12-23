package com.example.notes;

public class Todo_details {
    private  String note_name ;
    private  String time ;
    private int id ;

    public Todo_details(String note_name , String time) {
        this.note_name = note_name;
        this.time = time ;
    }

    public Todo_details(String note_name, int id, String time) {
        this.note_name = note_name;
        this.id = id;
        this.time = time;
    }

    public String getNote_name() {
        return note_name;
    }

    public void setNote_name(String note_name) {
        this.note_name = note_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


}
