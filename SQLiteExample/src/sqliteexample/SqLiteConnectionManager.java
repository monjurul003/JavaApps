/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqliteexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

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
            String sql = "CREATE TABLE IF NOT EXISTS CONTACT "
                    + "(ID INT PRIMARY KEY     NOT NULL,"
                    + " NAME           VARCHAR    NOT NULL, "
                    + " NUMBER         VARCHAR    NOT NULL)";
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
            String sql = "DROP TABLE test.CONTACT";
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
            String sql = "INSERT INTO CONTACT (ID,NAME,NUMBER) "
                    + "VALUES (1, 'Paul','01764684636' );";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTACT (ID,NAME,NUMBER) "
                    + "VALUES (2, 'Allen','01564684636');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTACT (ID,NAME,NUMBER) "
                    + "VALUES (3, 'Teddy','0194684636');";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO CONTACT (ID,NAME,NUMBER) "
                    + "VALUES (4, 'Mark','0186456667');";
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTACT;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String number = rs.getString("NUMBER");
               
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("NUmber = " + number);
                System.out.println();
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
            String sql = "UPDATE CONTACT set NUMBER = '019879797' where ID=1;";
            stmt.executeUpdate(sql);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTACT;");
            while (rs.next()) {
             int id = rs.getInt("id");
                String name = rs.getString("name");
                String number = rs.getString("NUMBER");
               
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("NUmber = " + number);
                System.out.println();
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
            String sql = "DELETE from CONTACT where ID=2;";
            stmt.executeUpdate(sql);
            c.commit();

           
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTACT;");
            while (rs.next()) {
             int id = rs.getInt("id");
                String name = rs.getString("name");
                String number = rs.getString("NUMBER");
               
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("NUmber = " + number);
                System.out.println();
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

    public ArrayList<Contact> selectAndReturnAsAList() {
        ArrayList<Contact> list = new ArrayList<Contact>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CONTACT;");

            while (rs.next()) {
                String name = rs.getString("name");
                String number = rs.getString("number");
                Contact obj = new Contact(name, number);
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

}
