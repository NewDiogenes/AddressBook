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
    public void shouldReturnAnEmptyListWhenThereAreNoAddresses(){
        assertEquals(Collections.emptyList(), addressBookService.getAllEntries());
    }

    @Test
    public void shouldAddNewEntriesToTheList(){
        addressBookService.addNewEntry(testName, testPhoneNumber);
        assertEquals(1, addressBookService.getAllEntries().size());
    }
}