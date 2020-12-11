package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    ContactHelper contactHelper=app.contact();
    Set<ContactData> before = contactHelper.all();
    app.gotoNewContactPage();
    ContactData contact = new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ).withPhone("+375298641245").withEmail("babaVera@tut.by").withGroup( "test1" );
    contactHelper.create(contact);
    Set<ContactData> after = contactHelper.all();
    Assert.assertEquals( after.size(), before.size() + 1 );


    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals( before, after);
  }

}

