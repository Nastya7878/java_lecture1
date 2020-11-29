package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    private ContactHelper contactHelper;


    @Test
    public void testContactModification() {
        this.contactHelper =this.getApp().getContactHelper();
        app.getNavigationHelper().gotoHomePage();

        if (!contactHelper.isThereAGroup()) {
            contactHelper.createContact( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
        }

        List<ContactData> before = contactHelper.getContactList();
        contactHelper.initContactModification();
        contactHelper.fillContactForm( new ContactData( "Maria", "Callous", "Milan", "+375292525258", "Milana@tut.by", null ), false );
        contactHelper.submitContactModification();
        contactHelper.returnToHomepage();
        List<ContactData> after = contactHelper.getContactList();
        Assert.assertEquals( after.size(), before.size() );
    }
}
