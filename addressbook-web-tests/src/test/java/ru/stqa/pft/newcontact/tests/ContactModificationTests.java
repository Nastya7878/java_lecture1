package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.newcontact.model1.ContactData;

public class ContactModificationTests extends ContactBase {

    @Test
    public void testContactModification() {
        app.getNavigationContactHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Maria", "Callous", "Milan", "+375292525258", "Milana@tut.by"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }
}
