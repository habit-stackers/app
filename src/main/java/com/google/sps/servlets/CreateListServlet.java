
package com.google.sps.servlets;

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
import org.jsoup.safety.Whitelist;

@WebServlet("/create-list")
public class CreateListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // Sanitize user input to remove HTML tags and JavaScript.
      String list = Jsoup.clean(request.getParameter("listName"), Whitelist.none());
      //long timestamp = System.currentTimeMillis();
      String username = Jsoup.clean(request.getParameter("username"), Whitelist.none());
  
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
      KeyFactory keyFactory = datastore.newKeyFactory().setKind("List");
      FullEntity listEntity =
          Entity.newBuilder(keyFactory.newKey())
              .set("listName", list)
              //.set("notifyTime", timestamp)
              .set("username", username)
              .build();
      datastore.put(listEntity);
  
      response.sendRedirect("/index.html");
    }
}

