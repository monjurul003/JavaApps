/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.filesafe.entity;


/**
 *
 * @author Daffodil PC
 */
public class User {
    
    private String userName;
    private String password; 
    private String creationDate;
    private String lastModifiedDate;

    public User(String userName, String password, String creationDate) {
        this.userName = userName;
        this.password = password;
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    
    
    
}
