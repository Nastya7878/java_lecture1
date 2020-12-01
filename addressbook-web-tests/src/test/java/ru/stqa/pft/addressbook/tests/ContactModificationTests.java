package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    private ContactHelper contactHelper;


    @Test
    public void testContactModification() {
        this.contactHelper =this.getApp().getContactHelper();
        app.getNavigationHelper().gotoHomePage();

        if (!contactHelper.isThereAGroup()) {
            contactHelper.createContact( new ContactData( "Verem", "Anastasia", "Minsk", "+375298641245", "babaVera@tut.by", "test1"), true);
        }

        List<ContactData> before = contactHelper.getContactList();
        contactHelper.selectContact(before.size() - 1);
        app.acceptNextAlert=true;
        contactHelper.initContactModification();
        ContactData contact = new ContactData (  "Callous", "Maria", "Milan", "+375292525258", "Milana@tut.by", "null");
        contactHelper.fillContactForm(contact, false);
        contactHelper.submitContactModification();
        contactHelper.returnToHomepage();
        List<ContactData> after = contactHelper.getContactList();
        Assert.assertEquals( after.size(), before.size() );

        before.remove(before.size() - 1);
        before.add( contact );

        Assert.assertEquals( new HashSet<Object>(before),  new HashSet<Object>(after));

    }
}