package controller;

import model.Contact;
import service.AddressBookService;

import java.util.ArrayList;
import java.util.List;

public class AddressBookController {
    private AddressBookService addressBookService;

    public AddressBookController() {
        this.addressBookService = new AddressBookService();
    }

    public void addNewEntry(String name, String phoneNumber){
        addressBookService.addNewEntry(name, phoneNumber);
    }

    public void removeEntry(String testName) {
        addressBookService.removeByName(testName);
    }

    public List<String> readAllEntries() {
        List<String> entryList = new ArrayList<>();
        for (Contact contact : addressBookService.getAllEntries()) {
            entryList.add("Name: " + contact.getName() + "\tPhone Number: " + contact.getPhoneNumber());
        }
        return entryList;
    }
}

