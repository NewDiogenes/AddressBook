package service;

import model.AddressBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookReaderService {
    public String readContact(String name, String number) {
        return String.format("Name: %s\tPhone Number: %s", name, number);
    }

    public List<String> readBook(AddressBook addressBook) {
        return mapToStringList(addressBook.getContactList());
    }

    private List<String> mapToStringList(Map<String, String> contactList) {
        List<String> stringList = new ArrayList<>();
        String[] contactNames = contactList.keySet().toArray(new String[0]);
        for (String name : contactNames) {
            stringList.add(readContact(name, contactList.get(name)));
        }
        return stringList;
    }

    public List<String> readMultipleBooks(List<AddressBook> bookList) {
        Map<String, String> mergedList = new HashMap<>();
        for (AddressBook book : bookList) {
            mergedList.putAll(book.getContactList());
        }
        return mapToStringList(mergedList);
    }
}
