package db.connection;

import db.entity.ConnectionData;
import db.entity.Contacts;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {

    private Connection conn;
    private PreparedStatement stm;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private ConectToMySql conn2sql;
    private List<Contacts> myList;
    private ConnectionData connData;
    private String[] columnName;

    public ContactManager() {
        try {
            this.conn2sql = new ConectToMySql();
            this.conn = (Connection) conn2sql.getConnection();
            this.myList = new ArrayList<Contacts>();
            this.stm = (PreparedStatement) this.conn.prepareStatement("SELECT * FROM CONTACTS"); // Get the ResultSet from the query
            this.rs = this.stm.executeQuery(); // Get the ResultSet from the query
            this.rsmd = (ResultSetMetaData) this.rs.getMetaData();                // Get result set meta data
            int numColumns = this.rsmd.getColumnCount();
            this.columnName = new String[numColumns];
            for (int i = 0; i < numColumns; i++) {
              this.columnName[i] = rsmd.getColumnName(i+1);
              System.out.println(this.columnName[i]);
            }
            while (rs.next()) {

                String test = "";
                for (int index = 1; index < numColumns + 1; index++) {
                    if (index == 1) {
                        System.out.print(rs.getString(index));
                    } else {
                        test += rs.getString(index) + ",";
                        System.out.print("," + rs.getString(index));
                    }
                }
                String[] str = test.split(",");
                Contacts cntact = new Contacts(str[0], str[1], str[2], str[3], str[4], str[5]);
                myList.add(cntact);
                System.out.println("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public ContactManager(ConnectionData connData) {
        try {
            this.connData = connData;
            this.conn2sql = new ConectToMySql(connData.getServer(), connData.getPort(), connData.getDatabase(), connData.getUsername(), connData.getPassword());
            this.conn = (Connection) conn2sql.getConnection();
            this.myList = new ArrayList<Contacts>();
            this.stm = (PreparedStatement) this.conn.prepareStatement("SELECT * FROM CONTACTS"); // Get the ResultSet from the query
            this.rs = this.stm.executeQuery(); // Get the ResultSet from the query
            this.rsmd = (ResultSetMetaData) this.rs.getMetaData();                // Get result set meta data
            int numColumns = this.rsmd.getColumnCount();
            while (rs.next()) {

                String test = "";
                for (int index = 1; index <
                        numColumns + 1; index++) {
                    if (index == 1) {
                        System.out.print(rs.getString(index));
                    } else {
                        test += rs.getString(index) + ",";
                        System.out.print("," + rs.getString(index));
                    }

                }
                String[] str = test.split(",");
                Contacts cntact = new Contacts(str[0], str[1], str[2], str[3], str[4], str[5]);
                myList.add(cntact);
                System.out.println("");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void printContactList() {
        System.out.println("Printing the arraylist");
        for (int i = 0; i < this.myList.size(); i++) {
            this.myList.get(i).printContact();
        }

    }

    public void addContact(Contacts obj) {
        try {
            System.out.println(" VALUES (NULL ,  '" + obj.getName() + "','" + obj.getMobile_no() + "',  '" + obj.getEmail() + "','" + obj.getAddress() + "',  '" + obj.getGender() + "',  '" + obj.getWork_phone() + "')");
            this.stm = (PreparedStatement) this.conn.prepareStatement("INSERT INTO CONTACTS(`ID`,`NAME`,`MOBILE_NO`,`EMAIL`,`ADDRESS`,`GENDER`,`WORK_PHONE`)" + " VALUES (NULL ,  '" + obj.getName() + "','" + obj.getMobile_no() + "',  '" + obj.getEmail() + "','" + obj.getAddress() + "',  '" + obj.getGender() + "',  '" + obj.getWork_phone() + "')");
            this.stm.executeUpdate();
            updateList();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateList() {
        try {
            this.myList = new ArrayList<Contacts>();
            this.stm = (PreparedStatement) this.conn.prepareStatement("SELECT * FROM CONTACTS"); // Get the ResultSet from the query
            this.rs = this.stm.executeQuery(); // Get the ResultSet from the query
            this.rsmd = (ResultSetMetaData) this.rs.getMetaData();                // Get result set meta data
            int numColumns = this.rsmd.getColumnCount();
            while (rs.next()) {
                String test = "";
                for (int index = 1; index <
                        numColumns + 1; index++) {
                    if (index == 1) {
                    } else {
                        test += rs.getString(index) + ",";

                    }

                }
                String[] str = test.split(",");
                Contacts cntact = new Contacts(str[0], str[1], str[2], str[3], str[4], str[5]);
                myList.add(cntact);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public String[] getColumnName() {
        return columnName;
    }

    public void setColumnName(String[] columnName) {
        this.columnName = columnName;
    }

}