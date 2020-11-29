package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {


  private ContactHelper contactHelper;



  @Test
  public void testContactDeletion() throws Exception {
    this.contactHelper=this.getApp().getContactHelper();

    app.getNavigationHelper().gotoHomePage();

    if (!contactHelper.isThereAGroup()) {
      contactHelper.createContact( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
    }

    List<ContactData> before = contactHelper.getContactList();
    contactHelper.selectContact(before.size() - 1);
    app.acceptNextAlert=true;
    contactHelper.deleteSelectedContact();

    app.AlertAndGetItsText();
    app.returnHomePage();
    List<ContactData> after = contactHelper.getContactList();
    Assert.assertEquals( after.size(), before.size() - 1);
  }
}
