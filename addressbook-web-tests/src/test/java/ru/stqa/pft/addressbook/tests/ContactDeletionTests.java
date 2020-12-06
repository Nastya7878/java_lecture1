package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  private ContactHelper contactHelper;

  @BeforeMethod
  public void ensurePreconditions() {
    this.contactHelper=this.getApp().contact();
    app.goTo().homePage();
    if (contactHelper.list().size() == 0) {
      contactHelper.create( new ContactData( "Anastasia", "Verem", "Minsk", "+375298641245", "babaVera@tut.by", "test11" ), true );
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    List<ContactData> before = contactHelper.list();
    int index = before.size() - 1;
    contactHelper.delete( index, app );
    List<ContactData> after = contactHelper.list();
    Assert.assertEquals( after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals( before, after );
  }
}
