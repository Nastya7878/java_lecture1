package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    ContactHelper contactHelper=app.getContactHelper();

    List<ContactData> before = contactHelper.getContactList();

    app.gotoNewContactPage();
    contactHelper.fillContactForm( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
    contactHelper.submitContactCreation();
    contactHelper.returnToHomepage();
    List<ContactData> after = contactHelper.getContactList();
    Assert.assertEquals( after.size(), before.size() + 1  );
  }
}