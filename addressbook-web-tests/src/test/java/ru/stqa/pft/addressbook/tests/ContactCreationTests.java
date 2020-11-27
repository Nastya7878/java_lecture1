package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    ContactHelper contactHelper = app.getContactHelper();

    int before = contactHelper.getContactCount();
    app.gotoNewContactPage();
    contactHelper.fillContactForm(new ContactData("Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11"), true);
    contactHelper.submitContactCreation();
    contactHelper.returnToHomepage();
    int after = contactHelper.getContactCount();
    Assert.assertEquals( after, before + 1 );
    }
}