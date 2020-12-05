package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        if (groupHelper.all().size()== 0) {
            groupHelper.create( new GroupData().withName( "test1" ) );
        }
    }

    @Test
    public void testGroupModification() {
        GroupHelper groupHelper=app.group();

        Set<GroupData> before=groupHelper.all();
        GroupData modifiedGroup=before.iterator().next();
        GroupData group=new GroupData()
                .withId( modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter( "test3" );
        groupHelper.modify(group);
        Set<GroupData> after=groupHelper.all();
        Assert.assertEquals( after.size(), before.size() );


        before.remove( modifiedGroup );
        before.add( group );
        Assert.assertEquals( before, after );
    }
}


