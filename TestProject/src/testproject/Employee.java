/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

/**
 *
 * @author Daffodil PC
 */
public class Employee {

    private String name, id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }
    
   

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void printInfo() {
        System.out.println("Name: " + name + "\n Id:" + id);
    }
  
}
