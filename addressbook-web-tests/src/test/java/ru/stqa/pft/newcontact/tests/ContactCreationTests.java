package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.newcontact.model1.ContactData;

public class ContactCreationTests extends ContactBase {


  @Test
  public void testContactCreation() throws Exception {
    app.wd.get("http://localhost/addressbook/delete.php?part=5;");
    app.gotoNewContactPage();
    app.fillContactForm(new ContactData("Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by"));
    app.submitContactCreation();
    app.returnToHomepage();
    app.Logout();
  }

}
