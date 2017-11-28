package com.example.zaid.task;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        updateView();
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {
        ArrayList<Task> tasks = db.selectAll( );
        if( tasks.size( ) > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( tasks.size( ) );
            grid.setColumnCount( 4 );

            // create arrays of components
            TextView[] ids = new TextView[tasks.size( )];
            EditText[][] namesAnddate = new EditText[tasks.size( )][2];
            Button[] buttons = new Button[tasks.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            int i = 0;

            for ( Task task : tasks ) {
                // create the TextView for the candy's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + task.getId( ) );

                // create the two EditTexts for the candy's name and price
                namesAnddate[i][0] = new EditText( this );
                namesAnddate[i][1] = new EditText( this );
                namesAnddate[i][0].setText( task.getName( ) );
                namesAnddate[i][1].setText( "" + task.getDate( ) );
                namesAnddate[i][1]
                        .setInputType( InputType.TYPE_CLASS_NUMBER );
                namesAnddate[i][0].setId( 10 * task.getId( ) );
                namesAnddate[i][1].setId( 10 * task.getId( ) + 1 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( task.getId( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAnddate[i][0], ( int ) ( width * .4 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAnddate[i][1], ( int ) ( width * .15 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .35 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // retrieve name and price of the candy
            int taskId = v.getId();
            EditText nameET = (EditText) findViewById(10 * taskId);
            EditText dateET = (EditText) findViewById(10 * taskId + 1);
            String name = nameET.getText().toString();
            String date = dateET.getText().toString();

            // update candy in database
            try {
                db.updateById(taskId, name, date);
                Toast.makeText(UpdateActivity.this, "Task updated",
                        Toast.LENGTH_SHORT).show();

                // update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this,
                        "Task error", Toast.LENGTH_LONG).show();
            }
            UpdateActivity.this.finish();
        }
    }
}