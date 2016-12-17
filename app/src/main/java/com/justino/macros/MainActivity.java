package com.justino.macros;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.justino.macros.Activity.SavedEntries;
import com.justino.macros.Database.DBHandler;

public class MainActivity extends AppCompatActivity {

    private DBHandler myDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) throws SQLException{
        super.onCreate(savedInstanceState);

        // open a connection to the database
        myDBHandler = DBHandler.getInstance(this);
        myDBHandler.open();

        // Start the saved entries activity
        startActivity(new Intent(this, SavedEntries.class));
    }
}



