package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare( g1.getId(), g2.getId() );
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals( before, after);
    }
}


