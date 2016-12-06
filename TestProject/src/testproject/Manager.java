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
public class Manager extends Employee {

    private String dept;

    public Manager(String name, String id, String dept) {
        super(name, id);
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    public void printInfo() {
        super.printInfo();
        System.out.println("Department: " + dept);
    }
}
