package ru.stqa.pft.newcontact;

import org.testng.annotations.Test;

public class ContactCreationTests extends ContactBase {


  @Test
  public void testContactCreation() throws Exception {
    wd.get("http://localhost/addressbook/delete.php?part=5;");
    gotoNewContactPage();
    fillContactForm(new ContactData("Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by"));
    submitContactCreation();
    returnToHomepage();
    Logout();
  }


}
