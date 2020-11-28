package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper = app.getGroupHelper();
        List<GroupData> before = groupHelper.getGroupList();
        groupHelper.createGroup( new GroupData( "test1", "null", "null" ) );
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = groupHelper.getGroupList();
        Assert.assertEquals( after.size(), before.size() + 1 );
       }
}
