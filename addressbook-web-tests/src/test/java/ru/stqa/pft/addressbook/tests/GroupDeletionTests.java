package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;

public class GroupDeletionTests extends TestBase {


    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        GroupHelper groupHelper = app.getGroupHelper();
        int before= groupHelper.getGroupCount();
        if (! app.getGroupHelper().isThereAGroup()) {
            groupHelper.createGroup( new GroupData("test N1", "null", "null" ) );
        }

        groupHelper.selectGroup(before - 1);
        groupHelper.deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();

        int after = groupHelper.getGroupCount();
        Assert.assertEquals( after, before - 1);
    }
}


