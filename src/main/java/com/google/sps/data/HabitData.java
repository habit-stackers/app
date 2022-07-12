package com.google.sps.data;

public final class HabitData {
    private final String habitName;
    private final String listName;
    private final Boolean complete;

    // TODO: 
    // Need to figure out Cascading Deletes
    // (Referencing this link: https://cloud.google.com/appengine/docs/standard/java/datastore/jdo/relationships?csw=1#Dependent_Children_and_Cascading_Deletes)
    //                         "Dependent Children and Cascading Deletes")

    public HabitData(String habitName, String listName, Boolean complete) {
        this.habitName = habitName;
        this.listName = listName;
        this.complete = complete;

    }
}
