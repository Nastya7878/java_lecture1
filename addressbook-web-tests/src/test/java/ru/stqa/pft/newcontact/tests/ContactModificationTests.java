package ru.stqa.pft.newcontact.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.newcontact.model1.ContactData;

public class ContactModificationTests extends ContactBase {

    @Test
    public void testContactModification() {
        app.getNavigationContactHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAGroup()) {
            app.getContactHelper().createContact( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm( new ContactData( "Maria", "Callous", "Milan", "+375292525258", "Milana@tut.by", null ), false );
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }
}
