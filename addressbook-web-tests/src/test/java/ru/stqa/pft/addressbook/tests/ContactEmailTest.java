package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

    @Test
    public void testContactEmails()  {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

       assertThat( (contact.getEmail()), equalTo(contactInfoFromEditForm.getEmail()));
       assertThat( (contact.getEmail2()), equalTo( contactInfoFromEditForm.getEmail2()));
       assertThat((contact.getEmail3()), equalTo( contactInfoFromEditForm.getEmail3()));
    }
}



