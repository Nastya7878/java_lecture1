package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {


    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        Set<GroupData> before=groupHelper.all();
        GroupData group = new GroupData().withName( "test2" ) ;
        groupHelper.create(group);
        app.goTo().groupPage();
        Set<GroupData> after=groupHelper.all();
        Assert.assertEquals( after.size(), before.size() + 1 );

        group.withId( after.stream().mapToInt( (g) -> g.getId()).max().getAsInt() );
        before.add(group);
        Assert.assertEquals( before, after);
    }
}
