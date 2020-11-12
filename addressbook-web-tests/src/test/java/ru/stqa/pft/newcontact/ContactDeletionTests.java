package ru.stqa.pft.newcontact;

import org.testng.annotations.Test;

public class ContactDeletionTests extends ContactBase {



  @Test
  public void testContactDeletion() throws Exception {
    gotoHomePage();
    selectContact();
    acceptNextAlert = true;
    deleteSelectedContact();
    AlertAndGetItsText();
    returnHomePage();
    Logout();
  }
}
