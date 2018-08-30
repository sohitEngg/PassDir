package com.example.root.myapplication.events;

import com.example.root.myapplication.controllers.Event;

public class StartActivityEvent extends Event {
    public final String activityName;

    public StartActivityEvent(String activityName)
    {
        this.activityName = activityName;
    }
}
