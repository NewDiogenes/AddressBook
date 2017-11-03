package service;

import model.AddressBook;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AddressBookServiceTest {
    private AddressBookService addressBookService;
    private Map<String, AddressBook> bookList;
    private String bookName = "new book";
    private AddressBook testBook;

    @Before
    public void setUp() throws Exception {
        addressBookService = new AddressBookService();
        bookList = new HashMap<>();
        Map<String, String> testContacts = new HashMap<>();
        testContacts.put("test book", "+00 1234567890");
        testBook = new AddressBook(testContacts);
    }

    @Test
    public void addNewBookShouldAddANewAddressBookWithTheGivenName() {
        bookList = addressBookService.addNewBook(bookList, bookName);
        assertTrue(bookList.containsKey(bookName));
    }

    @Test
    public void setBookShouldDoNothingIfTheBookDoesNotExist() {
        bookList = addressBookService.addNewBook(bookList, bookName);
        assertEquals(bookList, addressBookService.setBook(bookList, "wrong name", testBook));
    }

    @Test
    public void setBookShouldReplaceTheBookMatchingTheNameGiven() {
        bookList = addressBookService.addNewBook(bookList, bookName);
        assertEquals(testBook, addressBookService.setBook(bookList, bookName, testBook).get(bookName));
    }
}