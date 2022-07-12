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

import com.google.sps.data.ListData;


// Servlet responsible for listing tasks. 
@WebServlet("/list-habit")
public class ListHabitServlet extends HttpServlet {
    
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
     Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    
    Query<Entity> query = Query.newEntityQueryBuilder().setKind("HabitData").setOrderBy(OrderBy.desc("habitName")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<ListData> habitListList = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();
      
      // TODO: Implement uncommented part after function works

      // TimestampValue notifyTime = entity.get("notifyTime");
      // String username = entity.getString("username");
      String habitName = entity.getString("habitName");

      ListData habitList = new ListData(/*notifyTime,*/ habitName/* , username*/);
      habitListList.add(habitList);
     }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(habitListList));
  }
}
