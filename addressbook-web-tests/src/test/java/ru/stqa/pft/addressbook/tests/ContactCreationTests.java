package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    ContactHelper contactHelper=app.contact();
    List<ContactData> before = contactHelper.list();
    app.gotoNewContactPage();
    ContactData contact = new ContactData( "Verem", "Anastasia", "Minsk", "+375298641245", "babaVera@tut.by", "test1");
    contactHelper.create(contact);
    List<ContactData> after = contactHelper.list();
    Assert.assertEquals( after.size(), before.size() + 1 );



    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare( g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals( before, after);
  }

}

