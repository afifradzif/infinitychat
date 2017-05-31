package com.afifrdzf.infinitychat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Afifrdzf on 5/29/2017.
 */

public class History extends AppCompatActivity {

    public static final String strMesej = DatabaseHelper.COLUMN_MESSAGE;
    public static final String strUser = DatabaseHelper.COLUMN_USER;
    public static final String strTime = DatabaseHelper.COLUMN_TIME;

    DatabaseHelper dbHelper;
    ListView listView;
    ArrayList<HashMap<String, String>> alListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setEnabled(false);

        dbHelper = new DatabaseHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.list_of_messages);
        alListView = new ArrayList<HashMap<String, String>>();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                String strSql = "Select * from " + dbHelper.tblName;
                Cursor currData = dbHelper.getReadableDatabase().rawQuery(strSql, null);
                currData.moveToFirst();

                while (currData.moveToNext()) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put(strMesej, currData.getString(currData.getColumnIndex(DatabaseHelper.COLUMN_MESSAGE)));
                    map.put(strUser, currData.getString(currData.getColumnIndex(DatabaseHelper.COLUMN_USER)));
                    map.put(strTime, currData.getString(currData.getColumnIndex(DatabaseHelper.COLUMN_TIME)));

                    alListView.add(map);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter adapter = new SimpleAdapter(History.this, alListView, R.layout.message,
                                new String[]{strMesej, strUser, strTime},
                                new int[]{R.id.message_text, R.id.message_user, R.id.message_time});

                        listView.setAdapter(adapter);
                    }
                });
            }
        };
        Thread thr = new Thread(run);
        thr.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_history) {
            DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
            databaseHelper.deleteAll();
            finish();
        }
        return true;
    }
}
