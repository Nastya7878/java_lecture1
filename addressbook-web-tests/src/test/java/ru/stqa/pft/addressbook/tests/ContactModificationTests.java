package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

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
        Set<ContactData> before = contactHelper.all();
        ContactData modifiedContact = before.iterator().next();
        app.acceptNextAlert=true;
        ContactData contact = new ContactData
                ().withId( modifiedContact.getId() ).withSurname( "Callous" ).withFirstname( "Maria" ).withAddress( "Milan" ).withPhone( "+375292525258" ).withEmail( "Milana@tut.by" ).withGroup("null");
        contactHelper.modify(contact);
        Set<ContactData> after = contactHelper.all();
        Assert.assertEquals( after.size(), before.size() );

        before.remove(modifiedContact);
        before.add( contact );
        Assert.assertEquals( before, after);



    }



}