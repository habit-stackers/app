package main.java.com.google.sps.data;

public final class Habit {
    private final string habitName;
    private final string listName; // primary key?

    // Need to figure out Cascading Deletes
    // (Referencing this link: https://cloud.google.com/appengine/docs/standard/java/datastore/jdo/relationships?csw=1#Dependent_Children_and_Cascading_Deletes)
    //                         "Dependent Children and Cascading Deletes")

    public Habit(string habitName, string listName) {
        this.habitName = habitName;
        this.listName = listName;
    }
}
