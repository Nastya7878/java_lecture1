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
        int beforeTest= groupHelper.getGroupCount();


        groupHelper.createGroup( new GroupData("test N1", "null", "null" ) );

        app.getNavigationHelper().gotoGroupPage();
        int afterAddingTest= groupHelper.getGroupCount();
        Assert.assertEquals( beforeTest + 1, afterAddingTest);


        groupHelper.selectGroup();
        groupHelper.deleteSelectedGroups();

        app.getNavigationHelper().gotoGroupPage();

        int afterTest = groupHelper.getGroupCount();
        Assert.assertEquals( beforeTest, afterTest);
    }
}


