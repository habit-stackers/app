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

//TODO: This should have cascading effect to all habits under list
@WebServlet("/delete-list") 
public class DeleteListServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String listName = request.getParameter("listName");
  
      Datastore datastore = DatastoreOptions.getDefaultInstance().getService(); 
      KeyFactory keyFactory = datastore.newKeyFactory().setKind("ListData"); 
      Key listEntityKey = keyFactory.newKey(listName);  
      datastore.delete(listEntityKey);
    }
}