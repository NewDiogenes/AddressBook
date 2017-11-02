package model;

import java.util.List;

public class AddressBook {
    private String name;
    private List<Contact> contactList;

    public AddressBook(String name, List<Contact> contactList) {
        this.name = name;
        this.contactList = contactList;
    }

    public AddressBook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
