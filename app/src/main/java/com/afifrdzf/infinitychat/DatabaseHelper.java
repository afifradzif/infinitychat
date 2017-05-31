package com.afifrdzf.infinitychat;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Afifrdzf on 5/29/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "Chat_History";
    public static final String tblName = "chat";
    public static final String COLUMN_MESSAGE = "name";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ID = "id";

    public static final String[] ALL_KEYS = new String[]{COLUMN_ID, COLUMN_MESSAGE, COLUMN_USER, COLUMN_TIME};

    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS chat(id integer primary key,"+ COLUMN_MESSAGE+" TEXT, "
                                + COLUMN_USER+" TEXT, "
                                + COLUMN_TIME+" TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS chat");
        onCreate(db);
    }

    public Cursor getDataById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * from "+tblName+" where "+COLUMN_ID+"= "+id,null);

        return cur;
    }

    public void fnExecuteSql(String strSql, Context appContext){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(strSql);
        } catch (Exception e){
            Log.d("Unable to run query", "error!");
        }
    }

    public int fnTotalRow(){
        int intRow;
        SQLiteDatabase db = this.getReadableDatabase();
        intRow = (int) DatabaseUtils.queryNumEntries(db,tblName);

        return intRow;
    }

    public Cursor getAllRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        String where = null;
        Cursor c = db.query(true, tblName, ALL_KEYS, where, null, null, null, null, null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblName, null, null);
    }
}
