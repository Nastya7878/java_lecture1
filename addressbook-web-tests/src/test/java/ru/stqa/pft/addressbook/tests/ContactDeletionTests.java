package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {


  private ContactHelper contactHelper;



  @Test
  public void testContactDeletion() throws Exception {
    this.contactHelper=this.getApp().getContactHelper();

    app.getNavigationHelper().gotoHomePage();
    int before = contactHelper.getContactCount();

    if (!contactHelper.isThereAGroup()) {
      contactHelper.createContact( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
    }

    app.getNavigationHelper().gotoHomePage();
    contactHelper.selectContact(before - 1);
    app.acceptNextAlert=true;
    contactHelper.deleteSelectedContact();

    app.AlertAndGetItsText();
    app.returnHomePage();
    int after = contactHelper.getContactCount();
    Assert.assertEquals( after, before - 1);
  }
}
