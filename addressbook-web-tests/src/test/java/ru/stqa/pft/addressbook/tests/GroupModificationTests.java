package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int beforeTest = app.getGroupHelper().getGroupCount();

        GroupHelper groupHelper = app.getGroupHelper();

        boolean isGroupCreated = false;
        if (!groupHelper.isThereAGroup()) {
            groupHelper.createGroup( new GroupData( "test 1", "null", "null" ) );
            isGroupCreated = true;
        }

        groupHelper.selectGroup();
        groupHelper.initGroupModification();
        groupHelper.fillGroupForm(new GroupData( "test 11", "test 12", "test 13" ) );
        groupHelper.submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();

        if(isGroupCreated)
        {
            groupHelper.selectGroup();
            groupHelper.deleteSelectedGroups();
        }

        int afterTest = groupHelper.getGroupCount();
        Assert.assertEquals(afterTest, beforeTest);
    }
}


