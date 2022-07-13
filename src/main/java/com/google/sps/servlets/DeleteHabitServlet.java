
package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This servlet deletes a habit by passing a header of type habitName 
@WebServlet("/delete-habit")
public class DeleteHabitServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // Obtain the headers required for this route and store into habitNameString
      String habitNameString = request.getParameter("habitName").toString();
      System.out.println("habitNameString: " + habitNameString);
      System.out.println("request.getParameter(habitName)" + request.getParameter("habitName"));
      System.out.println("request: " + request);
      // Create an instance of datastore
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

      //
      KeyFactory keyFactory = datastore.newKeyFactory().setKind("HabitData");
      System.out.println("keyFactory: " + keyFactory);
      // Create a key for the header

      Key habitEntityKey = keyFactory.newKey(habitNameString);
      System.out.println("before-habitEntityKey: " + habitEntityKey);

      //Uses the key to delete the header
      datastore.delete(habitEntityKey);
      System.out.println("after-habitEntityKey: " + habitEntityKey);

      /*
        Key taskKey = datastore.newKeyFactory().setKind("Task").newKey("sampleTask");
        Entity task =
            Entity.newBuilder(taskKey)
                .set("category", "Personal")
                .set("done", false)
                .set("priority", 4)
                .set("description", "Learn Cloud Datastore")
                .build();
       */
    }
}
  