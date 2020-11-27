package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper();
    app.gotoNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomepage();
    app.Logout();
  }

}