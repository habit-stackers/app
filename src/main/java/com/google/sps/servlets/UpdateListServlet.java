package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//NOTE: This does not work right now 
@WebServlet("/update-list")
public class UpdateListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
      String keyName = "listName";
      Key key = datastore.newKeyFactory().setKind("ListData").newKey(keyName);
      Entity.Builder entityBuilder = Entity.newBuilder(key);

      //TODO: Figure out how to pass user input here
      entityBuilder.set("propertyName", "value"); 
      Entity entity = entityBuilder.build();
      datastore.put(entity);
    

    }
}