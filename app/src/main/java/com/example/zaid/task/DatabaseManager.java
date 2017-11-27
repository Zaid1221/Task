package com.example.zaid.task;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Zaid on 11/27/2017.
 */

public class DatabaseManager
{
    public class DatabaseManager extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "taskDB";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_TASK = "task";
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String DATE = "date";

        public DatabaseManager(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            // build sql create statement
            String sqlCreate = "create table " + TABLE_TASK + "( " + ID;
            sqlCreate += " integer primary key autoincrement, " + NAME;
            sqlCreate += " text, " + DATE + " text )";

            db.execSQL(sqlCreate);
        }

        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            // Drop old table if it exists
            db.execSQL("drop table if exists " + TABLE_TASK);
            // Re-create tables
            onCreate(db);
        }

        public void insert(Task task) {
            SQLiteDatabase db = this.getWritableDatabase();
            String sqlInsert = "insert into " + TABLE_TASK;
            sqlInsert += " values( null, '" + task.getName();
            sqlInsert += "', '" + task.getDate() + "' )";

            db.execSQL(sqlInsert);
            db.close();
        }


        public void deleteById( int id ) {
            SQLiteDatabase db = this.getWritableDatabase( );
            String sqlDelete = "delete from " + TABLE_TASK;
            sqlDelete += " where " + ID + " = " + id;

            db.execSQL( sqlDelete );
            db.close( );
        }


        public void updateById( int id, String name, String date ) {
            SQLiteDatabase db = this.getWritableDatabase();

            String sqlUpdate = "update " + TABLE_TASK;
            sqlUpdate += " set " + NAME + " = '" + name + "', ";
            sqlUpdate += DATE + " = '" + date + "'";
            sqlUpdate += " where " + ID + " = " + id;

            db.execSQL( sqlUpdate );
            db.close( );
        }


        public ArrayList<Task> selectAll( ) {
            String sqlQuery = "select * from " + TABLE_TASK;

            SQLiteDatabase db = this.getWritableDatabase( );
            Cursor cursor = db.rawQuery( sqlQuery, null );

            ArrayList<Task> tasks = new ArrayList<Task>( );
            while( cursor.moveToNext( ) ) {
                Task currentTask = new Task( Integer.parseInt( cursor.getString( 0 ) ),
                        cursor.getString( 1 ), cursor.getString( 2 ) );
                tasks.add( currentTask );
            }
            db.close( );
            return tasks;
        }

    }
}
