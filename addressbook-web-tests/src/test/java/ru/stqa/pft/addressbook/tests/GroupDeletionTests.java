package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    private int i;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        GroupHelper groupHelper=app.group();
        if (groupHelper.list().size()== 0) {
            groupHelper.create( new GroupData( "test1", "null", "null" ) );
        }
    }

    @Test
    public void testGroupDeletion() {
        GroupHelper groupHelper=app.group();
        List<GroupData> before=groupHelper.list();
        int index = before.size() - 1;
        groupHelper.delete( groupHelper, index );
        app.goTo().groupPage();

        List<GroupData> after=groupHelper.list();
        Assert.assertEquals( after.size(), before.size() - 1);


        before.remove( index );
        Assert.assertEquals( before, after);
        }


}




