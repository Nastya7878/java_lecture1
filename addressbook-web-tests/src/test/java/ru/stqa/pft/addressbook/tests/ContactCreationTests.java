package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    ContactHelper contactHelper=app.contact();
    Contacts before = contactHelper.all();
    app.gotoNewContactPage();
    ContactData contact = new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ).withPhone("+375298641245").withEmail("babaVera@tut.by").withGroup( "test1" );
    contactHelper.create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1 ));
    Contacts after = contactHelper.all();
    assertThat( after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))) );
  }

}

