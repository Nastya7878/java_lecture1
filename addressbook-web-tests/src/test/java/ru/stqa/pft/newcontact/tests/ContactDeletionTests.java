package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends ContactBase {



  @Test
  public void testContactDeletion() throws Exception {
    app.gotoHomePage();
    app.selectContact();
    app.acceptNextAlert= true;
    app.deleteSelectedContact();
    app.AlertAndGetItsText();
    app.returnHomePage();
    app.Logout();
  }
}
