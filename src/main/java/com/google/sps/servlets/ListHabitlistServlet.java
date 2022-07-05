package main.java.com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Task;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet responsible for listing tasks. 
@WebServlet("/list-habitlist")
public class ListHabitlistServlet extends HttpServlet {
    
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("HabitList").setOrderBy(OrderBy.desc("listName")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<HabitList> habitListList = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();

      TimeStamp notifyTime = entity.getTimeStamp("notifyTime");
      String listName = entity.getString("listName");
      String username = entity.getString("username");

      HabitList habitList = new HabitList(notifyTime, listName, username);
      habitListList.add(habitList);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(tasks));
  }
}
