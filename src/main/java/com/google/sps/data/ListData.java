
package com.google.sps.data;

import com.google.cloud.datastore.TimestampValue;

public final class HabitList {
    

    //private final TimestampValue notifyTime;
    private final String listName; // Head of the habit list/The old habit that has new stacked habits
    //private final String username;

    public HabitList(/*TimestampValue notifyTime,*/ String listName) {
        //this.notifyTime = notifyTime;
        this.listName = listName;
        //this.username = username;
    }

}
