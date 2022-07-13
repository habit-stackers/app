package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.TimestampValue;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.sps.data.HabitData;
import com.google.sps.data.ListData;


// This servlet iterates through HabitData for all habitName associated with a certain listName 
@WebServlet("/display-habit")
public class DisplayHabitServlet extends HttpServlet {
    
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("HabitData")/*.setOrderBy(OrderBy.desc("habitName"))*/.build();
    QueryResults<Entity> results = datastore.run(query);

    // Create a List object of type HabitData
    List<HabitData> newHabitList = new ArrayList<>();

    // Iterate through the query until the query is empty
    while (results.hasNext()) {
      Entity entity = results.next();
      
      // Obtain the attributes with their specific parameter names
      String listName = entity.getString("listName");
      String habitName = entity.getString("habitName");
      Boolean isComplete = entity.getBoolean("isComplete");

      // Create new Entity using the obtained attribute values
      HabitData habitList = new HabitData(habitName, listName, isComplete);

      // Add to the HabitList List Object
      newHabitList.add(habitList);
     }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(newHabitList));
  }
}
