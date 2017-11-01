package service;

import model.Contact;

import java.util.ArrayList;
import java.util.List;

public class AddressBookService {
    List<Contact> contactList = new ArrayList<>();

    public void addNewEntry(String testName, String testPoneNumber) {
        contactList.add(new Contact(testName, testPoneNumber));
    }

    public List<Contact> getAllEntries() {
        return this.contactList;
    }
}
