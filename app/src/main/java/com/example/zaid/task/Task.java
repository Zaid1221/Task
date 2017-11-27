package com.example.zaid.task;

/**
 * Created by Zaid on 11/27/2017.
 */

public class Task
{
    private int id;
    private String name;
    private String date;

    public Task(int newId, String newName, String newDate)
    {
        setId(newId);
        setName(newName);
        setDate(newDate);
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

    public void setDate(String newDate)
    {
        date = newDate;
    }

    public String getDate()
    {
        return date;
    }

    public String toString( ) {
        return id + "; " + name;
    }
}
