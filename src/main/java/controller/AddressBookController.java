package controller;

import service.AddressBookService;

public class AddressBookController {
    private AddressBookService addressBookService;

    public AddressBookController() {
        this.addressBookService = new AddressBookService();
    }

    public void addNewEntry(String name, String phoneNumber){
        addressBookService.addNewEntry(name, phoneNumber);
    }
}

