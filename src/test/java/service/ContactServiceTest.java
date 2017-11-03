package service;

import model.AddressBook;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ContactServiceTest {
    private ContactService contactService;
    private AddressBook addressBook;
    private String testName = "test name";
    private String testPhoneNumber = "+00 1234567890";

    @Before
    public void setUp() throws Exception {
        contactService = new ContactService();
        addressBook = new AddressBook(new HashMap<>());
    }

    @Test
    public void addNewEntryAddsANewEntryToTheList() {
        addressBook = contactService.addNewEntry(addressBook, testName, testPhoneNumber);
        assertEquals(1, addressBook.getContactList().size());
    }

    @Test
    public void searchByNameReturnsNullWhenNoEntriesMatchTheNameGiven() {
        addressBook = contactService.addNewEntry(addressBook, testName, testPhoneNumber);
        assertNull(addressBook.getContactList().get("false name"));
    }

    @Test
    public void searchByNameReturnsContactMatchingTheNameGiven() {
        addressBook = contactService.addNewEntry(addressBook, testName, testPhoneNumber);
        assertEquals(testPhoneNumber, addressBook.getContactList().get(testName));
    }

    @Test
    public void addNewEntryDoesNotAddANewEntryIfThereExistsAnEntryWithTheSameName() {
        addressBook = contactService.addNewEntry(addressBook, testName, testPhoneNumber);
        String duplicatePhoneNumber = "0000 0000";
        addressBook = contactService.addNewEntry(addressBook, testName, duplicatePhoneNumber);
        assertNotEquals(duplicatePhoneNumber, addressBook.getContactList().get(testName));
    }

    @Test
    public void removeEntryRemovesTheEntryMatchingTheNameGiven() {
        addressBook = contactService.addNewEntry(addressBook, testName, testPhoneNumber);
        addressBook = contactService.removeEntry(addressBook, testName);
        assertNull(addressBook.getContactList().get(testName));
    }
}