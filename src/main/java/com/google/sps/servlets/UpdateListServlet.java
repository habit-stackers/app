package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Transaction;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update-list")
public class UpdateListServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong("5649205632696320");

        String listName = request.getParameter("listName");
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
       
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("ListData");  
        Key listEntityKey = keyFactory.newKey(id);

        Transaction transaction = datastore.newTransaction();
          Entity task = transaction.get(listEntityKey);
            transaction.put(Entity.newBuilder(task).set("listName", listName).build());
          transaction.commit();

          response.sendRedirect("/modify-list.html");
    }
}