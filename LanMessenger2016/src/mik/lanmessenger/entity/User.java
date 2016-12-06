/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mik.lanmessenger.entity;

/**
 *
 * @author MIK
 */
public class User {
    private String name, password, ip;

    public User() {
    }

    
    public User(String name, String password, String ip) {
        this.name = name;
        this.password = password;
        this.ip = ip;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
}
