
package com.google.sps.data;

import com.google.cloud.datastore.TimestampValue;

public final class HabitList {
    

    //private final TimestampValue notifyTime;
    private final String listName;
    private final String username;

    public HabitList(/*TimestampValue notifyTime,*/ String listName, String username) {
        //this.notifyTime = notifyTime;
        this.listName = listName;
        this.username = username;
    }
}
