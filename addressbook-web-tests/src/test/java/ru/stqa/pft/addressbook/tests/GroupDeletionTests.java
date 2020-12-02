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
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper=app.getGroupHelper();
        if (!groupHelper.isThereAGroup()) {
            groupHelper.createGroup( new GroupData( "test1", "null", "null" ) );
        }
    }

    @Test
    public void testGroupDeletion() {
        GroupHelper groupHelper=app.getGroupHelper();
        List<GroupData> before=groupHelper.getGroupList();
        groupHelper.selectGroup( before.size() - 1 );
        groupHelper.deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> after=groupHelper.getGroupList();
        Assert.assertEquals( after.size(), before.size() - 1);


        before.remove( before.size() - 1 );
        Assert.assertEquals( before, after);
        }
    }




