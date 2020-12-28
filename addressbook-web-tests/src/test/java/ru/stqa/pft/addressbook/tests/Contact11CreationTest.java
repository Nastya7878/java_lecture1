package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class Contact11CreationTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            GroupHelper groupHelper=app.group();
            groupHelper.create( new GroupData().withName( "test1" ) );
        }
    }

    @Test
    public void testContact11Creation(ContactData contactData) {
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/stru.png");
        ContactData newContact = new ContactData().withSurname( "test_surname" ).withFirstname( "test_firstname" ).withPhoto( photo )
                .inGroup (groups.iterator().next());
        app.goTo().homePage();
        app.contact().initContactCreation();
        app.contact().fillContactForm( newContact, true);
        app.contact().submitContactCreation();
        app.contact().returnToHomepage();

    }
}
