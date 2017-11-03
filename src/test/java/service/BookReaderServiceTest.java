package service;

import model.AddressBook;
import org.junit.Before;
import org.junit.Test;
import service.BookReaderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BookReaderServiceTest {
    private BookReaderService bookReaderService;

    @Before
    public void setUp() throws Exception {
        bookReaderService = new BookReaderService();

    }

    @Test
    public void readContactShouldReturnTheNameAndNumberInAFormattedString() {
        String expectedResult = "Name: name\tPhone Number: number";
        assertEquals(expectedResult, bookReaderService.readContact("name", "number"));
    }

    @Test
    public void readBookShouldReturnAllContactsInTheAddressBookAsListOfFormattedStrings() {
        Map<String, String> contactList = new HashMap<>();
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            contactList.put("name" + i, "number" + i);
            expectedList.add(String.format("Name: name%s\tPhone Number: number%s", i, i));
        }
        assertTrue(bookReaderService.readBook(new AddressBook(contactList)).containsAll(expectedList));
    }

    @Test
    public void readMultipleBooksShouldReturnAllUniqueContactsAsArrayOfFormattedStrings() {
        Map<String, String> contactList = new HashMap<>();
        Map<String, String> otherContactList = new HashMap<>();
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            if (i == 5 || i % 2 == 0) {
                contactList.put("name" + i, "number" + i);
            }
            if (i % 2 == 1) {
                otherContactList.put("name" + i, "number" + i);
            }
            expectedList.add(String.format("Name: name%s\tPhone Number: number%s", i, i));
        }
        List<AddressBook> bookList = new ArrayList<>();
        bookList.add(new AddressBook(contactList));
        bookList.add(new AddressBook(otherContactList));
        assertTrue(bookReaderService.readMultipleBooks(bookList).containsAll(expectedList));
    }
}