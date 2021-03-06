package com.google.sps.servlets;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/create-habit")
public class CreateHabitServlet extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String habit = Jsoup.clean(request.getParameter("habitName"), Safelist.none());
    String list = "Make the bed.";//Jsoup.clean(request.getParameter("listName"), Whitelist.none());

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("HabitData");
    FullEntity habitEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("habitName", habit)
            .set("listName", list)
            .set("isComplete",false)
            .set("timeCreated", Timestamp.now())
            .build();
    datastore.put(habitEntity);

    response.sendRedirect("/modify-list.html");
  }
}
