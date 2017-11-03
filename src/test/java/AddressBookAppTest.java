import controller.AddressBookController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.Assert.*;

public class AddressBookAppTest {
    private AddressBookApp app;
    private String testName = "test name";
    private String testPhoneNumber = "+00 1234567890";
    private String testBookName = "test book";
    private String[] args;
    @Before
    public void setUp() throws Exception {
        app = new AddressBookApp();
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
    }

    @Test
    public void executeShouldAddNewEntryToDefaultBookWhenGivenADD_CONTACTCommandWithNoBookName() {
        app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, new String[] {testName, testPhoneNumber});
        assertEquals(checkForEntry(AddressBookController.DEFAULT_BOOK_NAME, testName), testPhoneNumber);
    }

    @Test
    public void executeShouldAddNewEntryToABookWhenGivenADD_CONTACTCommandWithTheBookName() {
        app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, new String[] {testName, testPhoneNumber});
        assertEquals(checkForEntry(AddressBookController.DEFAULT_BOOK_NAME, testName), testPhoneNumber);
    }

    @Test
    public void executeShouldRemoveEntryFromDefaultBookWhenGivenRemove_CONTACTCommandWithoutABookName() {
        app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, new String[] {testName, testPhoneNumber});
        app.runCommand(AddressBookApp.AddressBookCommand.REMOVE_CONTACT, new String[] {testName});
        assertNull(checkForEntry(AddressBookController.DEFAULT_BOOK_NAME, testName));
    }

    @Test
    public void executeShouldRemoveEntryFromBookWhenGivenRemove_CONTACTCommandWithABookName() {
        app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, new String[] {testName, testPhoneNumber, testBookName});
        app.runCommand(AddressBookApp.AddressBookCommand.REMOVE_CONTACT, new String[] {testName, testBookName});
        assertNull(checkForEntry(testBookName, testName));
    }

    @Test
    public void executeShouldAddANewBookWhenGivenADD_BOOKCommandAndABookName() {
        app.runCommand(AddressBookApp.AddressBookCommand.ADD_BOOK, new String[] {testBookName});
        assertNotNull(app.getBookList().get(testBookName));
    }

    @Test
    public void executeShouldPrintEveryEntryInABookWhenGivenPRINT_BOOKCommandAndABookName() {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            String[] args = new String[] {testName + i, testPhoneNumber, testBookName};
            app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, args);
            expectedList.add(String.format("Name: %s\tPhone Number: %s", testName + i, testPhoneNumber));
        }
        ByteArrayOutputStream systemPrint = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemPrint));
        app.runCommand(AddressBookApp.AddressBookCommand.PRINT_BOOK, new String[] {testBookName});
        for (String entry : expectedList) {
            assertTrue(systemPrint.toString().contains(entry));
        }
    }

    @Test
    public void executeShouldPrintEveryUniqueEntryInAllBooksWhenGivenPRINT_UNIQUES_CONTACTSCommandAndAnArrayOfBookNames() {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            if (i == 5 || i % 2 == 0) {
                String[] args = new String[] {testName + i, testPhoneNumber + i, testBookName + 1};
                app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, args);
            }
            if (i % 2 == 1) {
                String[] args = new String[] {testName + i, testPhoneNumber + i, testBookName + 2};
                app.runCommand(AddressBookApp.AddressBookCommand.ADD_CONTACT, args);
            }
            expectedList.add(String.format("Name: %s\tPhone Number: %s", testName + i, testPhoneNumber));
        }
        ByteArrayOutputStream systemPrint = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemPrint));
        app.runCommand(AddressBookApp.AddressBookCommand.PRINT_UNIQUE_CONTACTS, new String[] {testBookName +1, testBookName + 2});
        for (String entry : expectedList) {
            assertTrue(systemPrint.toString().contains(entry));
        }
    }



    private String checkForEntry(String bookName, String entryName) {
        return app.getBookList().get(bookName).getContactList().get(entryName);
    }
}