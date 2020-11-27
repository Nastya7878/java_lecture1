package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper = app.getGroupHelper();
        int before= groupHelper.getGroupCount();
        groupHelper.createGroup( new GroupData( "test1", "null", "null" ) );
        app.getNavigationHelper().gotoGroupPage();
        int after=groupHelper.getGroupCount();
        Assert.assertEquals( after, before + 1 );
       }
}
