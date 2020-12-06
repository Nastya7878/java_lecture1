package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    private ContactHelper contactHelper;

    @BeforeMethod
    public void ensurePreconditions() {
        this.contactHelper =this.getApp().contact();
        app.goTo().homePage();
        if (contactHelper.list().size() == 0) {
            contactHelper.create( new ContactData( "Verem", "Anastasia", "Minsk", "+375298641245", "babaVera@tut.by", "test1"), true);
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = contactHelper.list();
        app.acceptNextAlert=true;
        int index = before.size() - 1;
        ContactData contact = new ContactData (  before.get(index).getId(), "Callous", "Maria", "Milan", "+375292525258", "Milana@tut.by", "null");
        contactHelper.modify( index, contact );
        List<ContactData> after = contactHelper.list();
        Assert.assertEquals( after.size(), before.size() );

        before.remove(index);
        before.add( contact );

        Assert.assertEquals( new HashSet<Object>(before),  new HashSet<Object>(after), null);



    }



}