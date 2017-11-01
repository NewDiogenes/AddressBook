package controller;

import model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.AddressBookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressBookControllerTest {
    private String testName = "test name";
    private String testPhoneNumber = "+00 1234567890";
    @Mock
    private AddressBookService addressBookService;

    @InjectMocks
    private AddressBookController addressBookController;


    @Before
    public void setUp() throws Exception {
        this.addressBookController = new AddressBookController();
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void addNewEntryShouldPassNewAddressToAddressBookService() {
        addressBookController.addNewEntry(testName, testPhoneNumber);
        verify(addressBookService).addNewEntry(testName, testPhoneNumber);
    }

    @Test
    public void removeEntryShouldPassTheContactNameToAddressBookService() throws Exception {
        addressBookController.removeEntry(testName);
        verify(addressBookService).removeByName(testName);
    }

    @Test
    public void readAllEntriesShouldReturnAllContactsAsAnArrayOfStrings() {
        List<String> expectedResult = new ArrayList<>();
        List<Contact> mockContactList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            String newName = testName + i;
            mockContactList.add(new Contact(newName, testPhoneNumber));
            expectedResult.add("Name: " + newName + "\tPhone Number: " + testPhoneNumber);
        }

        when(addressBookService.getAllEntries()).thenReturn(mockContactList);
        assertEquals(expectedResult, addressBookController.readAllEntries());
    }
}