/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.oop.processor;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import swe.oop.entity.Contact;
import swe.oop.entity.User;

/**
 *
 * @author Rasel
 */
public class DBManager {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement stmt;
    private ArrayList<Contact> conList;
    private ArrayList<User> userList;

    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Connection getConnection(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createPhoneBookTable() {

        try {
            this.conn = getConnection();
            this.stmt = conn.createStatement();
            String sql = "CREATE TABLE PHONEBOOK "
                    + "(NAME           VARCHAR    NOT NULL, "
                    + " NUMBER         VARCHAR     NOT NULL";
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void createUsaerTable() {

        try {
            this.conn = getConnection();
            this.stmt = conn.createStatement();
            String sql = "CREATE TABLE USER "
                    + "(NAME           VARCHAR    NOT NULL, "
                    + " PASSWORD         VARCHAR     NOT NULL";
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void insertIntoUserTable(String name, String password) {

        try {
            this.conn = getConnection();
            this.stmt = conn.createStatement();
            this.conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql = "INSERT INTO USER (NAME,PASSWORD) "
                    + "VALUES ('" + name + "', '" + password + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            this.conn.commit();
            this.conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    
      public void insertArrayListIntoUserTable() {
        for (int i = 0; i < this.userList.size(); i++) {
            insertIntoPhonebook(this.userList.get(i).getUser_name(), this.userList.get(i).getPaasword());
        }
    }

    public void insertIntoPhonebook(String name, String number) {

        try {
            this.conn = getConnection();
            this.stmt = conn.createStatement();
            this.conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            String sql = "INSERT INTO PHONEBOOK (NAME,NUMBER) "
                    + "VALUES ('" + name + "', '" + number + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            this.conn.commit();
            this.conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public void insertArrayListIntoPhonebookTable() {
        for (int i = 0; i < this.conList.size(); i++) {
            insertIntoPhonebook(this.conList.get(i).getName(), this.conList.get(i).getNumber());
        }
    }

    public ArrayList<Contact> selectFromDBandInitContactList() {
        this.conList = new ArrayList<Contact>();

        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            this.conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                String name = rs.getString("name");
                String number = rs.getString("number");
                Contact obj = new Contact(name, number);
                this.conList.add(obj);
            }
            rs.close();
            stmt.close();
            this.conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return this.conList;
    }

    public ArrayList<User> selectFromDBandInitUserList() {
        this.userList = new ArrayList<User>();

        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            this.conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                String name = rs.getString("name");
                String pass = rs.getString("PASSWORD");
                User obj = new User(name, pass);
                this.userList.add(obj);
            }
            rs.close();
            stmt.close();
            this.conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return this.userList;
    }
}
