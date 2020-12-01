package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper=app.getGroupHelper();
        List<GroupData> before=groupHelper.getGroupList();
        GroupData group = new GroupData( "test 1", "null", "null" );
        groupHelper.createGroup(group);
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after=groupHelper.getGroupList();
        Assert.assertEquals( after.size(), before.size() + 1 );




        group.setId(after.stream().max( (o1, o2) -> Integer.compare( o1.getId(),o2.getId() )).get().getId());
        before.add(group);
        Assert.assertEquals( new HashSet<Object>(before), new HashSet<Object>(after) );
    }
}
