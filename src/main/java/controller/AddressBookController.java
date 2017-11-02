package controller;

import model.AddressBook;
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
        return writeBookToString(addressBookService.getAllEntries());
    }

    public void addNewBook(String newBook) {
        addressBookService.addNewBook(newBook);
    }

    public void addNewEntry(String testName, String testPhoneNumber, String testBook) {
        addressBookService.addNewEntry(testName, testPhoneNumber, testBook);
    }

    public void removeEntry(String testName, String testBook) {
        addressBookService.removeByName(testName, testBook);
    }

    public List<String> readAllEntries(String testBook) {
        return writeBookToString(addressBookService.getAllEntries(testBook));
    }

    private List<String> writeBookToString(List<Contact> contactList) {
        List<String> entryList = new ArrayList<>();
        for (Contact contact : contactList) {
            entryList.add("Name: " + contact.getName() + "\tPhone Number: " + contact.getPhoneNumber());
        }
        return entryList;
    }
}

