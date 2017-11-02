package service;

import model.AddressBook;
import model.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookService {
    private Map<String, Contact> contactList = new HashMap<>();
    private Map<String, AddressBook> books = new HashMap<>();

    public Map<String, Contact> getContactList() {
        return contactList;
    }

    public Map<String, AddressBook> getBooks() {
        return books;
    }

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

    public void removeByName(String contactName) {
        contactList.remove(contactName);
    }

    public void addNewBook(String newBook) {
        books.put(newBook, new AddressBook(newBook));
    }

    public void addNewEntry(String testName, String testPhoneNumber, String testBook) {
    }

    public void removeByName(String testName, String testBook) {
    }

    public List<Contact> getAllEntries(String testBook) {
        return null;
    }
}
