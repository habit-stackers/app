
package com.google.sps.data;

import com.google.cloud.datastore.TimestampValue;

public final class ListData {
    
    // TODO: Implement commented areas later.
    
    //private final dateTimeValue notifyTime;
    private final String listName; // Head of the habit list/The old habit that has new stacked habits
    private final String username;

    public ListData(/*TimestampValue notifyTime, */String listName, String username) {
        //this.notifyTime = notifyTime;
        this.listName = listName;
        this.username = username;
    }

}
