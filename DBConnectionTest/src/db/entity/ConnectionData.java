/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.entity;

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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getServer() {
        return server;
    }

    public String getPort() {
        return port;
    }

    
}
