package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] { new ContactData().withSurname("test1_surname").withFirstname("test1_name").withAddress( "address1") });
    list.add(new Object[] { new ContactData().withSurname("test2_surname").withFirstname("test2_name").withAddress( "address2") });
    list.add(new Object[] { new ContactData().withSurname("test3_surname").withFirstname("test3_name").withAddress( "address3") });
    return list.iterator();
  }



  @Test (dataProvider="validContacts")
  public void testContactCreation(ContactData contact) {
      ContactHelper contactHelper=app.contact();
      Contacts before=contactHelper.all();
      app.gotoNewContactPage();
      contactHelper.create( contact );
      Contacts after=contactHelper.all();
      assertThat( app.contact().count(), equalTo( before.size() + 1 ) );
    //  assertThat( after, equalTo(before.withAdded( contact.withId( after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt() ) ) ) );
    }
  }









