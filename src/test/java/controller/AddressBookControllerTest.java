package controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import service.AddressBookService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddressBookControllerTest {
    @InjectMocks
    AddressBookController addressBookController;

    @Mock
    AddressBookService addressBookService;

    @Before
    public void setUp() throws Exception {
        this.addressBookService = mock(AddressBookService.class);
        this.addressBookController = new AddressBookController(addressBookService);
    }

    @Test
    public void shouldPassNewAddressToAddressBookService() {
        String testName = "test name";
        String testPoneNumber = "+00 1234567890";

        verify(addressBookService).addNewAddress(testName, testPoneNumber);
    }
}