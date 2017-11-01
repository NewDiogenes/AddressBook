package controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.AddressBookService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AddressBookControllerTest {
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
    public void shouldPassNewAddressToAddressBookService() {
        String testName = "test name";
        String testPoneNumber = "+00 1234567890";
        addressBookController.addNewEntry(testName, testPoneNumber);

        verify(addressBookService).addNewEntry(testName, testPoneNumber);
    }
}