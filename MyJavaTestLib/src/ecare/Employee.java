/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecare;

/**
 *
 * @author monjurul.k
 */
class Employee {

    private String name;
    private int id;
    private int age;
    private String type;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public String getType() {
        return this.type;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public int getId() {
        return this.id;
    }

    public void setAge(int Age) {
        this.age = Age;
    }

    public int getAge() {
        return this.age;
    }

    Employee(String name, int id, int age, String type) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.type = type;
    }
}
