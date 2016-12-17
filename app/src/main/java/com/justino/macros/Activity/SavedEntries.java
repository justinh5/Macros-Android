package com.justino.macros.Activity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.justino.macros.Adapter.EntryAdapter;
import com.justino.macros.Database.DBHandler;
import com.justino.macros.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class SavedEntries extends AppCompatActivity {

    private DBHandler myDBHandler;
    private ListView myListview;
    private EntryAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_entries);

        myDBHandler = DBHandler.getInstance(this);

        //Generate ListView from SQLite Database
        displayListView();


        TextView startText = (TextView) findViewById(R.id.startText);
        if(myAdapter != null)
            startText.setVisibility(View.GONE);

    }


    private void displayListView() {

        Cursor cursor = myDBHandler.retrieveEntries();

        myAdapter = new EntryAdapter(this, cursor, 0);


        myListview = (ListView) findViewById(R.id.entries_list);
        // Assign adapter to ListView
        myListview.setAdapter(myAdapter);


        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(SavedEntries.this, Entry.class));
                /*
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor c = (Cursor) parent.getItemAtPosition(position);

                // Get the name from this row in the database.
                String name = c.getString(c.getColumnIndexOrThrow("name"));
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show(); */
            }
        });
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_saved_entries, menu);
            return true;
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            switch(item.getItemId()) {

                case R.id.action_search:
                    return true;

                case R.id.action_clear_all:
                    Cursor update = myDBHandler.clearAllEntries();
                    myAdapter.swapCursor(update);
                    myAdapter.notifyDataSetChanged();
                    return true;

                case R.id.action_settings:
                    startActivity(new Intent(this, Settings.class));
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }


        // When the add entry button is clicked
        public void addClick(View view) {

            String date = getDate();
            myDBHandler.addEntry(date);
            myAdapter.notifyDataSetChanged();
            startActivity(new Intent(this, Entry.class));
        }


        public String getDate() {
            String date;
            String pattern = "MM/dd/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
            date = format.format(new Date());
            return date;
        }

}
