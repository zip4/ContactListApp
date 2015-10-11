package com.example.pashulya.contactlistapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper DBH;
    SQLiteDatabase db;
    ListView lv;
    Cursor cursor;
    ContentValues cv;
    Intent intent;

    public void fillDB() {
        DBH = new DatabaseHelper(this);
        db = DBH.getWritableDatabase();

        cursor = db.query("Contacts", null, null, null, null, null, null);

        if (cursor.getCount() == 0) {
            cv = new ContentValues();
            cv.put("firstname", "Anna");
            cv.put("lastname", "Yarovaya");
            cv.put("number", "+380934364912");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Victoria");
            cv.put("lastname", "Kovalenko");
            cv.put("number", "+380955688962");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Sergei");
            cv.put("lastname", "Klopenko");
            cv.put("number", "+380932738704");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Dima");
            cv.put("lastname", "Radchenko");
            cv.put("number", "+380672985923");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Ivan");
            cv.put("lastname", "Antonenko");
            cv.put("number", "+380634756183");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Alena");
            cv.put("lastname", "Sviatnaya");
            cv.put("number", "+380503761042");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Evgenii");
            cv.put("lastname", "Kozak");
            cv.put("number", "+380636544855");
            db.insert("Contacts", null, cv);

            cv = new ContentValues();
            cv.put("firstname", "Roman");
            cv.put("lastname", "Kliuchko");
            cv.put("number", "+380645568456");
            db.insert("Contacts", null, cv);
        }

        if (cursor != null) {
            cursor.close();
        }

        if (DBH != null) {
            DBH.close();
        }
    }

    public void printListView() {
        TextView tvId = (TextView) findViewById(R.id.tvId);
        TextView tvFirstname = (TextView) findViewById(R.id.tvFirstname);
        TextView tvLastname = (TextView) findViewById(R.id.tvLastname);
        TextView tvNumber = (TextView) findViewById(R.id.tvNumber);

        DBH = new DatabaseHelper(this);
        db = DBH.getWritableDatabase();
        lv = (ListView) findViewById(R.id.listView);

        if (cursor != null) {
            cursor.close();
        }

        //String query = "Contacts c";
        //String[] columns = new String[]{"c.Firstname as firstname", "c.Lastname as lastname", "c.Number as number"};

        cursor = db.query("Contacts", null, null, null, null, null, null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item,
                cursor,
                new String[]{"_id", "Firstname", "Lastname", "Number"},
                new int[]{R.id.tvId, R.id.tvFirstname, R.id.tvLastname, R.id.tvNumber}
        );

        lv.setAdapter(adapter);

        if (DBH != null) {
            //DBH.close();
        }

        if (cursor != null) {
            //cursor.close();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fillDB();
        printListView();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvNum = (TextView) view.findViewById(R.id.tvNumber);
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tvNum.getText()));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
