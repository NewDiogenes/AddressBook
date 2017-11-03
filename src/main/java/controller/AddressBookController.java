package controller;

import model.AddressBook;
import service.AddressBookService;
import service.ContactService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressBookController {
    private AddressBookService addressBookService;
    private ContactService contactService;
    public static final String DEFAULT_BOOK_NAME = "default";

    public AddressBookController() {
        addressBookService = new AddressBookService();
        contactService = new ContactService();
    }

    public Map<String, AddressBook> addNewEntry(Map<String, AddressBook> bookList, String entryName, String phoneNumber){
        return addNewEntry(bookList, entryName, phoneNumber, DEFAULT_BOOK_NAME);
    }

    public Map<String, AddressBook> addNewEntry(Map<String, AddressBook> bookList,String entryName, String phoneNumber, String bookName){
        if (!bookList.containsKey(bookName)) {
            addressBookService.addNewBook(bookList, bookName);
        }
        AddressBook addressBook = bookList.get(bookName);
        addressBook = contactService.addNewEntry(addressBook, entryName, phoneNumber);
        return addressBookService.setBook(bookList, bookName, addressBook);
    }

    public Map<String, AddressBook> removeEntry(Map<String, AddressBook> bookList, String name){
        return removeEntry(bookList, name, DEFAULT_BOOK_NAME);
    }

    public Map<String, AddressBook> removeEntry(Map<String, AddressBook> bookList,String entryName, String bookName){
        AddressBook addressBook = bookList.get(bookName);
        addressBook = contactService.removeEntry(addressBook, entryName);
        return addressBookService.setBook(bookList, bookName, addressBook);
    }

    public Map<String, AddressBook> addBook(Map<String, AddressBook> bookList, String bookName) {
        return addressBookService.addNewBook(bookList, bookName);
    }
}

