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
    Contacts before = contactHelper.all();
    ContactData deletedContact = before.iterator().next();
    contactHelper.delete( deletedContact, app );
    Contacts after = contactHelper.all();
    assertEquals( after.size(), before.size() - 1);
    assertThat( after, equalTo( before.without(deletedContact) ) );
  }
}
