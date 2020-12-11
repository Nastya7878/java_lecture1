package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

  private ContactHelper contactHelper;

  @BeforeMethod
  public void ensurePreconditions() {
    this.contactHelper=this.getApp().contact();
    app.goTo().homePage();
    if (contactHelper.all().size() == 0) {
      contactHelper.create( new ContactData( ).withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ).withPhone("+375298641245").withEmail("babaVera@tut.by").withGroup( "test11" ));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Set<ContactData> before = contactHelper.all();
    ContactData deletedContact = before.iterator().next();
    contactHelper.delete( deletedContact, app );
    Set<ContactData> after = contactHelper.all();
    Assert.assertEquals( after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals( before, after );
  }
}
