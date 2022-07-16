
package com.google.sps.data;

import com.google.api.client.util.DateTime;
import com.google.cloud.Timestamp;
import com.google.cloud.datastore.TimestampValue;

public final class HabitData {
    private final long id;
    private final String habitName;
    private final String listName;
    private final Boolean isComplete;
    private final Timestamp timeCreated;

    // TODO: 
    // Need to figure out Cascading Deletes
    // (Referencing this link: https://cloud.google.com/appengine/docs/standard/java/datastore/jdo/relationships?csw=1#Dependent_Children_and_Cascading_Deletes)
    //                         "Dependent Children and Cascading Deletes")

    public HabitData(long id, String habitName, String listName, Boolean isComplete, Timestamp timeCreated) {
        this.id = id;
        this.habitName = habitName;
        this.listName = listName;
        this.isComplete = isComplete;
        this.timeCreated = timeCreated;

    }
}