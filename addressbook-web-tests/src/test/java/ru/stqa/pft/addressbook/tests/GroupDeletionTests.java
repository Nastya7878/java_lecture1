package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {
    private int i;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        if (groupHelper.all().size()== 0) {
            groupHelper.create( new GroupData().withName( "test1" ) );
        }
    }

    @Test
    public void testGroupDeletion() {
        GroupHelper groupHelper=app.group();
        Set<GroupData> before=groupHelper.all();
        GroupData deletedGroup=before.iterator().next();
        groupHelper.delete( deletedGroup );
        app.goTo().groupPage();

        Set<GroupData> after=groupHelper.all();
        Assert.assertEquals( after.size(), before.size() - 1 );


        before.remove( deletedGroup );
        Assert.assertEquals( before, after );
    }


}




