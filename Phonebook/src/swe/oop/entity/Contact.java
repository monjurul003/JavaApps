/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swe.oop.entity;

/**
 *
 * @author Administrator
 */
public class Contact {
    private String name, number;

    public Contact() {
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
