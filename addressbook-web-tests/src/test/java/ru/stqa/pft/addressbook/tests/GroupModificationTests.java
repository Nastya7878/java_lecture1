package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper = app.getGroupHelper();

        if (!groupHelper.isThereAGroup()) {
            groupHelper.createGroup( new GroupData( "test1", "null", "null" ) );
            }
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = groupHelper.getGroupList();
        groupHelper.selectGroup(before.size() - 1);
        groupHelper.initGroupModification();
        GroupData group = new GroupData(before.get( before.size() - 1 ).getId(), "test1", "test2", "test3" );
        groupHelper.fillGroupForm( group );
        groupHelper.submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> after = groupHelper.getGroupList();
        Assert.assertEquals(after.size(), before.size() );

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals( new HashSet<Object>(before),  new HashSet<Object>(after));
    }
}


