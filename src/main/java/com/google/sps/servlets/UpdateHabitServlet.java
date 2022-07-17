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

//NOTE: This does not work right now
@WebServlet("/update-habit")
public class UpdateHabitServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
       
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("HabitData");  
        Key habitEntityKey = keyFactory.newKey(id);

        Transaction transaction = datastore.newTransaction();
          Entity task = transaction.get(habitEntityKey);
          if (task.getBoolean("isComplete")) {
            transaction.put(Entity.newBuilder(task).set("isComplete", false).build());
          }
          else if (!task.getBoolean("isComplete")) {
            transaction.put(Entity.newBuilder(task).set("isComplete", true).build());
          }
          transaction.commit();

    }
}