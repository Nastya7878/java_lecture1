package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test 1", "test 2", "test 3"));
    submitGroupCreation();
    gotoGroupPage();
    logout();
  }

}
