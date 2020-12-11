package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

        Groups before=groupHelper.all();
        GroupData modifiedGroup=before.iterator().next();
        GroupData group=new GroupData()
                .withId( modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter( "test3" );
        groupHelper.modify(group);
        assertThat( app.group().count(), equalTo( before.size()));
        Groups after=groupHelper.all();
                assertThat( after, equalTo( before.without( modifiedGroup ).withAdded( group )));

    }
}


