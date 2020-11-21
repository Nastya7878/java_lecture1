package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.newcontact.model1.ContactData;

public class ContactDeletionTests extends ContactBase{


  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAGroup()) {
      app.getContactHelper().createContact( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
    }
    app.getContactHelper().selectContact();
    app.acceptNextAlert=true;
    app.getContactHelper().deleteSelectedContact();
    app.AlertAndGetItsText();
    app.returnHomePage();
    app.Logout();
  }
}
