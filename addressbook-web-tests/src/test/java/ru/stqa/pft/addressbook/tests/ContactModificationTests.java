package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    private ContactHelper contactHelper;

    @BeforeMethod
    public void ensurePreconditions() {
        this.contactHelper =this.getApp().contact();
        app.goTo().homePage();
        if (contactHelper.all().size() == 0) {
            contactHelper.create ( new ContactData( ).withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ).withPhone("+375298641245").withEmail("babaVera@tut.by").withGroup( "test1" ));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = contactHelper.all();
        ContactData modifiedContact = before.iterator().next();
        app.acceptNextAlert=true;
        ContactData contact = new ContactData
                ().withId( modifiedContact.getId() ).withSurname( "Callous" ).withFirstname( "Maria" ).withAddress( "Milan" ).withPhone( "+375292525258" ).withEmail( "Milana@tut.by" ).withGroup("null");
        contactHelper.modify(contact);
        assertThat(app.contact().count(), equalTo(before.size() ));
        Contacts after = contactHelper.all();
        assertThat(after, equalTo( before.without(modifiedContact).withAdded( contact )));




    }



}