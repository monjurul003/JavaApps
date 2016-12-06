/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swe.oop.entity;

/**
 *
 * @author Monjurul Islam Khan
 */
public class User {
    private String user_name;
    private String paasword;

    public User() {
    }

    public User(String user_name, String paasword) {
        this.user_name = user_name;
        this.paasword = paasword;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPaasword() {
        return paasword;
    }

    public void setPaasword(String paasword) {
        this.paasword = paasword;
    }
    
    
    
}
