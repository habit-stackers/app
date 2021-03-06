
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
import org.jsoup.safety.Safelist;
import org.jsoup.safety.Whitelist;

@WebServlet("/create-list")
public class CreateListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String list = Jsoup.clean(request.getParameter("listName"), Safelist.none());
      String username = "jessy413";
  
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
      KeyFactory keyFactory = datastore.newKeyFactory().setKind("ListData");
      FullEntity listEntity =
          Entity.newBuilder(keyFactory.newKey())
              .set("listName", list)
              .set("username", username)
              .build();
      datastore.put(listEntity);
  
      response.sendRedirect("/modify-list.html");
    }
}

