package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends ContactBase{



  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationContactHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.acceptNextAlert= true;
    app.getContactHelper().deleteSelectedContact();
    app.AlertAndGetItsText();
    app.returnHomePage();
    app.Logout();
  }
}
