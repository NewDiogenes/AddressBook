package controller;

import model.AddressBook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.AddressBookService;
import service.ContactService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressBookControllerTest {
    private String testName = "test name";
    private String testPhoneNumber = "+00 1234567890";
    private String testBookName = "test book";
    private AddressBook testBook;
    private AddressBook defaultBook;
    private Map<String, AddressBook> bookList;
    private Map<String, String> contactList;
    @Mock
    private AddressBookService addressBookService;
    @Mock
    private ContactService contactService;

    @InjectMocks
    private AddressBookController addressBookController;


    @Before
    public void setUp() throws Exception {
        addressBookController = new AddressBookController();
        MockitoAnnotations.initMocks(this);
        bookList = new HashMap<>();
        testBook = new AddressBook(new HashMap<>());
        defaultBook = new AddressBook(new HashMap<>());
        bookList.put(AddressBookController.DEFAULT_BOOK_NAME, defaultBook);
        bookList.put(testBookName, testBook);
    }

    @Test
    public void addNewEntryShouldPassNewAddressAndDefaultBookToContactService() {
        addressBookController.addNewEntry(bookList, testName, testPhoneNumber);
        verify(contactService).addNewEntry(defaultBook, testName, testPhoneNumber);
    }

    @Test
    public void removeEntryShouldPassTheContactNameAndDefaultBookToContactBookService() throws Exception {
        addressBookController.removeEntry(bookList, testName);
        verify(contactService).removeEntry(defaultBook, testName);
    }

    @Test
    public void addBookShouldPassNewBookNameAndTheBookListToAddressBookServiceService() {
        addressBookController.addBook(bookList, testBookName);
        verify(addressBookService).addNewBook(bookList, testBookName);
    }

    @Test
    public void addNewEntryShouldPassNewContactAndSpecifiedBookToAddressBookService() {
        addressBookController.addNewEntry(bookList, testName, testPhoneNumber, testBookName);
        verify(contactService).addNewEntry(testBook, testName, testPhoneNumber);
    }

    @Test
    public void removeEntryShouldPassTheContactNameAndBookNameToAddressBookService() {
        addressBookController.removeEntry(bookList, testName, testBookName);
        verify(contactService).removeEntry(testBook, testName);
    }

    @Test
    public void addNewEntryShouldCreateAnAddressBookIfOneWithTheSpecifiedNameDoesNotExist() {
        addressBookController.addNewEntry(bookList, testName, testPhoneNumber, "other name");
        verify(addressBookService).addNewBook(bookList, "other name");
    }
}