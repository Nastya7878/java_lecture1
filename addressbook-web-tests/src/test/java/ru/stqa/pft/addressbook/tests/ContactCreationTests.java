package ru.stqa.pft.addressbook.tests;


import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    try (BufferedReader reader=new BufferedReader( new FileReader( new File( "src/test/resources/contacts.xml" ) ) )) {
      String xml="";
      String line=reader.readLine();
      while (line != null) {
        xml+=line;
        line=reader.readLine();
      }
      XStream xstream=new XStream();
      xstream.processAnnotations( Contacts.class );
      List<ContactData> contacts=(List<ContactData>) xstream.fromXML( xml );
      return contacts.stream().map( (g) -> new Object[]{g} ).collect( Collectors.toList() ).iterator();
    }
  }

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      ContactHelper contactHelper=app.contact();
      contactHelper.create( new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ));
    }
  }


  @Test(dataProvider="validContacts")
  public void testContactCreation(ContactData contact) {
    ContactHelper contactHelper=app.contact();
    Contacts before=app.db().contacts();
    app.gotoNewContactPage();
    contactHelper.create( contact );
    Contacts after=app.db().contacts();
    assertThat( app.contact().count(), equalTo( before.size() + 1 ) );
    assertThat( after, equalTo( before.withAdded( contact.withId( after.stream().mapToInt( (g) -> g.getId() ).max().getAsInt() ) ) ) );
    verifyContactListInUI();


  }

}












