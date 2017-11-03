package service;

import model.AddressBook;

import java.util.List;
import java.util.Map;

public class ContactService {

    public AddressBook addNewEntry(AddressBook addressBook, String name, String phoneNumber) {
        Map<String, String> contactList = addressBook.getContactList();
        if (!contactList.containsKey(name)) {
            contactList.put(name, phoneNumber);
        }
        return new AddressBook(contactList);
    }

    public AddressBook removeEntry(AddressBook addressBook, String name) {
        Map<String, String> contactList = addressBook.getContactList();
        contactList.remove(name);
        return new AddressBook(contactList);
    }
}
