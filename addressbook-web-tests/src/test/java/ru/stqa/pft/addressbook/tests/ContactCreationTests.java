package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test (enabled=false)
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



    before.add(contact);

    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare( g1.getId(), g2.getId() );
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals( before, after);
  }
}