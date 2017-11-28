package com.example.zaid.task;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager db; //instance of the DBM class
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseManager( this );
        scrollView = ( ScrollView ) findViewById( R.id.scrollView );
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        buttonWidth = size.x / 2;
        updateView( );
    }

    protected void onResume( ) //this is called when the user comes back
    {                          //from another activity
        super.onResume();
        updateView();
    }

    public void updateView( ) //adds all the candy to the main screen
    {
        ArrayList<Task> tasks = db.selectAll( ); //creates array stores all candy from DB in it
        if( tasks.size( ) > 0 )
        {
            //scrollView.removeAllViewsInLayout( ); //rebuilds the scrollview

            // set up the grid layout
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( ( tasks.size( ) + 1 ) / 2 );
            grid.setColumnCount( 2 );

            // create array of buttons, 2 per row
            TaskButton [] buttons = new TaskButton[tasks.size( )];

            // fill the grid
            int i = 0;
            for ( Task task : tasks ) {
                // create the button
                buttons[i] = new TaskButton( this, task );
                buttons[i].setText( task.getName( )
                        + "\n" + task.getDate( ) );


                // add the button to grid
                grid.addView( buttons[i], buttonWidth,
                        GridLayout.LayoutParams.WRAP_CONTENT );
                i++;
            }
            scrollView.addView( grid );
        }
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent insertIntent = new Intent(this, AddActivity.class);
            this.startActivity(insertIntent);
            return true;
        }
        else if(id == R.id.action_delete) {
            Intent removeIntent = new Intent(this, RemoveActivity.class);
            this.startActivity(removeIntent);
            return true;
        } else if (id == R.id.action_update) {
            Intent updateIntent = new Intent(this, UpdateActivity.class);
            this.startActivity(updateIntent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
