package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias( "group" )
@Entity
@Table(name = "group_list")
public class GroupData implements CharSequence {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private  int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "group_name")
    private  String name;

    @Expose
    @Column(name = "group_header")
    @Type( type="text" )
    private  String header;

    @Expose
    @Column(name = "group_footer")
    @Type( type="text" )
    private  String footer;

    @ManyToMany(mappedBy= "groups")
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public Set<ContactData> getContacts() {
        return contacts;
    }

    public GroupData withId(int id) {
        this.id=id;
        return this;
    }
    public GroupData withName(String name) {
        this.name=name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header=header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer=footer;
        return this;
    }


    public String getName() { return name;  }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public int getId() {  return id;     }

    public void setID(int max) {
        this.id=id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData=(GroupData) o;
        return id == groupData.id &&
                Objects.equals( name, groupData.name );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id, name );
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


