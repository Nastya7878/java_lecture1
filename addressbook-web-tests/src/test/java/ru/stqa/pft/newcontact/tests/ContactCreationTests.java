package ru.stqa.pft.newcontact.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.newcontact.model1.ContactData;

public class ContactCreationTests extends ContactBase {

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
