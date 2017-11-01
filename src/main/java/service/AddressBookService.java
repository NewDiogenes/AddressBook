package service;

import model.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookService {
    Map<String, Contact> contactList = new HashMap<>();

    public boolean addNewEntry(String contactName, String phoneNumber) {
        if (contactList.containsKey(contactName)) {
            return false;
        } else{
            contactList.put(contactName, new Contact(contactName, phoneNumber));
            return true;
        }
    }

    public List<Contact> getAllEntries() {
        List<Contact> entryList = new ArrayList<>();
        entryList.addAll(contactList.values());
        return entryList;
    }

    public Contact searchByName(String contactName) {
        return contactList.get(contactName);
    }

    public void removeByName(String contactName) {
        contactList.remove(contactName);
    }
}
