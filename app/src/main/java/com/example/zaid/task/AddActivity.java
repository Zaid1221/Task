package com.example.zaid.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        setContentView(R.layout.activity_add);
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText nameEditText = ( EditText) findViewById( R.id.input_name );
        EditText dateEditText = (EditText) findViewById( R.id.input_date );
        String name = nameEditText.getText( ).toString( );
        String date = dateEditText.getText( ).toString( );

        // insert new candy in database
        try {
            Task task = new Task( 0, name, date );
            db.insert( task );
            Toast.makeText( this, "Task added", Toast.LENGTH_SHORT ).show( );
        } catch( NumberFormatException nfe ) {
            Toast.makeText( this, "Task error", Toast.LENGTH_LONG ).show( );
        }

        // clear data
        nameEditText.setText( "" );
        dateEditText.setText( "" );
    }

    public void goBack( View v ) {
        this.finish( );
    }
}




