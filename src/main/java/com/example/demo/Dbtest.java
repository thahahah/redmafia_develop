package com.example.demo;

import com.mysql.cj.protocol.Resultset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Dbtest {

    @GetMapping("test")
    public ArrayList<String> tests(){
        ArrayList<String> result = addtest();
        return result;
    }
    public ArrayList<String> addtest(){
        ArrayList<String> list = new ArrayList<String>();
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://database-1.caxyf4kvshky.ap-northeast-2.rds.amazonaws.com:3306";
        String ID = "admin";
        String PW = "zoqtmxhs12";

        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL,ID,PW);
        statement = connection.createStatement();

        String sql;
        sql = "show databases";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            list.add(resultSet.getString("database"));
            System.out.println("database--> "+resultSet.getString("database"));
        }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
