import controller.AddressBookController;
import model.AddressBook;
import service.BookReaderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookApp {
    private AddressBookController addressBookController = new AddressBookController();
    private BookReaderService bookReaderService = new BookReaderService();
    private Map<String, AddressBook> bookList = new HashMap<>();

    public void runCommand(AddressBookCommand command, String[] args) {
        switch (command) {
            case ADD_CONTACT:
                addContact(args);
                break;
            case REMOVE_CONTACT:
                removeContact(args);
                break;
            case ADD_BOOK:
                addBook(args);
                break;
            case PRINT_BOOK:
                printBook(args);
                break;
            case PRINT_UNIQUE_CONTACTS:
                printUniqueContacts(args);
                break;
        }
    }

    private void printUniqueContacts(String[] args) {
        List<AddressBook> printList = new ArrayList<>();
        for (String bookName : args) {
            printList.add(bookList.get(bookName));
        }
       for (String entry : bookReaderService.readMultipleBooks(printList)) {
           System.out.println(entry);
       }
    }

    private void printBook(String[] args) {
        if (args.length == 1) {
            for (String entry : bookReaderService.readBook(bookList.get(args[0]))) {
                System.out.println(entry);
            }
        }
    }

    private void addBook(String[] args) {
        if (args.length == 1) {
            bookList = addressBookController.addBook(bookList, args[0]);
        }
    }

    private void addContact(String[] args) {
        if (args.length == 3) {
            bookList = addressBookController.addNewEntry(this.bookList, args[0], args[1], args[2]);
        } else if (args.length == 2) {
            bookList = addressBookController.addNewEntry(this.bookList, args[0], args[1]);
        }
    }

    private void removeContact(String[] args) {
        if (args.length == 1) {
            bookList = addressBookController.removeEntry(bookList, args[0]);
        }
        if (args.length == 2) {
            bookList = addressBookController.removeEntry(bookList, args[0], args[1]);
        }
    }

    public Map<String, AddressBook> getBookList() {
        return bookList;
    }

    public enum AddressBookCommand {
        ADD_CONTACT,
        REMOVE_CONTACT,
        ADD_BOOK,
        PRINT_BOOK,
        PRINT_UNIQUE_CONTACTS
    }
}
