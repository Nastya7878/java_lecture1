package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private  int id;
    private final String firstname;
    private final String surname;
    private final String address;

    @Override
    public String toString() {
        return "ContactData{" +
                "surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
          //     "id='" + id + '\'' +
          //    ", address='" + address + '\'' +
                '}';
    }

    private final String phone;
    private final String email;
    private String group;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that=(ContactData) o;
        return Objects.equals( firstname, that.firstname ) &&
                Objects.equals( surname, that.surname );
    }

    @Override
    public int hashCode() {
        return Objects.hash( firstname, surname );
    }

    public ContactData(String surname, String firstname, String address, String phone, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.surname=surname;
        this.firstname=firstname;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.group=group;
    }

    public void setId(int id) {
        this.id=id;
    }

    public ContactData(int id, String surname, String firstname, String address, String phone, String email, String group) {
        this.id=id;
        this.surname=surname;
        this.firstname=firstname;
        this.address=address;
        this.phone=phone;
        this.email=email;
        this.group=group;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }


}


