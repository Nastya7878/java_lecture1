package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    ContactHelper contactHelper=app.getContactHelper();

    List<ContactData> before = contactHelper.getContactList();

    app.gotoNewContactPage();
    ContactData contact = new ContactData(  "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" );
    contactHelper.fillContactForm(contact, true);
    contactHelper.submitContactCreation();
    contactHelper.returnToHomepage();
    List<ContactData> after = contactHelper.getContactList();
    Assert.assertEquals( after.size(), before.size() + 1  );

    int max = 0;
    for (ContactData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals( new HashSet<Object>(before),  new HashSet<Object>(after));
  }
}