package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


        public class ContactAddToGroupTest extends TestBase {



        @BeforeMethod
        public  void ensurePreconditions() {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                ContactHelper contactHelper=app.contact();
                contactHelper.create( new ContactData().withSurname( "Verem" ).withFirstname( "Anastasia" ).withAddress( "Minsk" ) );
            }


            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test1"));
            }
        }

        @Test
        public void testContactAddToGroup() {

            Contacts beforeContacts = app.db().contacts();
            ContactData selectContact = beforeContacts.iterator().next();
            Groups beforeGroups = app.db().groups();
            GroupData selectGroup = beforeGroups.iterator().next();
            app.goTo().homePage();

            if (!selectContact.getGroups().isEmpty() && selectContact.getGroups().contains(selectGroup)) {
                app.contact().deleteContactFromGroup(selectContact, selectGroup);
                assertThat(selectContact.getGroups().without(selectGroup), equalTo(app.db().contacts().stream().
                        filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
                app.goTo().homePage();
            }
            app.contact().selectShowGroup("[all]");
            app.contact().addContactToGroup(selectContact, selectGroup);
            assertThat(selectContact.getGroups().withAdded(selectGroup), equalTo(app.db().contacts().stream().
                    filter((c) -> c.getId() == selectContact.getId()).collect(Collectors.toList()).get(0).getGroups()));
        }

    }

