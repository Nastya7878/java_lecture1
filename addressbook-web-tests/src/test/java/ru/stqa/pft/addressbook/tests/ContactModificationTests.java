package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
        app.goTo().homePage();
        ContactHelper contactHelper=app.contact();
        contactHelper.create ( new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ));
        }
    }

    @Test
    public void testContactModification() {
        ContactHelper contactHelper=app.contact();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.acceptNextAlert=true;
        ContactData contact = new ContactData()
                .withId( modifiedContact.getId() ).withSurname( "Callous" ).withFirstname( "Maria" ).withAddress("address" ).withHomePhone( "" ).withMobilePhone( "" ).withWorkPhone( "" )
                .withEmail( "" ).withEmail2( "" ).withEmail3( "" );
        app.goTo().homePage();
        contactHelper.modify(contact);

        assertThat( app.contact().count(), equalTo( before.size()));
        Contacts after = app.db().contacts();
        assertThat( after, equalTo( before.without( modifiedContact ).withAdded(contact)) );
        verifyContactListInUI();

    }
}