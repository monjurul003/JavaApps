package dbconnectiontest;

public class Contacts {

    private String name;
    private String mobile_no;
    private String email;
    private String address;
    private String gender;
    private String work_phone;

    public Contacts() {
    }

    public Contacts(String name, String mobile_no) {
        this.name = name;
        this.mobile_no = mobile_no;
    }

    public Contacts(String name, String mobile_no, String email, String address, String gender, String work_phone) {
        this.name = name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.work_phone = work_phone;
    }

    public void printContact() {
        System.out.println(this.name + "," + this.mobile_no + "," + this.email + "," + this.address + "," + this.gender + "," + this.work_phone);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }
}