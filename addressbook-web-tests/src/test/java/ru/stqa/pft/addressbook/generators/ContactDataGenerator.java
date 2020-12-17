package ru.stqa.pft.addressbook.generators;

import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt( args[0] );
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts (count);
        saveAsXml (contacts, file);
    }

    private static void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        XStream xstream = new XStream();
        xstream.processAnnotations( ContactData.class );
        String xml = xstream.toXML( contacts );
        Writer writer = new FileWriter( file );
        writer.write(xml);
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withSurname(String.format("test_surname %s", i))
                    .withFirstname(String.format("test_name %s", i))
                    .withAddress( "address"));
        }
        return contacts;

    }
}
