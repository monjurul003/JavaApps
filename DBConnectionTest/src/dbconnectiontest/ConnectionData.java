/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnectiontest;

/**
 *
 * @author Rasel
 */
public class ConnectionData {

    private String database;
    private String username;
    private String password;
    private String server;
    private String port;

    public ConnectionData() {
    }

    public ConnectionData(String database, String username, String password, String server, String port) {
        this.database = database;
        this.username = username;
        this.password = password;
        this.server = server;
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
}
