package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper=app.getGroupHelper();
        if (!groupHelper.isThereAGroup()) {
            groupHelper.createGroup( new GroupData( "test1", "null", "null" ) );
        }
    }

    @Test
    public void testGroupModification() {
        GroupHelper groupHelper=app.getGroupHelper();

        List<GroupData> before=groupHelper.getGroupList();
        int index=before.size() - 1;
        GroupData group=new GroupData( before.get( index ).getId(), "test1", "test2", "test3" );
        groupHelper.modifyGroup( groupHelper, index, group );
        List<GroupData> after=groupHelper.getGroupList();
        Assert.assertEquals( after.size(), before.size() );


        before.remove( index );
        before.add( group );
        Comparator<? super GroupData> byId=(g1, g2) -> Integer.compare( g1.getId(), g2.getId() );
        before.sort( byId );
        after.sort( byId );
        Assert.assertEquals( before, after );
    }
}


