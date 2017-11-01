package service;

import model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookServiceTest {
    private AddressBookService addressBookService;
    private String testName = "test name";
    private String testPhoneNumber = "+00 1234567890";

    @Before
    public void setUp() throws Exception {
        addressBookService = new AddressBookService();
    }

    @Test
    public void getAllEntriesReturnsAnEmptyListWhenThereAreNoAddresses() {
        assertEquals(Collections.emptyList(), addressBookService.getAllEntries());
    }

    @Test
    public void addNewEntryAddsANewEntryToTheList() {
        addressBookService.addNewEntry(testName, testPhoneNumber);
        assertEquals(1, addressBookService.getAllEntries().size());
    }

    @Test
    public void searchByNameReturnsNullWhenNoEntriesMatchTheNameGiven() {
        addressBookService.addNewEntry(testName, testPhoneNumber);
        assertNull(addressBookService.searchByName("false name"));
    }

    @Test
    public void searchByNameReturnsContactMatchingTheNameGiven() {
        assert (addressBookService.addNewEntry(testName, testPhoneNumber));
        assertEquals(new Contact(testName, testPhoneNumber), addressBookService.searchByName(testName));
    }

    @Test
    public void addNewEntryDoesNotAddANewEntryIfThereExistsAnEntryWithTheSameName() {
        addressBookService.addNewEntry(testName, testPhoneNumber);
        String duplicatePhoneNumber = "0000 0000";
        assertFalse(addressBookService.addNewEntry(testName, duplicatePhoneNumber));
        assertNotEquals(new Contact(testName, duplicatePhoneNumber), addressBookService.searchByName(testName));
    }

    @Test
    public void removeByNameRemovesTheEntryMatchingTheNameGiven() {
        addressBookService.addNewEntry(testName, testPhoneNumber);
        addressBookService.removeByName(testName);
        assertNull(addressBookService.searchByName(testName));
    }
}