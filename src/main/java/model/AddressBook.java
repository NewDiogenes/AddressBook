package model;

import java.util.Map;

public class AddressBook {
    private Map<String, String> contactList;

    public AddressBook(Map<String, String> contactList) {
        this.contactList = contactList;
    }

    public Map<String, String> getContactList() {
        return contactList;
    }
}
