package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    public int id = Integer.MAX_VALUE;
    private  String firstname;
    private  String surname;
    private  String address;
    private  String phone;
    private  String email;
    private  String group;

    public ContactData withId(int id) {
        this.id=id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname=firstname;
        return this;
    }

    public ContactData withSurname(String surname) {
        this.surname=surname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address=address;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone=phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email=email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group=group;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that=(ContactData) o;
        return id == that.id &&
                Objects.equals( firstname, that.firstname ) &&
                Objects.equals( surname, that.surname );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, firstname, surname );
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


    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", surname='" + surname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }

}






