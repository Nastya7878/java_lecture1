package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        GroupHelper groupHelper = app.getGroupHelper();

        if (!groupHelper.isThereAGroup()) {
            groupHelper.createGroup( new GroupData( "test 1", "null", "null" ) );
            }

        groupHelper.selectGroup(before - 1);
        groupHelper.initGroupModification();
        groupHelper.fillGroupForm(new GroupData( "test 11", "test 12", "test 13" ) );
        groupHelper.submitGroupModification();
        app.getNavigationHelper().gotoGroupPage();

        int after = groupHelper.getGroupCount();
        Assert.assertEquals(after, before );
    }
}


