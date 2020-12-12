package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @Test (enabled=false)
  public void testContactCreation() {
    ContactHelper contactHelper=app.contact();
    Contacts before = contactHelper.all();
    app.gotoNewContactPage();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withSurname( "test_surname" ).withFirstname( "test_name" ).withPhoto( photo );
    contactHelper.create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1 ));
    Contacts after = contactHelper.all();
    assertThat( after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))) );
  }

  @Test
  public void testCurrentDir() {
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}

