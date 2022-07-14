
package com.google.sps.data;

public final class HabitData {
    private final long id;
    private final String habitName;
    private final String listName;
    private final Boolean isComplete;

    // TODO: 
    // Need to figure out Cascading Deletes
    // (Referencing this link: https://cloud.google.com/appengine/docs/standard/java/datastore/jdo/relationships?csw=1#Dependent_Children_and_Cascading_Deletes)
    //                         "Dependent Children and Cascading Deletes")

    public HabitData(long id, String habitName, String listName, Boolean isComplete) {
        this.id = id;
        this.habitName = habitName;
        this.listName = listName;
        this.isComplete = isComplete;

    }
}