package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias( "contacts" )
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField

    @Id
    @Column(name = "id")
    public int id=Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String surname;

    @Transient
    private String address;
    @Transient
    private String phone;
    @Transient
    private String group;

    @Column(name = "home")
    @Type( type="text" )
    private String homePhone;

    @Column(name = "mobile")
    @Type( type="text" )
    private String mobilePhone;

    @Column(name = "work")
    @Type( type="text" )
    private String workPhone;

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String email;
    @Transient
    private String email2;
    @Transient
    private String email3;

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Column(name = "photo")
    @Type( type="text" )
    private String photo;

    public File getPhoto() { return new File (photo);}

    public ContactData withPhoto(File photo) {
        this.photo=photo.getPath();
        return this;
    }

    public ContactData withEmail(String email) {
        this.email=email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2=email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3=email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones=allPhones;
        return this;
    }


    public ContactData withAllEmail(String allEmails) {
        this.allEmails=allEmails;
        return this;
    }


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


    public ContactData withGroup(String group) {
        this.group=group;
        return this;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone=homePhone;
        return this;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone=mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone=workPhone;
        return this;
    }

    public String getWorkPhone() { return workPhone; }




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

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() { return allEmails; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that=(ContactData) o;
        return id == that.id &&
                Objects.equals( firstname, that.firstname ) &&
                Objects.equals( surname, that.surname ) &&
                Objects.equals( address, that.address ) &&
                Objects.equals( phone, that.phone ) &&
                Objects.equals( group, that.group ) &&
                Objects.equals( homePhone, that.homePhone ) &&
                Objects.equals( mobilePhone, that.mobilePhone ) &&
                Objects.equals( workPhone, that.workPhone ) &&
                Objects.equals( allPhones, that.allPhones ) &&
                Objects.equals( allEmails, that.allEmails ) &&
                Objects.equals( email, that.email ) &&
                Objects.equals( email2, that.email2 ) &&
                Objects.equals( email3, that.email3 );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, firstname, surname,
                address, phone, group, homePhone, mobilePhone,
                workPhone, allPhones, email, email2, email3, allEmails );
    }
}








