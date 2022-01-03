package com.semer.projet_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBConnections extends SQLiteOpenHelper {
    public static final String DBName = "EVM";
    public static final int VERSION = 1;

    public DBConnections(Context context) {
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE="CREATE TABLE IF NOT EXISTS EVENTS ( ID INTEGER PRIMARY KEY, NOM_EVENT TEXT, LOCAL_EVENT TEXT,DATE_EVENT );";
        db.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS EVENTS");
        onCreate(db);
    }

    public void InsertRowEvent(Event e){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NOM_EVENT",e.getNom_event());
        contentValues.put("LOCAL_EVENT",e.getLocal_event());
        contentValues.put("DATE_EVENT",e.getDate_event());

        db.insert("EVENTS",null, contentValues);
    }

    public List getAllEvents(){
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM EVENTS",null);
        res.moveToFirst();
        while(res.isAfterLast()==false){
            Event e = new Event(res.getInt(0),res.getString(1),res.getString(2),res.getString(3));
            events.add(e);
            res.moveToNext();
        }
        return events;
    }
     long updateEvent(Event event){
         SQLiteDatabase db = this.getWritableDatabase();
          ContentValues values = new ContentValues();
          values.put("NOM_EVENT",event.getNom_event());
         values.put("LOCAL_EVENT",event.getLocal_event());
         values.put("DATE_EVENT",event.getDate_event());
         long retour = db.update("EVENT",values,"ID_EVENT =?", new String[]{String.valueOf(event.getId())});
         return retour;
     }
     void deleteEventById(Event event){
         SQLiteDatabase db = this.getWritableDatabase();
         long retour = db.delete("EVENTS","ID_EVENT=?",new String[]{String.valueOf(event.getId())});

     }
}
