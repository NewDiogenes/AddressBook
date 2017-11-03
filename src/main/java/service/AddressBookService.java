package service;

import model.AddressBook;

import java.util.HashMap;
import java.util.Map;

public class AddressBookService {
    private Map<String, AddressBook> books = new HashMap<>();

    public Map<String, AddressBook> addNewBook(Map<String, AddressBook> bookList, String bookName) {
        if (!bookList.containsKey(bookName)) {
            bookList.put(bookName, new AddressBook(new HashMap<>()));
        }
        return bookList;
    }

    public Map<String, AddressBook> setBook(Map<String, AddressBook> bookList, String bookName, AddressBook book) {
        if (bookList.containsKey(bookName)) {
            bookList.replace(bookName, book);
        }
        return bookList;
    }
}
