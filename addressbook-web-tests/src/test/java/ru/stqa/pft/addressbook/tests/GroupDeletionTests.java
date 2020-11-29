package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;

import java.util.List;

public class GroupDeletionTests extends TestBase {


    private int i;

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper = app.getGroupHelper();

        if (! app.getGroupHelper().isThereAGroup()) {
            groupHelper.createGroup( new GroupData("test N1", "null", "null" ) );
        }

        List<GroupData> before = groupHelper.getGroupList();
        groupHelper.selectGroup(before.size() - 1);
        groupHelper.deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();

        List<GroupData> after = groupHelper.getGroupList();
        Assert.assertEquals( after.size(), before.size() - 1);


        before.remove(before.size() - 1);
        Assert.assertEquals( before, after);
        }

    }




