/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mik.lanmessenger.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mik.lanmessenger.entity.User;

/**
 *
 * @author Daffodil PC
 */
public class SqLiteConnectionManager {

    Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    private ResultSetMetaData rsmd;

    public SqLiteConnectionManager() {

        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public SqLiteConnectionManager(String dbName) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createTableExample() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USER "
                    + "(ID INT PRIMARY KEY     NOT NULL,"
                    + " NAME           VARCHAR    NOT NULL, "
                    + " PASSWORD         VARCHAR    NOT NULL,"
                    + " IP         VARCHAR    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
//DROP TABLE database_name.table_name;

    public void dropTableExample() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DROP TABLE test.USER";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table dropped successfully");
    }

    public void insertExample() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO USER (ID,NAME,PASSWORD,IP) "
                    + "VALUES (1, 'Paul','qwer', '10.10.10.1' );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO USER (ID,NAME,PASSWORD,IP) "
                    + "VALUES (2, 'Allen','kisu', '10.10.10.2');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO USER (ID,NAME,PASSWORD,IP) "
                    + "VALUES (3, 'Teddy','saleha', '10.10.10.3');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO USER (ID,NAME,PASSWORD,IP) "
                    + "VALUES (4, 'Mark','asdasf', '10.10.10.5');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public void selectExample() {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");


            while (rs.next()) {
                this.printResultSet(rs);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void updateExample() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE USER set PASSWORD = 'sada' where ID=1;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                this.printResultSet(rs);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void deleteExample() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from USER where ID=2;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");
            while (rs.next()) {
                this.printResultSet(rs);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public ArrayList<User> selectAndReturnAsAList() {
        ArrayList<User> list = new ArrayList<User>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USER;");

            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                String ip = rs.getString("ip");
                User obj = new User(name, password, ip);
                list.add(obj);
            }
            rs.close();
            stmt.close();
            c.close();
            return list;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return null;
    }

    private void printResultSet(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String ip = rs.getString("ip");

            System.out.println("ID = " + id);
            System.out.println("NAME = " + name);
            System.out.println("password = " + password);
            System.out.println("IP = " + ip);
            System.out.println();
        } catch (SQLException ex) {
            Logger.getLogger(SqLiteConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
