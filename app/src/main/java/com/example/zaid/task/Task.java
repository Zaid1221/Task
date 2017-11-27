package com.example.zaid.task;

/**
 * Created by Zaid on 11/27/2017.
 */

public class Task
{
    private int id;
    private String name;

    public Task(int newID, String newName)
    {
        setId(newId);
        setName(newName);
    }

    public void setId( int newId ) {
        id = newId;
    }

    public void setName( String newName ) {
        name = newName;
    }

    public int getId( ) {
        return id;
    }

    public String getName( ) {
        return name;
    }

    public String toString( ) {
        return id + "; " + name;
    }
}
