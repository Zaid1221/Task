package com.example.zaid.task;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Zaid Javaid on 11/28/2017.
 */

public class TaskButton extends Button {
    private Task task;

    public TaskButton(Context context, Task newTask ) {
        super( context );
        task = newTask;
    }
}
